package com.automotriz.Constantes;

import com.automotriz.Presentacion.ReadProperties;
import java.awt.Component;
import javax.swing.JOptionPane;

public class GlobalMethods {

    public  void  closeProgram(Component view) {
        int option = JOptionPane.showOptionDialog(view,
                ReadProperties.props.getProperty("system.shutdown"),
                ReadProperties.props.getProperty("system.shutdown.title"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
