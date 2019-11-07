package com.automotriz.Presentacion;

import com.automotriz.Constantes.Global;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Image;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.VO.Session;
import com.automotriz.Constantes.Constants;

public class Frame_Inicio extends javax.swing.JFrame implements Runnable, Constants<Frame_Inicio> {

    private Thread hiloDate;
    private Session session;

    public Frame_Inicio(Session session) {
        initComponents();
        this.session = session;
        initFrame(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String currentDate = getDate();
                lbl_date.setText(currentDate);
                hiloDate.sleep(1000);
            } catch (Exception e) {
                Logger.error(e.getMessage());
                Logger.error(e.getStackTrace());
            }
        }
    }

    private String getDate() {
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        //getting either AM or PM
        String time = (hour >= 1 && hour < 12) ? "AM" : "PM";
        return day + "/" + month + "/" + year + "  " + hour + ":"
                + (minute >= 0 && minute <= 9 ? "0" + minute : minute) + ":"
                + (second >= 0 && second <= 9 ? "0" + second : second) + " " + time;
    }

    @Override
    public void initFrame(Frame_Inicio c) {
        //initialize the global variables (parent, container, session)
        initGlobal();
        //Start the date thread
        hiloDate = new Thread(c);
        hiloDate.start();
        lbl_close.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.close")))
                                .getImage()
                                .getScaledInstance(lbl_close.getWidth(), lbl_close.getHeight(), Image.SCALE_DEFAULT)
                )
        );

        String name;
        if (global.getSession().getPerfil().equals("Cliente")) {
            name = ReadProperties.props.getProperty("name.InicioCliente");
        } else {
            name = ReadProperties.props.getProperty("name.Inicio");
        }
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");

        c.setLocationRelativeTo(null);
        c.setVisible(true);
        //set the log off icon
        lbl_logOff.setIcon(new ImageIcon(c.getClass().getResource(ReadProperties.props.getProperty("icon.logOff"))));

        //set the home icon
        ImageIcon home = new ImageIcon(c.getClass().getResource(ReadProperties.props.getProperty("icon.home")));
        Image image = home.getImage();
        image = image.getScaledInstance(lbl_inicio.getWidth(), lbl_inicio.getHeight(), Image.SCALE_SMOOTH);
        home = new ImageIcon(image);
        lbl_inicio.setIcon(home);

        //Setting an image for the panel above
        JLabel label = new JLabel(new ImageIcon(getClass()
                .getResource(ReadProperties.props.getProperty("icon.wallpaper"))));
        label.setSize(panelTitle.getWidth(), panelTitle.getHeight());
        panelTitle.add(label);
        panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));

        panelTitle.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));

        lbl_company_name.setText(ReadProperties.props.getProperty("company.name"));
        lbl_usuario.setText("Usuario: " + global.getSession().getUsername().toUpperCase());
        lbl_perfil.setText("Perfil: " + global.getSession().getPerfil());

        //shows the graph only to administrators
        if (global.getSession().getPerfil().equals("Administrador")) {
            initGraph();
        } else {
            initClientFrame();
        }
        setLogo();
    }

    private void initGlobal() {
        new Global(this, menusPanel, session);
    }

    private void initGraph() {
        menusPanel.add(new Frame_Graph());
    }

    private void initClientFrame() {
        menusPanel.add(new Frame_InicioCliente());
    }

    private void setLogo() {
        String value = ReadProperties.props.getProperty("icon.logo");
        ImageIcon icon = new ImageIcon(getClass().getResource(value));
        Image img = icon.getImage().getScaledInstance(
                lbl_logo.getWidth(),
                lbl_logo.getHeight(),
                java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        this.lbl_logo.setIcon(icon);
    }

    private void logOff() {
        int option = JOptionPane.showOptionDialog(this,
                ReadProperties.props.getProperty("logoff"),
                ReadProperties.props.getProperty("logoff.title"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SALIR", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            this.dispose();
            Frame_LogIn.main(null);
        }
    }

    private void goToInicio() {
        menusPanel.removeAll();
        if (global.getSession().getPerfil().equals("Administrador")) {
            menusPanel.add(new Frame_Graph());
        } else {
            menusPanel.add(new Frame_InicioCliente());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        lbl_company_name = new javax.swing.JLabel();
        lbl_usuario = new javax.swing.JLabel();
        lbl_perfil = new javax.swing.JLabel();
        lbl_inicio = new javax.swing.JLabel();
        lbl_logOff = new javax.swing.JLabel();
        menusPanel = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        lbl_company_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_company_name.setText("jLabel1");

        lbl_usuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_usuario.setText("jLabel1");

        lbl_perfil.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_perfil.setText("jLabel1");

        lbl_inicio.setToolTipText("Home");
        lbl_inicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_inicio.setPreferredSize(new java.awt.Dimension(35, 35));
        lbl_inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_inicioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_inicioMouseExited(evt);
            }
        });

        lbl_logOff.setToolTipText("Cerrar Session");
        lbl_logOff.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_logOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_logOff.setPreferredSize(new java.awt.Dimension(35, 35));
        lbl_logOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_logOffMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_logOffMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_logOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 635, Short.MAX_VALUE)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_company_name)
                    .addComponent(lbl_usuario)
                    .addComponent(lbl_perfil))
                .addGap(147, 147, 147))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_company_name)
                .addGap(18, 18, 18)
                .addComponent(lbl_usuario)
                .addGap(18, 18, 18)
                .addComponent(lbl_perfil)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_logOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout menusPanelLayout = new javax.swing.GroupLayout(menusPanel);
        menusPanel.setLayout(menusPanelLayout);
        menusPanelLayout.setHorizontalGroup(
            menusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menusPanelLayout.setVerticalGroup(
            menusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        lbl_close.setToolTipText("Cerrar la Aplicacion");
        lbl_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_closeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl_closeMouseReleased(evt);
            }
        });

        lbl_date.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_date.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lbl_date, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_date, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menusPanel)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menusPanel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_inicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_inicioMouseClicked
        lbl_inicio.setBorder(new BevelBorder(BevelBorder.LOWERED));
        goToInicio();
    }//GEN-LAST:event_lbl_inicioMouseClicked

    private void lbl_inicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_inicioMouseExited
        lbl_inicio.setBorder(new BevelBorder(BevelBorder.RAISED));
    }//GEN-LAST:event_lbl_inicioMouseExited

    private void lbl_logOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_logOffMouseClicked
        lbl_logOff.setBorder(new BevelBorder(BevelBorder.LOWERED));
        logOff();
    }//GEN-LAST:event_lbl_logOffMouseClicked

    private void lbl_logOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_logOffMouseExited
        lbl_logOff.setBorder(new BevelBorder(BevelBorder.RAISED));
    }//GEN-LAST:event_lbl_logOffMouseExited

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        Constants.metohds.closeProgram(this);
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        lbl_close.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_lbl_closeMousePressed

    private void lbl_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseReleased
        lbl_close.setBorder(null);
    }//GEN-LAST:event_lbl_closeMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_company_name;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_inicio;
    private javax.swing.JLabel lbl_logOff;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_perfil;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JDesktopPane menusPanel;
    private javax.swing.JPanel panelTitle;
    // End of variables declaration//GEN-END:variables
}
