package org.shopkeeper.gui.fx.model;

import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.subjects.modules.SubjectModule;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ItemModelFX extends AbstractModelFX {

    private static SubjectModule MODULE = null;
    private static ArrayList<AbstractViewFX> views = new ArrayList<>();

    public ItemModelFX(SubjectModule module) {
        MODULE = module;
    }

    @Override
    public void add(Subject subject) {
        updateViews();
    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void setViewPackage(ArrayList<AbstractViewFX> views) {
        ItemModelFX.views = views;
    }

    @Override
    public ArrayList<Subject> getSubjects() {
        return MODULE.getModuleSubjects();
    }

    public void updateViews() {
        for (AbstractViewFX view : views) {
            view.updateView();
        }
    }


}
