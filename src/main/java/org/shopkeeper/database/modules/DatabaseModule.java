package org.shopkeeper.database.modules;

import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract  class DatabaseModule {

    protected Integer TYPE;
    protected static final String DBNAME="shopkeeper";

    public DatabaseModule() {
        TYPE = DatabaseParser.parseTypeFromSubject(this);
    }

    public abstract void add(Subject subject);
    public abstract void delete(Subject subject);
    public abstract void update(Subject subject);
    public abstract void showAll(Subject subject);


}
