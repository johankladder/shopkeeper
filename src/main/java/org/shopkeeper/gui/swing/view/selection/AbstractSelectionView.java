package org.shopkeeper.gui.swing.view.selection;

        import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;

/**
 * Created by typhooncoaster on 5-1-16.
 */
public interface AbstractSelectionView {

    void setModel(ListSelectionModel model);
    void updateView();
}
