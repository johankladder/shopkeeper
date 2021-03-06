package org.shopkeeper.subjects.subjecttypes.customer;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Customer extends Subject {

    public static final String TABLENAME = "customers";

    private String placeOfLiving;
    private String address;
    private String zipcode;
    private String phone;
    private String email;


    public Customer(Long identificationNumber, String name, DateTime dateAdded, String placeOfLiving, String address,
                    String zipcode, String phone, String email) {
        super(identificationNumber, name, dateAdded);
        this.placeOfLiving = placeOfLiving;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
        super.INIT_FIELD =getInitFields();
    }

    public Long getId() {
        return getIdentificationNumber();
    }

    public String getDateadded() {
        return DateTimeGenerator.dateTimeToString(getDateAdded());
    }

    public String getPlaceOfLiving() {
        return placeOfLiving;
    }

    public void setPlaceOfLiving(String placeOfLiving) {
        this.placeOfLiving = placeOfLiving;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public  Map getFields() {
        Map fields = new LinkedHashMap();
        fields.put("id", getIdentificationNumber());
        fields.put("name", getName());
        fields.put("place", getPlaceOfLiving());
        fields.put("address", getAddress());
        fields.put("zipcode", getZipcode());
        fields.put("phone", getPhone());
        fields.put("email", getEmail());
        fields.put("dateadded", getDateAdded());
        fields.put("tablename", TABLENAME);
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new LinkedHashMap();
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("place", "string");
        fields.put("address", "string");
        fields.put("zipcode", "string");
        fields.put("phone", "string");
        fields.put("email", "string");
        fields.put("dateadded", "date");
        fields.put("tablename", TABLENAME);
        return fields;
    }
}
