package org.shopkeeper.gui.fx.view.cellrenderers;

import javafx.scene.control.ListCell;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class NotesCellRenderer extends ListCell<String> {

    static final String ADD_STYLE = "{-fx-background-color: green;}";


    @Override
    protected void updateItem(String s, boolean bln) {
        super.updateItem(s, bln);
        setStyle(null); // Reset style
        if (s != null) {
            setText(s);
            if(StringUtils.contains(s, "ADD:")) {
                //setStyle(ADD_STYLE); // TODO Selection in the list
            }
        } else {
            setText(""); // FIXME: Fix for not removing old values, this is not the right way.
        }
    }

}
