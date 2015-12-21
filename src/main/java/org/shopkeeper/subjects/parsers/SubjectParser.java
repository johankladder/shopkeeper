package org.shopkeeper.subjects.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;

import java.util.logging.Logger;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectParser {
    private final static Logger LOGGER = Logger.getLogger(SubjectParser.class.getName());

    public static Integer parseTypeFromSubject(Subject subject) {
        if (subject instanceof Item) {
            return SubjectTypes.ITEM;
        } else if (subject instanceof Category) {
            return SubjectTypes.CATEGORY;
        } else if (subject instanceof Customer) {
            return SubjectTypes.CUSTOMER;
        } else {
            LOGGER.warning("Subject can not be found!");
            return null;
        }
    }

}
