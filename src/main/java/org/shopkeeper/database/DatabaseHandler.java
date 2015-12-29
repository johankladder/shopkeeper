package org.shopkeeper.database;

import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.modules.SubjectModule;

/**
 * Created by root on 16-12-15.
 */
public class DatabaseHandler implements Runnable {

    private static Thread DBTHREAD = null;
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
        running = true;
        while(running) {
            if(!STARTED) {
                module = DatabaseChooser.getDatabase(Preference.DATABASE_TYPE);
                SubjectModule.DB = module;
                DBTHREAD = new Thread(module, "DATABASETHREAD FOR " + module.getClass().getName());
                DBTHREAD.start();
                STARTED = true;
            }
        }
    }
}
