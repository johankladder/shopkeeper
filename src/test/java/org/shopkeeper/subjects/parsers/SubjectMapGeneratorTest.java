package org.shopkeeper.subjects.parsers;

import java.util.Map;
import org.junit.Test;
import org.shopkeeper.subjects.subjecttypes.SubjectFields;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

import static org.junit.Assert.*;

/**
 * Created by typhooncoaster on 12-1-16.
 */
public class SubjectMapGeneratorTest {

    @Test
    public void testGenerateEditableMapSubject() throws Exception {

        Map map = SubjectMapGenerator.generateEditableMapSubject(SubjectTypes.ITEM);

        assertTrue(map.containsKey(SubjectFields.NAME));
        assertTrue(map.containsKey(SubjectFields.ITEM_PRICE));
        assertFalse(map.containsKey(SubjectFields.IDNUMBER));
        assertFalse(map.containsKey("dateadded"));

        map = SubjectMapGenerator.generateEditableMapSubject(SubjectTypes.CATEGORY);
        assertTrue(map.containsKey(SubjectFields.NAME));
        assertFalse(map.containsKey(SubjectFields.IDNUMBER));
        assertFalse(map.containsKey("dateadded"));

        map = SubjectMapGenerator.generateEditableMapSubject(SubjectTypes.CUSTOMER);
        assertTrue(map.containsKey(SubjectFields.NAME));
        assertTrue(map.containsKey(SubjectFields.CUSTOMER_EMAIL));
        assertTrue(map.containsKey(SubjectFields.CUSTOMER_ZIPCODE));
        assertTrue(map.containsKey(SubjectFields.CUSTOMER_PLACE));
        assertTrue(map.containsKey(SubjectFields.CUSTOMER_ADDRESS));
        assertTrue(map.containsKey(SubjectFields.CUSTOMER_PHONE));

        assertFalse(map.containsKey(SubjectFields.IDNUMBER));
        assertFalse(map.containsKey("dateadded"));


    }
}