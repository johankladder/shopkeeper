package org.shopkeeper.database.modules;

import org.shopkeeper.database.modules.sqllite.SQLLiteModule;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DatabaseChooser {

    // SQL LITE
    private static SQLLiteModule SQLLITEMODULE = new SQLLiteModule();

    public static DatabaseModule getDatabase(Integer databaseType) {
        if(databaseType == DatabaseTypes.DATABASETYPE_SQLLITE) {
            return SQLLITEMODULE;
        } else {
            return null;
        }
    }

}
