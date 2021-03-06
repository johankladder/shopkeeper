package org.shopkeeper.subjects.subjecttypes;

import org.joda.time.DateTime;
import org.shopkeeper.subjects.parsers.SubjectParser;

import java.util.Map;

// TODO: Documentation methods!
public abstract class Subject {

    private String name;
    private DateTime dateAdded;
    private Long identificationNumber;
    public Map INIT_FIELD;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public abstract Map getFields();





}
