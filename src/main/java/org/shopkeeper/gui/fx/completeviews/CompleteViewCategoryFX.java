package org.shopkeeper.gui.fx.completeviews;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.gui.fx.view.selection.InformationViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCategoryFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();

        InformationViewFX info_view = new InformationViewFX();
        ArrayList<AbstractSelectionViewFX> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListViewFX list = new ListViewFX(WindowBuilderFX.CATEGORY_MODEL, views_list);

        ArrayList<AbstractViewFX> views = new ArrayList<>();
        views.add(list);
        WindowBuilderFX.CATEGORY_MODEL.setViewPackage(views);
        WindowBuilderFX.CATEGORY_MODEL.updateViews();

        VBox list_info = new VBox();
        list.setMaxWidth(Double.MAX_VALUE);
        info_view.setMaxWidth(Double.MAX_VALUE);
        list_info.getChildren().addAll(list,info_view);

        panel.setCenter(list_info);
        return panel;

    }

}
