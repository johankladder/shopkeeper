package org.shopkeeper.database.modules;

import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class DatabaseModule {

    public Integer TYPE;
    protected static final String DBNAME = "shopkeeper";
    protected static boolean CONNECTED = false;

    public DatabaseModule() {
        TYPE = DatabaseParser.parseTypeFromSubject(this);
    }

    public abstract void add(Subject subject);

    public abstract void delete(Subject subject);

    public abstract void update(Subject subject);

    public abstract void showAll(Subject subject);

    /**
     * Shows whether the database is connected. When connected it will return true, if not it will return false.
     *
     * @return Status of connection
     */
    public static boolean isConnected() {
        return CONNECTED;
    }


}
