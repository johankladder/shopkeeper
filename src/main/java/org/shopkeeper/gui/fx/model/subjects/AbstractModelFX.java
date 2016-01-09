package org.shopkeeper.gui.fx.model.subjects;

import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.subjects.modules.SubjectModule;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public abstract class AbstractModelFX {

    private SubjectModule MODULE = null;
    private ArrayList<AbstractViewFX> views = null;

    public AbstractModelFX(SubjectModule module) {
        MODULE = module;
    }

    public void add(Subject subject) {
        
    }

    public void update(Subject subject){
        MODULE.update(subject);
    }

    public void delete(Subject subject) {

    }


    public void setViewPackage(ArrayList<AbstractViewFX> views) {
        this.views = views;
    }

    public  ArrayList<Subject> getSubjects() {
        return MODULE.getModuleSubjects();
    }

    public void updateViews() {
        for (AbstractViewFX view : views) {
            view.updateView();
        }
    }
}
