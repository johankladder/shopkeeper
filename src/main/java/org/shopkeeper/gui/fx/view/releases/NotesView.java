package org.shopkeeper.gui.fx.view.releases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.releases.Release;

import java.util.ArrayList;


/**
 * Created by typhooncoaster on 9-1-16.
 */
public class NotesView extends ListView implements AbstractSelectionViewFX {

    ListSelectionModelFX model = null;
    public NotesView() {

    }

    @Override
    public void setModel(ListSelectionModelFX model) {
        this.model = model;
    }

    @Override
    public void updateView() {
        Release release = (Release) model.getSelectedSubject();

        // Get all the notes from the release:
        ArrayList<String> data = new ArrayList<>();
        for(String note : release.notes) {
            data.add(note);
        }

        ObservableList list = FXCollections.observableList(data);
        setItems(list);
    }
}
