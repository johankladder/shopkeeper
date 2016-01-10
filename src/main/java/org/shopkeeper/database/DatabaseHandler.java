package org.shopkeeper.database;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseStarter;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.modules.sqllite.SQLLiteModule;
import org.shopkeeper.database.parsers.SQLLiteQueryCreator;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;

import java.util.ArrayList;

/**
 * Created by root on 16-12-15.
 */
public class DatabaseHandler implements Runnable {

    private static boolean STARTED = false;
    private static boolean running = false;
    private static DatabaseModule module;

    public DatabaseHandler(DatabaseModule module) {
        this.module = module;
    }

    public static void connectionNotEstablished() {
        STARTED = false;
    }

    @Override
    public void run() {
                synchronized (DatabaseStarter.DBTHREAD) {
                    try {
                        ArrayList<String> queries = new ArrayList<>();
                        queries.add(SQLLiteQueryCreator.createInitQuery(Item.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
                        queries.add(SQLLiteQueryCreator.createInitQuery(Customer.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
                        queries.add(SQLLiteQueryCreator.createInitQuery(Category.getInitFields(), DatabaseTypes.DATABASETYPE_SQLLITE));

                        SQLLiteModule mo = (SQLLiteModule) module;

                        for(String query : queries) {
                            mo.initQuery(query);
                            DatabaseStarter.DBTHREAD.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("done with init");
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().notify();
                }
            }

        }



