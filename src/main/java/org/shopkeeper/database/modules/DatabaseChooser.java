package org.shopkeeper.database.modules;

import org.shopkeeper.database.modules.sqllite.SQLLiteModule;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DatabaseChooser {

    // SQL LITE
    private static SQLLiteModule sqllite = new SQLLiteModule();
    private static Thread sqlLiteThread;

    public static void start() {
                sqlLiteThread = new Thread(sqllite, "SQLLITE_DATABASE_THREAD");
                sqlLiteThread.start();
    }

    public static DatabaseModule getDatabase(Integer databaseType) {
        if(databaseType == DatabaseTypes.DATABASETYPE_SQLLITE) {
            return sqllite;
        } else {
            return null;
        }
    }

}
