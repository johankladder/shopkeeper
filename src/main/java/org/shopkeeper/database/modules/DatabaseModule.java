package org.shopkeeper.database.modules;

import org.shopkeeper.subjects.subjecttypes.Subject;
/**
 * The Database-module gives the user the opportunity the communicate with databases. This can be a wide range of
 * database-types whom can be found in the DatabaseTypes class. Make sure that you call the start method first, otherwise
 * the requests to the database won't work.
 *
 * @see DatabaseTypes
 */
public abstract class DatabaseModule implements Runnable {

    public Integer TYPE;    // The type of the module
    protected static final String DB_NAME = "shopkeeper";    // The database-name
    protected static boolean CONNECTED = false; // Connected state of the database

    public DatabaseModule() {
        TYPE = DatabaseParser.parseTypeFromSubject(this);
    }

    /**
     * Adds the given subject to the module's database. The subject gets checked in in the query-creator and the query
     * will not be executed if the subject is null or doesn't have the requirements for making the query.
     *
     * @param subject The subject who the user likes to be injected into the database
     */
    public abstract void add(Subject subject);

    public abstract void delete(Subject subject);

    public abstract void update(Subject subject);

    public abstract void showAll(Integer subjectType);

    /**
     * Returns whether this module is connected to the database. Nothing more, nothing less.
     *
     * @return The connection-state
     */
    public static boolean isConnected() {
        return CONNECTED;
    }

    public abstract void initQuery(String query);


}
