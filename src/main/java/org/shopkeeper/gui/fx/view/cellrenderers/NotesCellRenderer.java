package org.shopkeeper.gui.fx.view.cellrenderers;

import javafx.scene.control.ListCell;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class NotesCellRenderer extends ListCell<String> {

    static final String ADD_STYLE = "-fx-text-fill: rgb(49, 89, 23);";
    static final String BUG_STYLE = "-fx-text-fill: rgb(145, 0, 0);";
    static final String FIX_STYLE = "-fx-text-fill: rgb(9, 0, 141);";


    @Override
    protected void updateItem(String s, boolean bln) {
        super.updateItem(s, bln);
        setStyle(null); // Reset style
        if (s != null) {
            setText(s);
            if(StringUtils.contains(s, "ADD:")) {
                setStyle(ADD_STYLE);
            }
            if(StringUtils.contains(s, "BUG:")) {
                setStyle(BUG_STYLE);
            }
            if(StringUtils.contains(s, "FIX:")) {
                setStyle(FIX_STYLE);
            }
        } else {
            setText(""); // FIXME: Fix for not removing old values, this is not the right way.
        }
    }

}
