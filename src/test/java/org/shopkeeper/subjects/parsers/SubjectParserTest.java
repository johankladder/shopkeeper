package org.shopkeeper.subjects.parsers;

import org.joda.time.DateTime;
import org.junit.Test;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;


import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectParserTest {

    @Test
    public void testParser() {
        Item item = new Item(new Long(1111), "Test", 12.0, new DateTime(1, 1, 1, 1, 1));
        int testcase = SubjectTypes.ITEM;
        int actual = SubjectParser.parseTypeFromSubject(item);
        int not = SubjectTypes.CUSTOMER;
        assertEquals(testcase, actual);
        assertNotEquals(not, actual);

        Customer c = new Customer(new Long(1111), "Test", new DateTime(1, 1, 1, 1, 1), null, null, null, null, null);
        testcase = SubjectTypes.CUSTOMER;
        actual = SubjectParser.parseTypeFromSubject(c);
        not = SubjectTypes.ITEM;
        assertEquals(testcase, actual);
        assertNotEquals(not, actual);

        Category ca = new Category(new Long(1111), "Test", new DateTime(1, 1, 1, 1, 1));
        testcase = SubjectTypes.CATEGORY;
        actual = SubjectParser.parseTypeFromSubject(ca);
        not = SubjectTypes.ITEM;
        assertEquals(testcase, actual);
        assertNotEquals(not, actual);
    }

}