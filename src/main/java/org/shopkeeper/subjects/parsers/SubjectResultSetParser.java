package org.shopkeeper.subjects.parsers;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.SubjectHandler;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by johankladder on 12/28/15.
 */
public class SubjectResultSetParser {

    /**
     * This method will parse the given result-set to Subject objects. The subject-type is needed because of choosing
     * the right module for the performed parse.
     * @param result The ResultSet from a database-response
     * @param subjectType The subject-type of the response
     */
    public static void parseResultSetToModule(ResultSet result, Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            parseItems(result);
        } else if (subjectType == SubjectTypes.CATEGORY) {
            parseCategories(result);
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            parseCustomers(result);
        }
    }

    private static void parseItems(ResultSet set) {
        try {
            while (set.next()) {
                Item item = new Item(
                        new Long(set.getInt("id")),
                        set.getString("name"),
                        set.getDouble("price"),
                        DateTime.parse(set.getString("dateadded"))
                );
                SubjectHandler.getModule("itemmodule").addToList(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void parseCategories(ResultSet set) {
        try {
            while (set.next()) {
                Category category = new Category(
                        new Long(set.getInt("id")),
                        set.getString("name"),
                        DateTime.parse(set.getString("dateadded"))
                );
                SubjectHandler.getModule("categorymodule").addToList(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void parseCustomers(ResultSet set) {

        try {
            while (set.next()) {
                Customer customer = new Customer(
                        new Long(set.getInt("id")),
                        set.getString("name"),
                        DateTime.parse(set.getString("dateadded")),
                        set.getString("place"),
                        set.getString("address"),
                        set.getString("zipcode"),
                        set.getString("phone"),
                        set.getString("email")
                );
                SubjectHandler.getModule("customermodule").addToList(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
