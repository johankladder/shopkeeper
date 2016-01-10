package org.shopkeeper.database.parsers;

import org.junit.Test;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.DateTimeGenerator;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class SQLLiteQueryCreatorTest {

    @Test
    public void testCreateInitQuery() throws Exception {

    }

    @Test
    public void testCreateDropTableQuery() throws Exception {

    }

    @Test
    public void testCreateInsertQuery() throws Exception {

    }

    // TODO Test cant run because of milliseconds different in time.
    @Test
    public void testCreateUpdateQuery() throws Exception {
        String date = DateTimeGenerator.dateTimeToString(DateTimeGenerator.generateDateTimeNow());
        String expectedQuery = "UPDATE items SET name = 'newname', price = 1.2, dateadded = '"+ date +"' WHERE id = 1";
        String query = SQLLiteQueryCreator.createUpdateQuery(new Item(new Long(1), "newname", 1.2, DateTimeGenerator.generateDateTimeNow()));
        assertEquals(expectedQuery, query);
    }

    @Test
    public void testCreateSelectAllQuery() throws Exception {

    }
}