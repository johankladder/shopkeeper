package org.shopkeeper.gui.fx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;
import org.shopkeeper.subjects.subjecttypes.Subject;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewFX extends TableView implements AbstractViewFX {

    private AbstractModelFX MODEL = null;
    private static ListSelectionModelFX SELECTION_MODEL = new ListSelectionModelFX();

    public ListViewFX(AbstractModelFX model, ArrayList<SelectionViewFX> views) {
        MODEL = model;
        SELECTION_MODEL.setViewPackage(views);
        getSelectionModel().selectedItemProperty().addListener(SELECTION_MODEL);
    }

    @Override
    public void updateView() {
        setItems(null);
        List<Subject> list = new ArrayList<>();
        if(MODEL.getSubjects().size() > 0) {
            Subject temp = MODEL.getSubjects().get(0);
            Map map = temp.getFields();
            map.forEach((k,v) -> {
                TableColumn col = new TableColumn((String)k);
                getColumns().add(col);
                col.setCellValueFactory(new PropertyValueFactory<Subject, String>((String) k));
            });
        }

        for (Subject subject : MODEL.getSubjects()) {
            list.add(subject);
        }
        ObservableList<Subject> names = FXCollections.observableArrayList(list);
        setItems(names);

    }
}
