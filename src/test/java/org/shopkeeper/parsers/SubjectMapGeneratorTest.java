package org.shopkeeper.parsers;

import org.junit.Test;
import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.items.Item;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectMapGeneratorTest {

    @Test
    public void testMap() {
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

    }

}