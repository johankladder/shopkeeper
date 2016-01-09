package org.shopkeeper.gui.fx.model.selection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;

import java.util.ArrayList;

/**
 * This class can be used for communicating with other views. I.e when using a list, this class can be set as an
 * change-listener. When views are added to this class, this model will call the update view method when the user is
 * firring an event in the list.
 *
 * @see SelectionViewFX
 */
public class ListSelectionModelFX implements ChangeListener<Object>{

    private static Object SELECTED_SUBJECT = null;
    private static ArrayList<SelectionViewFX> VIEWS = new ArrayList<>();

    public void setViewPackage(ArrayList<SelectionViewFX> views) {
        if(views != null) {
            VIEWS = views;
            for (SelectionViewFX view : views) {
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
        for(SelectionViewFX view : VIEWS) {
            view.updateView();
        }
    }

    @Override
    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object selectedSubject) {
        setSelectedSubject(selectedSubject);
    }

}
