package org.shopkeeper.gui.fx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;
import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.subjects.parsers.SubjectMapGenerator;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.util.Beatifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewFX extends TableView implements AbstractViewFX {

    private static ListSelectionModelFX SELECTION_MODEL = new ListSelectionModelFX();
    private AbstractModelFX MODEL = null;
    private ObservableList<Subject> names = FXCollections.observableArrayList();

    public ListViewFX(AbstractModelFX model, ArrayList<SelectionViewFX> views) {
        MODEL = model;
        SELECTION_MODEL.setViewPackage(views);
        getSelectionModel().selectedItemProperty().addListener(SELECTION_MODEL);
        init();
    }

    private void init() {
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        if (MODEL.getSubjects().size() > 0) {
            Subject temp = MODEL.getSubjects().get(0);
            Map map = SubjectMapGenerator.createUserViewMap(temp.getFields());
            map.forEach((k, v) -> {
                TableColumn col = new TableColumn(Beatifier.beautifyString((String) k));
                col.setMinWidth(PreferenceModule.MIN_WIDTH_TABLE_COLUMN);
                col.setCellValueFactory(new PropertyValueFactory<Subject, String>((String) k));
                getColumns().add(col);
            });
        }
        setMaxHeight(Double.MAX_VALUE);
    }

    @Override
    public void updateView() {
        names.clear(); // Clear for refreshing
        List<Subject> list = new ArrayList<>();
        for (Subject subject : MODEL.getSubjects()) {
            list.add(subject);
        }
        names = FXCollections.observableArrayList(list);
        setItems(names);

    }
}
