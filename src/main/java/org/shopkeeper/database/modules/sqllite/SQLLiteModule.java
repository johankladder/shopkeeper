package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.parsers.ResultParser;
import org.shopkeeper.database.parsers.SQLLiteQueryCreator;
import org.shopkeeper.subjects.parsers.SubjectResultSetParser;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;

import java.sql.*;
import java.util.LinkedList;

public class SQLLiteModule extends DatabaseModule implements Runnable {

    public static Connection CONNECTION = null;
    public static boolean RUNNING = false;
    public static boolean WAS_INITIALIZED = false;
    public final LinkedList queue = new LinkedList();
    public static ResultSet RESULTSET = null;
    private static Integer REQUESTEDTYPE = null;
    private int counter = 0;


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

    @Override
    public void initQuery(String query) {
        processQueryNoResult(query);
    }


    // TODO PREPARED STATMENT? Necessary with sqlite?
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


    public void run() {
        if (!RUNNING) {  // Check if the database is already RUNNING:
            RUNNING = true;
            try {
                synchronized (Thread.currentThread()) {
                    Class.forName("org.sqlite.JDBC");
                    CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
                    CONNECTED = true;
                    System.out.println("INFO: Database has an valid connection and the module is running");
                    Thread.currentThread().notify();
                }
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
                            synchronized (Thread.currentThread()) {
                                Statement stmt = CONNECTION.createStatement();
                                stmt.execute(query);
                                Thread.currentThread().notify();
                                System.out.println("INFO: Executed query: " + query);
                            }
                        } else {

                            synchronized (Thread.currentThread()) {
                                Statement stmt = CONNECTION.createStatement();
                                RESULTSET = stmt.executeQuery(query);
                                SubjectResultSetParser.parseResultSetToModule(RESULTSET, REQUESTEDTYPE);
                                REQUESTEDTYPE = null; // Reset the REQUESTEDTYPE, so i can be used next time
                                System.out.println("INFO: Executed query: " + query);
                                Thread.currentThread().notify();
                            }


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
