package org.shopkeeper.gui.fx.model;

import javafx.application.Platform;
import org.shopkeeper.gui.fx.view.releases.ReleaseView;
import org.shopkeeper.releases.Release;
import org.shopkeeper.releases.ReleaseModule;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ReleaseModel {

    private static ReleaseView view = null;
    private static ReleaseModule module = null;

    public ReleaseModel(ReleaseModule module ) {
        this.module = module;
    }


    public void setReleaseView(ReleaseView view) {
        this.view = view;
    }

    public ArrayList<Release> getReleases() {
        return module.RELEASES;
    }

    public void updateViews() {
        Platform.runLater(() -> view.update());

    }
}
