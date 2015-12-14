package org.shopkeeper.database.modules;

import org.junit.Test;
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
        Thread.sleep(5000);
        assertTrue(DatabaseModule.isConnected());
        assertEquals(DatabaseTypes.DATABASETYPE_SQLLITE, DatabaseChooser.CURRENTDB);
    }

}