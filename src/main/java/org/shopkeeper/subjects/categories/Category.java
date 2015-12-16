package org.shopkeeper.subjects.categories;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Category extends Subject {
    public static final String TABLENAME = "categories";
    public Category(Long identificationNumber, String name, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
    }


    @Override
    public Map getFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("price", "double");
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        return fields;
    }

}
