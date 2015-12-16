package org.shopkeeper.database.modules;

import org.shopkeeper.database.modules.sqllite.SQLLiteModule;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DatabaseChooser {

    // SQL LITE
    private static SQLLiteModule SQLLITEMODULE = new SQLLiteModule();
    private static Thread sqlLiteThread;

    public static Integer CURRENTDB = null;

    // TODO Param for starting a predefined db and init dbtype from that
    public static void start() {
                sqlLiteThread = new Thread(SQLLITEMODULE, "SQLLITE_DATABASE_THREAD");
                sqlLiteThread.start();
                CURRENTDB = DatabaseTypes.DATABASETYPE_SQLLITE;
    }

    public static DatabaseModule getDatabase(Integer databaseType) {
        if(databaseType == DatabaseTypes.DATABASETYPE_SQLLITE) {
            return SQLLITEMODULE;
        } else {
            return null;
        }
    }

}
