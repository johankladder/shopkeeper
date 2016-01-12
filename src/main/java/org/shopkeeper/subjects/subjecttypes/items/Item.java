package org.shopkeeper.subjects.subjecttypes.items;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.util.DateTimeGenerator;
import org.shopkeeper.util.PriceGenerator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Item extends Subject {

    public Double price;
    public static final String TABLENAME = "items";

    public Item(Long identificationNumber, String name, Double price, DateTime dateAdded) {
        super(identificationNumber, name, dateAdded);
        super.INIT_FIELD =getInitFields();
        this.price = price;
    }

    // This method is called from the table:
    public String getPrice() {
        return PriceGenerator.priceToString(price);
    }

    public Double getDoublePrice() {
        return price;
    }

    public Long getId() {
        return getIdentificationNumber();
    }

    // FIXME: Return nicely formatted string
    public String getDateadded() {
        return DateTimeGenerator.dateTimeToString(getDateAdded());
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Map getFields() {

        Map fields = new LinkedHashMap<>();
        fields.put("id", getIdentificationNumber());
        fields.put("name", getName());
        fields.put("price", getDoublePrice());
        fields.put("dateadded", DateTimeGenerator.dateTimeToString(getDateAdded()));
        fields.put("tablename", TABLENAME);
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new LinkedHashMap<>();

        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("price", "double");
        fields.put("dateadded", "date");
        fields.put("tablename", TABLENAME);
        return fields;
    }


}
