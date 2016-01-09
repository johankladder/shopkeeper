package org.shopkeeper.gui.fx.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.model.subjects.ModelUtil;
import org.shopkeeper.subjects.ModuleHandler;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.items.Item;

/**
 * Created by johankladder on 1/9/16.
 */
public class UpdateSubjectController extends Button {

    private static final String BUTTON_TEXT = "Update";
    private ListSelectionModelFX model;

    public UpdateSubjectController(ListSelectionModelFX model) {
        this.model = model;
        init();
    }

    private void init() {
        setText(BUTTON_TEXT);
        setOnAction(new SelectionHandler(model));
        setMaxWidth(Double.MAX_VALUE);
    }


}


// TODO Make an abstract handler for these kind of buttons
class SelectionHandler implements EventHandler<ActionEvent> {

    private ListSelectionModelFX model;
    public SelectionHandler(ListSelectionModelFX model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        Subject subject = (Subject) model.getSelectedSubject();
        AbstractModelFX model_db = ModelUtil.getModelFromSubjectType(subject.TYPE);
        model_db.update(subject);
    }
}
