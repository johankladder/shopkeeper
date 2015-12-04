package org.shopkeeper.parsers;

import org.shopkeeper.subjects.SubjectFields;
import org.shopkeeper.subjects.SubjectTypes;

import java.util.HashMap;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectMapGenerator {

    /**
     * Gets the hashmap with keys according to the subject-type. This map is used for saving form information.
     * The program needs to now which map fits the needs of a certain subject.
     * Will return null if subject-type is not supported
     */
    public static HashMap<String, Object> generateMapForStorage(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            return generateMapItem();
        } else if (subjectType == SubjectTypes.CATEGORY) {
            return generateMapCategories();
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            return generateMapCustomer();
        } else {
            return null;
        }
    }

    // Map generations:
    private static HashMap<String, Object> generateMapItem() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.ITEM_PRICE, null);
        return map;
    }

    private static HashMap<String, Object> generateMapCustomer() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.CUSTOMER_PLACE, null);
        map.put(SubjectFields.CUSTOMER_EMAIL, null);
        map.put(SubjectFields.CUSTOMER_ADDRESS, null);
        map.put(SubjectFields.CUSTOMER_PHONE, null);
        map.put(SubjectFields.CUSTOMER_ZIPCODE, null);
        return map;
    }

    private static HashMap<String, Object> generateMapCategories() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        return map;
    }
}
