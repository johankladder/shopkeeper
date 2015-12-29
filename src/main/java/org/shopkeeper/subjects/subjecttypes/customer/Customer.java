package org.shopkeeper.subjects.subjecttypes.customer;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.HashMap;
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
        super.INITFIELD=getInitFields();
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
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("id", getIdentificationNumber());
        fields.put("name", getName());
        fields.put("dateadded", getDateAdded());
        fields.put("place", getPlaceOfLiving());
        fields.put("address", getAddress());
        fields.put("zipcode", getZipcode());
        fields.put("phone", getPhone());
        fields.put("email", getEmail());
        return fields;
    }

    public static Map getInitFields() {
        Map fields = new HashMap();
        fields.put("tablename", TABLENAME);
        fields.put("id", "integer");
        fields.put("name", "string");
        fields.put("dateadded", "date");
        fields.put("place", "string");
        fields.put("address", "string");
        fields.put("zipcode", "string");
        fields.put("phone", "string");
        fields.put("email", "string");
        return fields;
    }
}
