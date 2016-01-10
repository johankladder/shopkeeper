package org.shopkeeper.subjects.parsers;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        Double now = item.getDoublePrice();
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

    @Test
    public void testUserMap() {
        Item item = new Item(new Long(1), "name", 12.31, DateTimeGenerator.generateDateTimeNow());
        Customer cus = new Customer(new Long(1), "name", DateTimeGenerator.generateDateTimeNow(), "","" ,"","","");
        Category cat = new Category(new Long(1), "name", DateTimeGenerator.generateDateTimeNow());

        ArrayList<Subject> s = new ArrayList<>();
        s.add(item);
        s.add(cus);
        s.add(cat);

        for(Subject su : s) {
            testGeneratedUserMap(su);
        }


    }

    private void testGeneratedUserMap(Subject subject) {
        final boolean[] hit = {false};
        Map userViewMap = SubjectMapGenerator.createUserViewMap(subject.getFields());

        userViewMap.forEach((k,v) -> {
            if(StringUtils.contains((String) k, "tablename")) {
                hit[0] = true;
            }
        });

        assertFalse(hit[0]);
    }


    @Test
    public void testUpdateSubjectWithMap() throws Exception {
        Map map = new HashMap<>();
        DateTime time = DateTimeGenerator.generateDateTimeNow();
        Item item = new Item(new Long(1), "name", 12.31, time);
        map.put(SubjectFields.IDNUMBER, "1");
        map.put(SubjectFields.NAME, "test");
        map.put(SubjectFields.ITEM_PRICE, "12.50");
        map.put("dateadded", time);

        Subject subject = SubjectMapGenerator.updateSubjectWithMap(item, map);
        item = (Item) subject;

        long id = item.getId();
        double price = item.getDoublePrice();
        assertEquals(1,id);
        assertEquals("test", item.getName());
        assertEquals(12.50,price, 0.0001);


        Category cat = new Category(new Long(1),"test", time);
        map = new HashMap<>();
        map.put(SubjectFields.IDNUMBER, "1");
        map.put(SubjectFields.NAME, "testname");
        map.put("dateadded", time);
        cat = (Category) SubjectMapGenerator.updateSubjectWithMap(cat, map);

        assertEquals("testname", cat.getName());


        Customer customer = new Customer(new Long(1),"test", time, "place", "address", "zipcode", "phone", "email");
        map = new HashMap<>();
        map.put(SubjectFields.IDNUMBER, "1");
        map.put(SubjectFields.NAME, "testname");
        map.put("dateadded", time);
        map.put(SubjectFields.CUSTOMER_ZIPCODE, "testzipcode");
        map.put(SubjectFields.CUSTOMER_EMAIL, "testemail");
        customer = (Customer) SubjectMapGenerator.updateSubjectWithMap(customer, map);

        assertEquals("place", customer.getPlaceOfLiving());
        assertEquals("testemail", customer.getEmail());
        assertEquals("testzipcode", customer.getZipcode());
        assertEquals("testname", customer.getName());
    }
}