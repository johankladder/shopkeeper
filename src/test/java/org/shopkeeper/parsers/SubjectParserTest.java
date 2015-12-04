package org.shopkeeper.parsers;

import org.joda.time.DateTime;
import org.junit.Test;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;
import org.shopkeeper.subjects.SubjectFields;

import java.util.HashMap;

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

    @Test
    public void testHashMapGetter() {
        HashMap<String, String> map = SubjectMapGenerator.generateMapForStorage(SubjectTypes.ITEM);
        boolean status;
        if (map.containsKey(SubjectFields.ITEM_PRICE) && map.containsKey(SubjectFields.NAME) && map.containsKey(SubjectFields.IDNUMBER)
                && !map.containsKey(SubjectFields.CUSTOMER_ADDRESS)) {
            status = true;
        } else {
            status = false;
        }


        HashMap<String, String> map2 = SubjectMapGenerator.generateMapForStorage(SubjectTypes.CUSTOMER);
        boolean status2;
        if (!map2.containsKey(SubjectFields.ITEM_PRICE) && map2.containsKey(SubjectFields.CUSTOMER_ADDRESS) && map2.containsKey(SubjectFields.CUSTOMER_EMAIL)
                && map2.containsKey(SubjectFields.NAME)) {
            status2 = true;
        } else {
            status2 = false;
        }
        assertTrue(status2);
    }

}