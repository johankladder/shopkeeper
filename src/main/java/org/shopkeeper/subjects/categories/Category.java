package org.shopkeeper.subjects.categories;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.Subject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Category extends Subject {

    public Category(Long identificationNumber, String name, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
    }


    public static Map getFields() {
        return null;
    }

}
