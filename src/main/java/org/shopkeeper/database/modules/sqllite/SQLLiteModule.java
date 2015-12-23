package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.parsers.ResultParser;
import org.shopkeeper.database.parsers.SQLLiteQueryCreator;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;

import java.sql.*;
import java.util.LinkedList;
// TODO Write status notificationer
// TODO Process bulk parsers
public class SQLLiteModule extends DatabaseModule implements Runnable {

    public static Connection CONNECTION = null;
    public static boolean RUNNING = false;
    public static boolean WAS_INITIALIZED = false;
    public final LinkedList queue = new LinkedList();
    public static ResultSet RESULTSET = null;


    @Override
    public void add(Subject subject) {
        processQueryNoResult(SQLLiteQueryCreator.createInsertQuery(subject));
    }


    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void showAll(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            String query = SQLLiteQueryCreator.createSelectAllQuery((String) Item.getInitFields().get("tablename"));
        } else if (subjectType == SubjectTypes.CATEGORY) {

        } else if (subjectType == SubjectTypes.CUSTOMER) {

        }
    }


    // TODO PREPARED STATMENT
    public void processQueryNoResult(String query) {
        if(query != null) {
            synchronized (queue) {
                queue.addLast(query);
                queue.notify();
            }
        }
    }

    public ResultSet processQueryResult(String query) {
        if (WAS_INITIALIZED && CONNECTED) {
            try {
                Statement stmt = CONNECTION.createStatement();
                ResultSet result = stmt.executeQuery(query);
                System.out.println(result.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    // TODO Put in abstract class and make initparser
    private void initDatabase() {
        try {
            Statement stmt = CONNECTION.createStatement();
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Item.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Customer.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Category.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            WAS_INITIALIZED = true; // Set status
            synchronized (Preloader.ready) {
                Preloader.ready.notify();
            }
        } catch (SQLException e) {
            e.printStackTrace();;
            WAS_INITIALIZED = false;
        }

    }


    // TODO Connection checking
    public void run() {
        if (!RUNNING) {  // Check if the database is already RUNNING:
            RUNNING = true;
            try {
                Class.forName("org.sqlite.JDBC");
                CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + DBNAME);
                CONNECTED = true;
                initDatabase(); // Initializes tables in this database

                String query;

                while (true) {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            try {
                                queue.wait();
                            } catch (InterruptedException ignored) {
                            }
                        }

                        query = (String) queue.removeFirst();
                    }

                    try {
                        if(!ResultParser.queryWithResult(query)) {
                            Statement stmt = CONNECTION.createStatement();
                            stmt.execute(query);
                            System.out.println("Executed query: " + query);
                        } else {
                            Statement stmt = CONNECTION.createStatement();
                            RESULTSET = stmt.executeQuery(query);
                            System.out.println("Executed query: " + query);
                        }
                    } catch (RuntimeException e) {
                        // TODO Log why query was not excecuted correctly
                    }
                }

            } catch (Exception e) {
                // Set status:
                DatabaseHandler.connectionNotEstablished();
                CONNECTED = false;
            }

        }
    }

}
