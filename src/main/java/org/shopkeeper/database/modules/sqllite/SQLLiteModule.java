package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.parsers.ResultParser;
import org.shopkeeper.database.parsers.SQLLiteQueryCreator;
import org.shopkeeper.subjects.parsers.SubjectResultSetParser;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.AntiLockSystem;

import java.sql.*;
import java.util.LinkedList;

public class SQLLiteModule extends DatabaseModule implements Runnable {

    public static Connection CONNECTION = null;
    public static boolean RUNNING = false;
    public static boolean WAS_INITIALIZED = false;
    public final LinkedList queue = new LinkedList();
    public static ResultSet RESULTSET = null;
    private static Integer REQUESTEDTYPE = null;


    @Override
    public void add(Subject subject) {
        processQueryNoResult(SQLLiteQueryCreator.createInsertQuery(subject));
    }


    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void update(Subject subject) {
        processQueryNoResult(SQLLiteQueryCreator.createUpdateQuery(subject));
    }

    @Override
    public void showAll(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            String query = SQLLiteQueryCreator.createSelectAllQuery((String) Item.getInitFields().get("tablename"));
            processQueryResult(query, SubjectTypes.ITEM);
        } else if (subjectType == SubjectTypes.CATEGORY) {
            String query = SQLLiteQueryCreator.createSelectAllQuery((String) Category.getInitFields().get("tablename"));
            processQueryResult(query, SubjectTypes.CATEGORY);
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            String query = SQLLiteQueryCreator.createSelectAllQuery((String) Customer.getInitFields().get("tablename"));
            processQueryResult(query, SubjectTypes.CUSTOMER);
        }
    }


    // TODO PREPARED STATMENT
    public void processQueryNoResult(String query) {
        if (query != null) {
            synchronized (queue) {
                queue.addLast(query);
                queue.notify();
            }
        }
    }

    public ResultSet processQueryResult(String query, Integer subjectType) {
        if (query != null) {
            synchronized (queue) {
                REQUESTEDTYPE = subjectType;
                queue.addLast(query);
                queue.notify();
            }
        }
        return null;
    }

    private void initDatabase() {
        try {
            Statement stmt = CONNECTION.createStatement();
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Item.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Customer.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Category.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            WAS_INITIALIZED = true; // Set status
            AntiLockSystem.notifyLock();
        } catch (SQLException e) {
            e.printStackTrace();
            WAS_INITIALIZED = false;
        }

    }


    // TODO Connection checking
    public void run() {
        if (!RUNNING) {  // Check if the database is already RUNNING:
            RUNNING = true;
            try {
                Class.forName("org.sqlite.JDBC");
                CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
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
                        if (!ResultParser.queryWithResult(query)) {
                            Statement stmt = CONNECTION.createStatement();
                            stmt.execute(query);
                        } else {

                            Statement stmt = CONNECTION.createStatement();
                            RESULTSET = stmt.executeQuery(query);
                            SubjectResultSetParser.parseResultSetToModule(RESULTSET, REQUESTEDTYPE);
                            REQUESTEDTYPE = null; // Reset the REQUESTEDTYPE, so i can be used next time.
                            AntiLockSystem.notifyLockDatabase();

                        }
                        // Release a given lock here!
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    } finally {
                        // release a given lock!
                    }
                    // Release lock anyway here...
                }

            } catch (Exception e) {
                // Set status:
                DatabaseHandler.connectionNotEstablished();
                CONNECTED = false;
            } finally {
                // release a given lock!
            }

        }
    }

}
