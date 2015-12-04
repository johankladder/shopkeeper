package org.shopkeeper.parsers;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.modules.SQLLiteModule;

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

    public static void main(String[] args) throws InterruptedException {
        DatabaseChooser.start();
        Thread.sleep(5000);
        DatabaseChooser.start();
    }
}
