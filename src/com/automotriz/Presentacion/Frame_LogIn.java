package com.automotriz.Presentacion;

import com.automotriz.VO.Session;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;
import java.util.HashMap;
import com.automotriz.logger.Logger;
import com.automotriz.logger.LoggerQuery;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frame_LogIn extends javax.swing.JFrame implements Runnable {

    public static String perfil = "";
    private Thread hilo;

    public Frame_LogIn() {
        initComponents();
        try {
            new Thread(this).start();
            this.initFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A thread to catch the selected profile in order to log in
     */
    @Override
    public void run() {
        //waiting till the gif has loaded
        try {
            if (hilo != null) {
                hilo.sleep(1000);
                validacion();
                hilo = null;
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }

        while (perfil.equals("")) {
            this.setTitle("Perfil: " + perfil);
        }
        this.setTitle("Perfil: " + perfil);
    }

    private void initFrame() throws Exception {
        //getting the application properties
        ReadProperties.loadApplicationProps();
        //init logs
        Logger.start();

        String name = ReadProperties.props.getProperty("name.logIn");
        this.setName(name);
        this.setTitle(name);
        Logger.log("Starting " + this.getName() + " frame...");
        //int SQL log
        LoggerQuery.start();
        this.setLocationRelativeTo(this);
        registrarLabelsDesign();

        this.initGetPerfil();

        panelForm.setBackground(Color.decode(ReadProperties.props.getProperty("color.grey")));

        //Setting an image for the panel above
        JLabel label = new JLabel(new ImageIcon(getClass()
                .getResource(ReadProperties.props.getProperty("icon.wallpaper"))));
        label.setSize(panelTitle.getWidth(), panelTitle.getHeight());
        panelTitle.add(label);

        panelForm.setBorder(new BevelBorder(BevelBorder.RAISED));

        String value = ReadProperties.props.getProperty("login.title");
        lbl_title.setText(value);

        value = ReadProperties.props.getProperty("login.boton.login");
        btn_entrar.setText(value);

        value = ReadProperties.props.getProperty("login.boton.cerrar");
        btn_atras.setText(value);
        lbl_close.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.close")))
                                .getImage()
                                .getScaledInstance(lbl_close.getWidth(), lbl_close.getHeight(), Image.SCALE_DEFAULT)
                )
        );
    }

    //setting the design some labels
    private void registrarLabelsDesign() {
        lbl_msg_registar.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        lbl_registrar_aqui.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_registrar_aqui.setForeground(Color.blue.darker());
        lbl_registrar_aqui.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void initGetPerfil() {
        Frame_GetPerfil h = new Frame_GetPerfil(this, true);
        h.setLocationRelativeTo(this);
        h.setVisible(true);
    }

    private void gotToSignIn() {
        Frame_SignIn signIn = new Frame_SignIn(this, true);
        signIn.setVisible(true);
    }

    private void closeProgram() {
        int option = JOptionPane.showOptionDialog(this,
                "¿Esta seguro que desea salir del sistema?",
                "Apagar Sistema",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void validacion() {
        Validacion validacion = new Validacion(new Object[]{
            txt_username.getText().trim(),
            new Hashing(txt_password.getText().trim()).encrypt(),
            perfil
        }, new Session()).validarLogIn();

        HashMap propMensaje = validacion.getMessage();

        if (propMensaje != null) { //if an error message is ready to show up
            JOptionPane.showMessageDialog(this,
                    propMensaje.get("message").toString(),
                    propMensaje.get("title").toString(),
                    Integer.parseInt(propMensaje.get("type").toString()));
        } else {
            this.dispose();
            //send the session to menuPrincipal
            new Frame_Inicio(validacion.getSession());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        panelForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_entrar = new javax.swing.JButton();
        btn_atras = new javax.swing.JButton();
        panelTitle = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        lbl_msg_registar = new javax.swing.JLabel();
        lbl_registrar_aqui = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        panelForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(51, 51, 51)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel2.setText("Contraseña");

        txt_username.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_password.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_entrar.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btn_entrar.setText("jButton1");
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });

        btn_atras.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btn_atras.setText("jButton2");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 131, Short.MAX_VALUE)
                        .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_username)
                            .addComponent(txt_password))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_entrar)
                    .addComponent(btn_atras))
                .addGap(33, 33, 33))
        );

        lbl_title.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N
        lbl_title.setText("jLabel1");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lbl_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbl_title)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        lbl_msg_registar.setText("¿No tienes una cuenta? Registrate");

        lbl_registrar_aqui.setText("Aqui");
        lbl_registrar_aqui.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbl_registrar_aquiMouseMoved(evt);
            }
        });
        lbl_registrar_aqui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_registrar_aquiMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_registrar_aquiMouseExited(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_close, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_msg_registar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_registrar_aqui))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_msg_registar)
                    .addComponent(lbl_registrar_aqui))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        Frame_Loading.main(null);
        hilo = new Thread(this);
        hilo.start();
    }//GEN-LAST:event_btn_entrarActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        this.txt_username.setText(null);
        this.txt_password.setText(null);
        //reset the value
        perfil = "";
        //start again the thread to catch the profile
        new Thread(this).start();
        new Frame_GetPerfil(this, true).setVisible(true);

    }//GEN-LAST:event_btn_atrasActionPerformed

    private void lbl_registrar_aquiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registrar_aquiMouseMoved
        lbl_registrar_aqui.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lbl_registrar_aqui.setForeground(Color.red.darker());
    }//GEN-LAST:event_lbl_registrar_aquiMouseMoved

    private void lbl_registrar_aquiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registrar_aquiMouseExited
        registrarLabelsDesign();
    }//GEN-LAST:event_lbl_registrar_aquiMouseExited

    private void lbl_registrar_aquiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registrar_aquiMouseClicked
        gotToSignIn();
    }//GEN-LAST:event_lbl_registrar_aquiMouseClicked

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        closeProgram();
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        lbl_close.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_lbl_closeMousePressed

    private void lbl_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseReleased
        lbl_close.setBorder(null);
    }//GEN-LAST:event_lbl_closeMouseReleased
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_LogIn().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_entrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_msg_registar;
    private javax.swing.JLabel lbl_registrar_aqui;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelTitle;
    public static javax.swing.JPasswordField txt_password;
    public static javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}

class Wait implements Runnable {

    public static Thread hilo;

    public Wait() {
        hilo = new Thread(this);
        hilo.start();
    }

    public void run() {

    }
}
