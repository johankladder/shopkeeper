package org.shopkeeper.gui.fx.controller.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.gui.fx.model.subjects.ModelUtil;
import org.shopkeeper.gui.fx.model.subjects.SubjectsUtil;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.Map;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class UpdateActionHandler implements EventHandler<ActionEvent> {

    private ListSelectionModelFX model;
    public UpdateActionHandler(ListSelectionModelFX model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        Subject subject = (Subject) model.getSelectedSubject();
        AbstractModelFX model_db = ModelUtil.getModelFromSubjectType(subject.TYPE);
        subject = SubjectsUtil.createSubjectFromUpdateSubjectMap(subject, model.getMap());
        model_db.update(subject);
    }
}
