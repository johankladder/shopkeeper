package org.shopkeeper.gui.fx.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.subjecttypes.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListViewFX extends javafx.scene.control.ListView implements AbstractViewFX {

    private AbstractModelFX MODEL = null;
    private static ListSelectionModelFX SELECTION_MODEL = new ListSelectionModelFX();

    public ListViewFX(AbstractModelFX model, ArrayList<AbstractSelectionViewFX> views) {
        MODEL = model;
        setPrefSize(Preference.PREF_WIDTH_LIST_CONTAINERS, Preference.PREF_HEIGHT_LIST_CONTAINERS);
        setMaxWidth(Preference.PREF_WIDTH_LIST_CONTAINERS); // TODO Needs to be double for some reason
        SELECTION_MODEL.setViewPackage(views);
        getSelectionModel().selectedItemProperty().addListener(SELECTION_MODEL);
    }

    @Override
    public void updateView() {
        setItems(null);
        List<Subject> list = new ArrayList<>();
        for (Subject subject : MODEL.getSubjects()) {
            list.add(subject);
        }
        ObservableList<Subject> names = FXCollections.observableArrayList(list);
        setItems(names);
    }
}
