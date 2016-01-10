package org.shopkeeper.database.modules;

import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.subjects.modules.SubjectModule;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class DatabaseStarter implements Runnable{

    public static Thread DBTHREAD = null;

    @Override
    public void run() {
        DatabaseModule module = DatabaseChooser.getDatabase(PreferenceModule.DATABASE_TYPE);
        SubjectModule.DB = module;
        DBTHREAD = new Thread(module, "DATABASETHREAD FOR " + module.getClass().getName());
        DBTHREAD.start();

        synchronized (DBTHREAD) {
            try {
                DBTHREAD.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (Thread.currentThread()) {
            Thread.currentThread().notify();
        }
    }
}
