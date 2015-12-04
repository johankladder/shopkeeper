package org.shopkeeper.subjects;

import org.joda.time.DateTime;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class Subject {

    private String name;
    private DateTime dateAdded;
    private Long identificationNumber;

    public Subject(Long identificationNumber, String name, DateTime dateAdded) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.dateAdded = dateAdded;
    }

    public DateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(DateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }




}
