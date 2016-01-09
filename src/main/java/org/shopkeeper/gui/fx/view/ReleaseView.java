package org.shopkeeper.gui.fx.view;

import javafx.scene.control.ListView;
import org.shopkeeper.gui.fx.model.ReleaseModel;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ReleaseView extends ListView {

    public ReleaseModel model = null;

    public ReleaseView(ReleaseModel model) {
        this.model = model;
    }

    public void update() {

    }

}
