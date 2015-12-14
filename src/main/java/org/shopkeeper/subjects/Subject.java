package org.shopkeeper.subjects;

import org.joda.time.DateTime;
import org.shopkeeper.parsers.SubjectParser;

import java.util.Map;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class Subject {

    private String name;
    private DateTime dateAdded;
    private Long identificationNumber;

    public Integer TYPE;

    public Subject(Long identificationNumber, String name, DateTime dateAdded) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.dateAdded = dateAdded;
        TYPE = SubjectParser.parseTypeFromSubject(this);
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

    public String getName() { return name; }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }




}
