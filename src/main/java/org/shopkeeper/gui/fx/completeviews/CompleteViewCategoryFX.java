package org.shopkeeper.gui.fx.completeviews;

import javafx.scene.layout.BorderPane;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCategoryFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();
        ArrayList<AbstractViewFX> views = new ArrayList<>();
        ListViewFX list = new ListViewFX(WindowBuilderFX.CATEGORY_MODEL);
        views.add(list);
        WindowBuilderFX.CATEGORY_MODEL.setViewPackage(views);
        WindowBuilderFX.CATEGORY_MODEL.add(null);
        panel.setCenter(list);
        return panel;

    }

}
