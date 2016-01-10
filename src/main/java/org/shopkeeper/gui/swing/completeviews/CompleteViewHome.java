package org.shopkeeper.gui.swing.completeviews;

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewHome {

    public static JPanel getView() {
        JPanel panel = new JPanel(new BorderLayout());
        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(CompleteViewHome.class.getResource("/images/preloader_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        panel.add(wIcon, BorderLayout.SOUTH);
        return panel;

    }
}
