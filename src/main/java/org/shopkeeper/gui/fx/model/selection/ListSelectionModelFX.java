package org.shopkeeper.gui.fx.model.selection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 5-1-16.
 */
public class ListSelectionModelFX implements ChangeListener<Object>{

    private static Object SELECTED_SUBJECT = null;
    private static ArrayList<AbstractSelectionViewFX> VIEWS = new ArrayList<>();

    public void setViewPackage(ArrayList<AbstractSelectionViewFX> views) {
        if(views != null) {
            VIEWS = views;
            for (AbstractSelectionViewFX view : views) {
                view.setModel(this);
            }
        }

    }

    public void setSelectedSubject(Object subject) {
        if(subject != null) {
            SELECTED_SUBJECT = subject;
            updateViews();
        }
    }

    public Object getSelectedSubject() {
        return SELECTED_SUBJECT;
    }

    private void updateViews() {
        for(AbstractSelectionViewFX view : VIEWS) {
            view.updateView();
        }
    }

    @Override
    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object selectedSubject) {
        setSelectedSubject(selectedSubject);
    }

}
