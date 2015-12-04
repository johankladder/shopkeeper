package org.shopkeeper.subjects.customer;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Customer extends Subject {

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
}
