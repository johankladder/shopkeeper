package org.shopkeeper.subjects.items;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Item extends Subject {

    public Double price;
    public static final String TABLENAME = "items";

    public Item(Long identificationNumber, String name, Double price, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
        this.price = price;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // TODO Make abstract
    public static Map getFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("price", "double");
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        return fields;
    }
}
