package org.shopkeeper.subjects.parsers;

import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;

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
