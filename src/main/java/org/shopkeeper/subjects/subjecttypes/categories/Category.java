package org.shopkeeper.subjects.subjecttypes.categories;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.HashMap;
import java.util.Map;


public class Category extends Subject {
    public static final String TABLENAME = "categories";
    public Category(Long identificationNumber, String name, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
        super.INIT_FIELD =getInitFields();
    }


    @Override
    public Map getFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("id", getIdentificationNumber());
        fields.put("name", getName());
        fields.put("dateadded", getDateAdded());
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        return fields;
    }

}
