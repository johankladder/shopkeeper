package org.shopkeeper.database;

import org.shopkeeper.database.modules.DatabaseModule;
import org.xml.sax.SAXException;

/**
 * Created by root on 16-12-15.
 */
public class DatabaseHandler implements Runnable {

    private static Thread DBTHREAD = null;
    private static boolean STARTED = false;
    private static boolean running = false;
    private static DatabaseModule module;

<<<<<<< HEAD
=======
    public static void start(DatabaseModule module) {
        if(!STARTED) {
            DBTHREAD = new Thread(module, "DATABASETHREAD");
            DBTHREAD.start();
            STARTED = true;
        }
    }
>>>>>>> 4ba6fda71f582aa17654196ccb17249bcac14485

    public DatabaseHandler(DatabaseModule module) {
        this.module = module;
    }
    public static void connectionNotEstablished() {
        STARTED = false;
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            if(!STARTED) {
                DBTHREAD = new Thread(module, "DATABASETHREAD FOR " + module.getClass().getName());
                DBTHREAD.start();
                STARTED = true;
            }
        }
    }
}
