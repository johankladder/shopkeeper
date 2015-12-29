package org.shopkeeper.gui.swing.model;

import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public abstract class AbstractModel {

    public abstract void add(Subject subject);

    public abstract void update(Subject subject);

    public abstract void delete(Subject subject);

    public abstract void setViewPackage(ArrayList<AbstractView> views);

    public abstract ArrayList<Subject> getSubjects();
}
