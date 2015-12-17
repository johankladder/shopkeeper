package org.shopkeeper.database.modules;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class DatabaseModule implements Runnable{

    public Integer TYPE;
    protected static final String DBNAME = "shopkeeper";
    protected static boolean CONNECTED = false;

    public DatabaseModule() {
        TYPE = DatabaseParser.parseTypeFromSubject(this);
    }

    public abstract void add(Subject subject);

    public abstract void delete(Subject subject);

    public abstract void update(Subject subject);

    public abstract void showAll(Integer subjectType);

    public static boolean isConnected() {
        return CONNECTED;
    }


}
