package org.shopkeeper.gui.fx.view.releases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.shopkeeper.gui.fx.model.ReleaseModel;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.view.cellrenderers.ReleaseCellRenderer;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;
import org.shopkeeper.releases.Release;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ReleaseView extends ListView {

    public ReleaseModel model = null;
    private static ListSelectionModelFX SELECTION_MODEL = new ListSelectionModelFX();

    public ReleaseView(ReleaseModel model, ArrayList<SelectionViewFX> views) {
        this.model = model;
        SELECTION_MODEL.setViewPackage(views);
        getSelectionModel().selectedItemProperty().addListener(SELECTION_MODEL);

    }


    public void update() {
        setItems(null);
        ArrayList<Release> data = new ArrayList<>();
        for (Release release : model.getReleases()) {
            data.add(release);
        }
        ObservableList list = FXCollections.observableList(data);
        setItems(list);

        setCellFactory(p -> {
            ListCell<Release> cell = new ReleaseCellRenderer();
            return cell;
        });
    }

}
