package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.queries.SQLLiteQueryCreator;
import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SQLLiteModule extends DatabaseModule implements Runnable {

    public static Connection CONNECTION = null;
    public static boolean RUNNING = false;
    public static boolean WAS_INITIALIZED = false;


    @Override
    public void add(Subject subject) {
        System.out.println(subject);
    }


    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void showAll(Subject subject) {

    }


    public void processQueryNoResult() {
        if(WAS_INITIALIZED && CONNECTED) {

        }
    }

    public void processQueryResult() {
        if(WAS_INITIALIZED && CONNECTED) {

        }
    }


    private static void initDatabase() {
        try {
            Statement stmt = CONNECTION.createStatement();
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Item.getFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Customer.getFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            stmt.execute(SQLLiteQueryCreator.createInitQuery(Category.getFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
            WAS_INITIALIZED = true; // Set status
        } catch (SQLException e) {
            e.printStackTrace();
            WAS_INITIALIZED = false;
        }

    }


    public void run() {
        if(!RUNNING) {  // Check if the database is already RUNNING:
            RUNNING = true;
            try {
                Class.forName("org.sqlite.JDBC");
                CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + DBNAME);
                System.out.println("Connection established in: " + Thread.currentThread().getName());
                CONNECTED = true;
                initDatabase(); // Inititializes tables in this database
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            while (RUNNING) {
                    // Check connection!
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }






}
