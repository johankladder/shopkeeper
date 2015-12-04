package org.shopkeeper.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectParser {

    // Make sure all the fields are stored here

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

    public static void generateMapForStorage(Integer subjectType) {

    }

    public static Integer parseTypeFromSubject(Subject subject) {
        if(subject instanceof Item) {
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
