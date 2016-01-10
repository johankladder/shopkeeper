package org.shopkeeper.gui.fx.model.subjects;

import javafx.scene.control.*;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.awt.*;
import java.awt.TextField;
import java.util.Map;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class SubjectsUtil {

    public static Map createUpdateSubjectMap(Subject subject, Map map) {
        Map subjectMap = subject.getFields();
        map.forEach((k,v)-> {
            if(v instanceof String) {
                subjectMap.put(k,v);
            }
            if(v instanceof javafx.scene.control.TextField) {
                javafx.scene.control.TextField valueField = (javafx.scene.control.TextField) v;
                subjectMap.put(k,valueField.getText());
            }
        });
        return subjectMap;
    }


}
