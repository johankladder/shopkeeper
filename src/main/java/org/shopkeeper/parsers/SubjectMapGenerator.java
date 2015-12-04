package org.shopkeeper.parsers;

import org.shopkeeper.subjects.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.SubjectTypes;

import java.util.HashMap;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectMapGenerator {

    // MAP IDS:
    public static final String MAP_ITEM_ID = "item_map_id=" + SubjectTypes.ITEM;
    public static final String MAP_CUSTOMER_ID = "customer_map_id=" + SubjectTypes.CUSTOMER;
    public static final String MAP_CATEGORY_ID = "category_map_id=" + SubjectTypes.CATEGORY;

    /**
     * Gets the hashmap with keys according to the subject-type. This map is used for saving form information.
     * The program needs to now which map fits the needs of a certain subject. Make sure you only change the fields that
     * meant to be changed. Fields are avalaible in SubjectField's class.
     * Will return null if subject-type is not supported
     *
     * @see SubjectFields
     */
    public static HashMap<String, String> generateMapForStorage(Integer subjectType) {
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
    private static HashMap<String, String> generateMapItem() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.ITEM_PRICE, null);
        map.put(MAP_ITEM_ID, null);
        return map;
    }

    private static HashMap<String, String> generateMapCustomer() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.CUSTOMER_PLACE, null);
        map.put(SubjectFields.CUSTOMER_EMAIL, null);
        map.put(SubjectFields.CUSTOMER_ADDRESS, null);
        map.put(SubjectFields.CUSTOMER_PHONE, null);
        map.put(SubjectFields.CUSTOMER_ZIPCODE, null);
        map.put(MAP_CUSTOMER_ID, null);
        return map;
    }

    private static HashMap<String, String> generateMapCategories() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(MAP_CATEGORY_ID, null);
        return map;
    }


    // MAP-PARSER:
    public static void createWithMap(HashMap<String, String> map) {
        if (map.containsKey(MAP_ITEM_ID)) {
            SubjectManipulator.createItem(map);
        } else if (map.containsKey(MAP_CUSTOMER_ID)) {
           // SubjectManipulator.create(map, SubjectTypes.CUSTOMER);
        } else if (map.containsKey(MAP_CATEGORY_ID)) {
            //SubjectManipulator.create(map, SubjectTypes.CATEGORY);
        } else {
            // Do nothing, map was not valid
        }
    }

    public static void updateWithMap(HashMap<String, Object> map) {

    }


}
