package com.automotriz.Constantes;

import com.automotriz.Presentacion.ReadProperties;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GlobalMethods {

    private final int logo_width = 45;
    private final int logo_height = 40;

    /**
     * Asks the user to close the entire program
     *
     * @param view the frame where the message will be shown
     */
    public void closeProgram(Component view) {
        int option = JOptionPane.showOptionDialog(view,
                ReadProperties.props.getProperty("system.shutdown"),
                ReadProperties.props.getProperty("system.shutdown.title"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * sets the system's log
     *
     * @param frame the frame where the logo will be located
     */
    public void setLogoSystem(JFrame frame) {
        //setting a logo for the system
        frame.setIconImage(new ImageIcon(
                new ImageIcon(frame.getClass().getResource(ReadProperties.props.getProperty("icon.logo.small")))
                        .getImage()
                        .getScaledInstance(logo_width, logo_height, Image.SCALE_DEFAULT)).getImage()
        );
    }

    /**
     * sets the close icon to a label
     *
     * @param lbl the label where the icon will be located
     * @param frame the frame where the label is located
     */
    public void setCloseIcon(JLabel lbl, Object frame) {
        //setting the close icon
        lbl.setIcon(new ImageIcon(
                new ImageIcon(frame.getClass().getResource(ReadProperties.props.getProperty("icon.close")))
                        .getImage()
                        .getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT))
        );
    }

    /**
     * sets an icon to a JButton
     *
     * @param btn the JButton Object
     * @param iconKey the key where the Icon's location is
     * @param width the icon's width
     * @param height the icon's height
     */
    public void setIconToButton(Object frame, JButton btn, String iconKey, int width, int height) {
        btn.setIcon(
                new ImageIcon(
                        new ImageIcon(frame.getClass().getResource(ReadProperties.props.getProperty(iconKey)))
                                .getImage()
                                .getScaledInstance(width, height, Image.SCALE_DEFAULT)
                )
        );
    }
}
