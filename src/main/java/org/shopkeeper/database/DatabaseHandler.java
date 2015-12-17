package org.shopkeeper.database;

import org.shopkeeper.database.modules.DatabaseModule;
import org.xml.sax.SAXException;

/**
 * Created by root on 16-12-15.
 */
public class DatabaseHandler {

    private static Thread DBTHREAD = null;

    public static void start(DatabaseModule module) {
        DBTHREAD = new Thread(module, "DATABASETHREAD");
        DBTHREAD.start();
    }
}
