package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.modules.queries.QueryCreator;
import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.items.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SQLLiteModule extends DatabaseModule implements Runnable {

    // static connection -> so no multiple connectios.
    public static Connection CONNECTION = null;
    public static boolean RUNNING = false;


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

    }

    public void processQueryResult() {

    }

    private static void initDatabase() {
        try {
            Statement stmt = CONNECTION.createStatement();
            stmt.execute(QueryCreator.createInitQuery(Item.getFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
        } catch (SQLException e) {
            e.printStackTrace();
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
                initDatabase();
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
