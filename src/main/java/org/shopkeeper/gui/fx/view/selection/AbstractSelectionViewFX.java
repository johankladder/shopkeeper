package org.shopkeeper.gui.fx.view.selection;


import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;

/**
 * Created by typhooncoaster on 5-1-16.
 */
public interface AbstractSelectionViewFX {

    void setModel(ListSelectionModelFX model);
    void updateView();
}

