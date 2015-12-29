package org.shopkeeper.gui.swing.model;

import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.modules.SubjectModule;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public abstract class AbstractModel {

    private SubjectModule MODULE = null;
    private ArrayList<AbstractView> views = null;

    public AbstractModel(SubjectModule module) {
        MODULE = module;
    }

    public void add(Subject subject) {

    }

    public void update(Subject subject) {

    }

    public void delete(Subject subject) {

    }

    public void setViewPackage(ArrayList<AbstractView> views) {
        this.views = views;
    }

    public ArrayList<Subject> getSubjects() {
        return MODULE.getModuleSubjects();
    }

    public void updateViews() {
        for (AbstractView view : views) {
            view.updateView();
        }
    }
}
