package org.shopkeeper.gui.fx.view.selection;


import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;

public interface SelectionViewFX {
    void setModel(ListSelectionModelFX model);
    void updateView();
}

