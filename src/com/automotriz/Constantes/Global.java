package com.automotriz.Constantes;

import com.automotriz.VO.SessionVO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 * global veraibles such as the session and other useful information through the
 * program
 *
 * @author oliva
 */
public class Global {

    public static Global global;
    private JFrame parent;
    private JDesktopPane container;
    private SessionVO session;

    public Global(JFrame parent, JDesktopPane container, SessionVO session) {
        if (global == null) {
            global = new Global();
            global.setParent(parent);
            global.setContainer(container);
            global.setSession(session);
        }
    }

    public Global() {
    }

    public JFrame getParent() {
        return parent;
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public JDesktopPane getContainer() {
        return container;
    }

    public void setContainer(JDesktopPane container) {
        this.container = container;
    }

    public SessionVO getSession() {
        return session;
    }

    public void setSession(SessionVO session) {
        this.session = session;
    }
}
