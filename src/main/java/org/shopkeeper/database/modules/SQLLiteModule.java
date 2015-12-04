package org.shopkeeper.database.modules;

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
    public static ArrayList<String> queue = new ArrayList<String>();
    public static Thread thread = new Thread(new SQLLiteModule(), "SQLLite-database-thread");


    @Override
    public void add(Subject subject) {
        System.out.println("hoi");
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

    public static void start() {
        thread.start();
    }

    public void stop() {

    }

    public void run() {
        if(!running) {  // Check if the database is already running:
            running = true;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + DBNAME);
                System.out.println("Connection established");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            while (running) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SQLLiteModule l = new SQLLiteModule();
    }


}
