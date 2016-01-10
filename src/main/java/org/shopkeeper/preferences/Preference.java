package org.shopkeeper.preferences;

import java.util.ArrayList;

/**
 * Created by johankladder on 1/10/16.
 */
public class Preference {

    private String id;
    private String value;
    private ArrayList<String> validFields;

    public Preference(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setValue(String value) {
        if(value != null) {
            this.value = value;
        }
    }

    public void setValidFields(ArrayList<String> validFields) {
        this.validFields = validFields;
    }

    public ArrayList<String> getValidFields() {
        return validFields;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
