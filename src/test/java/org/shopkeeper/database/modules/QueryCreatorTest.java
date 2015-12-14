package org.shopkeeper.database.modules;

import org.joda.time.DateTime;
import org.junit.Test;
import org.shopkeeper.database.modules.queries.QueryCreator;
import org.shopkeeper.subjects.items.Item;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 14-12-15.
 */
public class QueryCreatorTest {

    @Test
    public void testSQLLITE() {
        Item item = new Item(new Long(1234), "testItem", 12.31, new DateTime(1,2,3,4,5));
        assertEquals("", QueryCreator.createInitQuery(item.getFields(), DatabaseTypes.DATABASETYPE_SQLLITE));
    }

}