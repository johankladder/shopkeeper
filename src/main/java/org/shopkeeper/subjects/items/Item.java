package org.shopkeeper.subjects.items;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class Item extends Subject {

    public Double price;

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







}
