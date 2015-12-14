package org.shopkeeper.database.modules.sqllite;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.subjects.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;


/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SQLLiteModule extends DatabaseModule implements Runnable {

    // static connection -> so no multiple connectios.
    public static Connection conn = null;
    public static boolean running = false;
    public static boolean connected = false;


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

    public void run() {
        if(!running) {  // Check if the database is already running:
            running = true;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + DBNAME);
                System.out.println("Connection established in: " + Thread.currentThread().getName());
                connected = true;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            while (running) {
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
