package org.shopkeeper.gui.swing.completeviews;

import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewModule {

    public static JPanel MAIN = null;
    public static JPanel LASTADDED = null;

    public static void getCompleteview(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            if (LASTADDED != null) {
                MAIN.remove(LASTADDED);
            }
            JPanel newView = CompleteViewItem.getView();
            MAIN.add(newView, BorderLayout.CENTER);
            MAIN.repaint();
            MAIN.revalidate();
            LASTADDED = newView;
        } else if (subjectType == SubjectTypes.CATEGORY) {

        } else if (subjectType == SubjectTypes.CUSTOMER) {

        } else {

        }
    }
}
