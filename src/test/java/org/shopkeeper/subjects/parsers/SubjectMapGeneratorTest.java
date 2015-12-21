package org.shopkeeper.subjects.parsers;

import org.junit.Test;
import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectMapGeneratorTest {

    @Test
    public void testMap() throws InterruptedException {
        HashMap<String, String> map = SubjectMapGenerator.generateMapForStorage(SubjectTypes.ITEM);
        assertNotNull(map);

        boolean status;
        if (map.containsKey(SubjectMapGenerator.MAP_ITEM_ID)) {
            status = true;
        } else {
            status = false;
        }

        assertTrue(status);

        //Make an object:
        map.put(SubjectFields.IDNUMBER, "111");
        map.put(SubjectFields.NAME, "testname");
        map.put(SubjectFields.ITEM_PRICE, "12.51");

        Subject subject = SubjectManipulator.createItem(map);
        assertNotNull(subject);

        status = false;
        if (subject instanceof Item) {
            status = true;
        } else {
            status = false;
        }
        assertTrue(status);

        // Cast ->
        Item item = (Item) subject;
        assertEquals("testname", item.getName());
        Double actual = 12.51;
        Double now = item.getPrice();
        assertEquals(actual, now, 1);

        Long id = new Long(111);
        Long nowId = item.getIdentificationNumber();
        assertEquals(id, nowId);

        SubjectManipulator.add(item);
        Thread.sleep(5000);

    }

    @Test
    public void testCategory() {
        HashMap<String, String> map = SubjectMapGenerator.generateMapForStorage(SubjectTypes.CATEGORY);
        assertNotNull(map);

        boolean status;
        if (map.containsKey(SubjectMapGenerator.MAP_CATEGORY_ID)) {
            status = true;
        } else {
            status = false;
        }

        assertTrue(status);

        //Make an object:
        map.put(SubjectFields.IDNUMBER, "111");
        map.put(SubjectFields.NAME, "testname");

        Subject subject = SubjectManipulator.createCategory(map);
        assertNotNull(subject);

        status = false;
        if (subject instanceof Category) {
            status = true;
        } else {
            status = false;
        }
        assertTrue(status);

        // Cast ->
        Category item = (Category) subject;
        assertEquals("testname", item.getName());

        Long id = new Long(111);
        Long nowId = item.getIdentificationNumber();
        assertEquals(id, nowId);
    }

    @Test
    public void testCustomer() {
        HashMap<String, String> map = SubjectMapGenerator.generateMapForStorage(SubjectTypes.CUSTOMER);
        assertNotNull(map);

        boolean status;
        if (map.containsKey(SubjectMapGenerator.MAP_CUSTOMER_ID)) {
            status = true;
        } else {
            status = false;
        }

        assertTrue(status);

        //Make an object:
        map.put(SubjectFields.IDNUMBER, "111");
        map.put(SubjectFields.NAME, "testname");
        map.put(SubjectFields.CUSTOMER_EMAIL, "testemail");
        map.put(SubjectFields.CUSTOMER_PHONE, "010101");
        // map.put(SubjectFields.CUSTOMER_PLACE, "testplace"); // Prepare null check
        map.put(SubjectFields.CUSTOMER_ADDRESS, "testaddress");
        map.put(SubjectFields.CUSTOMER_ZIPCODE, "11 as");

        Subject subject = SubjectManipulator.createCustomer(map);
        assertNotNull(subject);

        status = false;
        if (subject instanceof Customer) {
            status = true;
        } else {
            status = false;
        }
        assertTrue(status);

        // Cast ->
        Customer item = (Customer) subject;
        assertEquals("testname", item.getName());

        Long id = new Long(111);
        Long nowId = item.getIdentificationNumber();
        assertEquals(id, nowId);
        assertEquals("testemail", item.getEmail());
        assertEquals("testaddress", item.getAddress());
        assertEquals("010101", item.getPhone());
        assertEquals(null, item.getPlaceOfLiving());
        assertEquals("11 as", item.getZipcode());
    }


}