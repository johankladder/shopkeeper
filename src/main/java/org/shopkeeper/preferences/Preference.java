package org.shopkeeper.preferences;

/**
 * Created by johankladder on 1/10/16.
 */
public class Preference {

    private String id;
    private String value;

    public Preference(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setValue(String value) {
        if(value != null) {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
