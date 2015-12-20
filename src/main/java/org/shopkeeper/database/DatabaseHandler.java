package org.shopkeeper.database;

import org.shopkeeper.database.modules.DatabaseModule;
import org.xml.sax.SAXException;

/**
 * Created by root on 16-12-15.
 */
public class DatabaseHandler {

    private static Thread DBTHREAD = null;
    private static boolean STARTED = false;

    public static void start(DatabaseModule module) {
        if(!STARTED) {
            DBTHREAD = new Thread(module, "DATABASETHREAD");
            DBTHREAD.start();
            STARTED = true;
        }
    }

    public static void connectionNotEstablished() {
        STARTED = false;
    }

}
