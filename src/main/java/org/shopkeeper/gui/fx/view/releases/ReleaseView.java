package org.shopkeeper.gui.fx.view.releases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.shopkeeper.gui.fx.model.ReleaseModel;
import org.shopkeeper.releases.Release;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ReleaseView extends ListView {

    public ReleaseModel model = null;

    public ReleaseView(ReleaseModel model) {
        this.model = model;
    }

    private void init() {

    }

    public void update() {
        ArrayList<String> data = new ArrayList<>();
        for(Release release : model.getReleases()) {
            data.add(release.PREFIX);
        }
        ObservableList names = FXCollections.observableList(data);
        setItems(names);
    }

}
