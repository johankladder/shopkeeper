package org.shopkeeper.gui.swing.model.subjects;

import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.modules.SubjectModule;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CategoryModel extends AbstractModel {

    private static SubjectModule MODULE = null;
    private static ArrayList<AbstractView> views = new ArrayList<>();

    public CategoryModel(SubjectModule module) {
        super(module);
    }


}
