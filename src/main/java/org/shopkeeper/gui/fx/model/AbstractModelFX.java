package org.shopkeeper.gui.fx.model;

import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public abstract class AbstractModelFX {

    public abstract void add(Subject subject);

    public abstract void update(Subject subject);

    public abstract void delete(Subject subject);

    public abstract void setViewPackage(ArrayList<AbstractViewFX> views);

    public abstract ArrayList<Subject> getSubjects();
}
