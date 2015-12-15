package org.shopkeeper.database.modules;

import org.junit.Test;
import org.shopkeeper.database.modules.sqllite.SQLLiteModule;
import org.shopkeeper.subjectsmodules.CustomerModule;
import org.shopkeeper.subjectsmodules.ItemModule;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 14-12-15.
 */
public class DatabaseModuleTest {

    @Test
    public void testDatabase() throws InterruptedException {
        ItemModule module =  new ItemModule();
        new CustomerModule();
        Thread.sleep(1000);
        assertTrue(DatabaseModule.isConnected());
        assertEquals(DatabaseTypes.DATABASETYPE_SQLLITE, DatabaseChooser.CURRENTDB);
        assertTrue(SQLLiteModule.WAS_INITIALIZED);
        assertTrue(SQLLiteModule.RUNNING);
        assertTrue(SQLLiteModule.CONNECTED);
    }

}