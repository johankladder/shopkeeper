package org.shopkeeper.gui.swing.model;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.modules.SubjectModule;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ItemModel extends AbstractModel {

    private static SubjectModule MODULE = null;
    private static ArrayList<AbstractView> views = new ArrayList<>();

    public ItemModel(SubjectModule module) {
        MODULE = module;
    }

    @Override
    public void add(Subject subject) {

    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void setViewPackage(ArrayList<AbstractView> views) {
        ItemModel.views = views;
    }

    @Override
    public ArrayList<Subject> getSubjects() {
        return MODULE.getModuleSubjects();
    }

    public void updateViews() {
        for (AbstractView view : views) {
            view.updateView();
        }
    }


}
