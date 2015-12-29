package org.shopkeeper.gui.fx.completeviews;

import javafx.scene.layout.BorderPane;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCustomerFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();
        ArrayList<AbstractViewFX> views = new ArrayList<>();
        ListViewFX list = new ListViewFX(WindowBuilderFX.CUSTOMER_MODEL);
        views.add(list);
        WindowBuilderFX.CUSTOMER_MODEL.setViewPackage(views);
        WindowBuilderFX.CUSTOMER_MODEL.updateViews();
        panel.setCenter(list);
        return panel;

    }

}
