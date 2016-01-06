package org.shopkeeper.gui.fx.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.util.StringBeatifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListViewFX extends TableView implements AbstractViewFX {

    private AbstractModelFX MODEL = null;
    private static ListSelectionModelFX SELECTION_MODEL = new ListSelectionModelFX();

    public ListViewFX(AbstractModelFX model, ArrayList<AbstractSelectionViewFX> views) {
        MODEL = model;
        setPrefSize(Preference.PREF_WIDTH_LIST_CONTAINERS, Preference.PREF_HEIGHT_LIST_CONTAINERS);
        setMaxWidth(Preference.PREF_WIDTH_LIST_CONTAINERS); // TODO Needs to be double for some reason
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
                TableColumn col = new TableColumn(StringBeatifier.beautify((String)k));
                getColumns().add(col);
                col.setCellValueFactory(new PropertyValueFactory<Subject, String>((String) k));
            });
        }

        for (Subject subject : MODEL.getSubjects()) {
            list.add(subject);
        }
        ObservableList<Subject> names = FXCollections.observableArrayList(list);
        setItems(names);


        // Init combos:

    }
}
