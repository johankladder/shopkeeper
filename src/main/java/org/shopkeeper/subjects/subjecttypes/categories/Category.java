package org.shopkeeper.subjects.subjecttypes.categories;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Category extends Subject {
    public static final String TABLENAME = "categories";
    public Category(Long identificationNumber, String name, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
        super.INIT_FIELD =getInitFields();
    }

    public Long getId() {
        return getIdentificationNumber();
    }

    public String getDateadded() {
        return DateTimeGenerator.dateTimeToString(getDateAdded());
    }


    @Override
    public Map getFields() {
        Map fields = new LinkedHashMap<>();
        fields.put("id", getIdentificationNumber());
        fields.put("name", getName());
        fields.put("dateadded", getDateAdded());
        fields.put("tablename", TABLENAME);
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new LinkedHashMap();
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        fields.put("tablename", TABLENAME);
        return fields;
    }

}
