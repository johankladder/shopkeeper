package org.shopkeeper.subjects.parsers;

import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

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
                System.out.println("test items");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void parseCategories(ResultSet set) {
        try {
            while(set.next()) {
                System.out.println("test categories");
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
