package org.shopkeeper.parsers;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

import java.util.HashMap;
import java.util.jar.Pack200;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectParser {

    // Make sure all the fields are stored here when adding a new subject:
    // GENERAL
    public static final String IDNUMBER = "id_number_general";
    public static final String NAME = "name_general";

    // ITEM:
    public static final String ITEM_PRICE = "item_price";

    // CUSTOMER:
    public static final String CUSTOMER_PLACE = "customer_place";
    public static final String CUSTOMER_ADDRESS = "customer_address";
    public static final String CUSTOMER_ZIPCODE = "customer_zipcode";
    public static final String CUSTOMER_PHONE = "customer_phone";
    public static final String CUSTOMER_EMAIL = "customer_email";

    // CATEGORIES:
    // Only has general fields:

    /**
     * Gets the hashmap with keys according to the subject-type. Will return null if subject-type is not supported
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
    private static HashMap generateMapItem() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(IDNUMBER, null);
        map.put(NAME, null);
        map.put(ITEM_PRICE, null);
        return map;
    }

    private static HashMap generateMapCustomer() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(IDNUMBER, null);
        map.put(NAME, null);
        map.put(CUSTOMER_PLACE, null);
        map.put(CUSTOMER_EMAIL, null);
        map.put(CUSTOMER_ADDRESS, null);
        map.put(CUSTOMER_PHONE, null);
        map.put(CUSTOMER_ZIPCODE, null);
        return map;
    }

    private static HashMap generateMapCategories() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // Put the fields in it:
        map.put(IDNUMBER, null);
        map.put(NAME, null);
        return map;
    }


    public static Integer parseTypeFromSubject(Subject subject) {
        if (subject instanceof Item) {
            return SubjectTypes.ITEM;
        } else if (subject instanceof Category) {
            return SubjectTypes.CATEGORY;
        } else if (subject instanceof Customer) {
            return SubjectTypes.CUSTOMER;
        } else {
            return null;
        }
    }

}
