package org.shopkeeper.database.modules;

import org.shopkeeper.database.modules.sqllite.SQLLiteModule;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DatabaseChooser {

    // SQL LITE
    private static SQLLiteModule SQLLITEMODULE = new SQLLiteModule();
    private static Thread dbThread;

    public static Integer CURRENTDB = null;

    // TODO databsestarter class
    public static void start(DatabaseModule module) {
                dbThread = new Thread(module, "DATABASE_THREAD");
                dbThread.start();
                CURRENTDB = module.TYPE;
    }

    public static DatabaseModule getDatabase(Integer databaseType) {
        if(databaseType == DatabaseTypes.DATABASETYPE_SQLLITE) {
            return SQLLITEMODULE;
        } else {
            return null;
        }
    }

}
