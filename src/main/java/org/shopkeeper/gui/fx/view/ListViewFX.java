package org.shopkeeper.gui.fx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.shopkeeper.gui.fx.model.AbstractModelFX;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.subjecttypes.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListViewFX extends javafx.scene.control.ListView implements AbstractViewFX {

    private AbstractModelFX MODEL = null;

    public ListViewFX(AbstractModelFX model) {
        MODEL = model;
        setPrefSize(Preference.PREF_WIDTH_LIST_CONTAINERS, Preference.PREF_HEIGHT_LIST_CONTAINERS);
        setMaxWidth(Preference.PREF_WIDTH_LIST_CONTAINERS); // TODO Needs to be double for some reason
    }

    @Override
    public void updateView() {
        setItems(null);
        List<String> list = new ArrayList<>();
        for (Subject subject : MODEL.getSubjects()) {
            list.add(subject.getName());
        }
        ObservableList<String> names = FXCollections.observableArrayList(list);
        setItems(names);
    }
}
