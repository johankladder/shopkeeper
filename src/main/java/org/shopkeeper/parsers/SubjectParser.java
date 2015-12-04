package org.shopkeeper.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectParser {


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
