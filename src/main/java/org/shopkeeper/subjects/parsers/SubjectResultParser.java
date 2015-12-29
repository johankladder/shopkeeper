package org.shopkeeper.subjects.parsers;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.SubjectHandler;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.items.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by johankladder on 12/28/15.
 */
public class SubjectResultParser {

    public static void addResultToListModule(ResultSet result, Integer subjectType) {
        if(subjectType == SubjectTypes.ITEM) {
            parseItems(result);
        } else if (subjectType == SubjectTypes.CATEGORY) {
            parseCategories(result);
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            parseCustomers(result);
        }
    }

    private static void parseItems(ResultSet set) {
        try {
            while(set.next()) {
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
            while(set.next()) {
                Category category = new Category(
                        new Long(set.getInt("id")),
                        set.getString("name"),
                        DateTime.parse(set.getString("dateadded"))
                );
                SubjectHandler.getModule("categorymodule").addToList(category);
                System.out.println("test");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void parseCustomers(ResultSet set) {

        try {
            while(set.next()) {
                System.out.println("test customers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
