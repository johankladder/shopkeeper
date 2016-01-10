package org.shopkeeper.gui.fx.view.cellrenderers;

import javafx.scene.control.ListCell;
import org.shopkeeper.releases.Release;

/**
 * Created by johankladder on 1/10/16.
 */
public class ReleaseCellRenderer extends ListCell<Release> {

    @Override
    protected void updateItem(Release r, boolean bln) {
        super.updateItem(r, bln);
        if (r != null) {
            setText("Version: " + r.PREFIX);
        }
    }

}
