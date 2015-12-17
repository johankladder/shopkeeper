package org.shopkeeper.database.modules;

import org.junit.Test;
import org.shopkeeper.database.modules.sqllite.SQLLiteModule;
import org.shopkeeper.subjects.items.Item;
import org.shopkeeper.subjects.modules.ItemModule;
import org.shopkeeper.util.DateTimeGenerator;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 14-12-15.
 */
public class DatabaseModuleTest {

    @Test
    public void testDatabase() throws InterruptedException {
        ItemModule module =  new ItemModule();
        module.add(new Item(null, "testw", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        module.add(new Item(null, "tests", 12.54, DateTimeGenerator.generateDateTimeNow()));
        Thread.sleep(5000);

    }

}