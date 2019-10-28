package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Image;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Frame_GetPerfil extends javax.swing.JDialog {

    public Frame_GetPerfil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.initFrame();
        this.setLocationRelativeTo(null);
        //set the text from properties
        String value = ReadProperties.props.getProperty("getPerfil.title");
        lbl_title.setText(value);
    }

    private void initFrame() {
        this.setName(ReadProperties.props.getProperty("name.GetPerfil"));
        Logger.log("Starting " + this.getName() + " modal...");

        lbl_close.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.close")))
                                .getImage()
                                .getScaledInstance(lbl_close.getWidth(), lbl_close.getHeight(), Image.SCALE_DEFAULT)
                )
        );
        //Setting an image for the panel above
        JLabel label = new JLabel(new ImageIcon(getClass()
                .getResource(ReadProperties.props.getProperty("icon.wallpaper"))));
        label.setSize(panel_top.getWidth(), panel_top.getHeight());
        panel_top.add(label);
        panel_top.setBorder(new BevelBorder(BevelBorder.RAISED));

        panel_center.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));

        lbl_admin.setVisible(false);
        lbl_cliente.setVisible(false);

        this.setIconCliente();
        this.setIconAdmin();
    }

    private void setIconCliente() {
        String value = ReadProperties.props.getProperty("icon.cliente");
        ImageIcon icon = new ImageIcon(getClass().getResource(value));
        Image img = icon.getImage().getScaledInstance(
                iconCliente.getWidth(),
                iconCliente.getHeight(),
                java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        this.iconCliente.setIcon(icon);
    }

    private void setIconAdmin() {
        String value = ReadProperties.props.getProperty("icon.admin");
        ImageIcon icon = new ImageIcon(getClass().getResource(value));
        Image img = icon.getImage().getScaledInstance(
                iconAdminstrador.getWidth(),
                iconAdminstrador.getHeight(),
                java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        this.iconAdminstrador.setIcon(icon);
    }

    private void closeProgram() {
        int option = JOptionPane.showOptionDialog(this,
                "¿Esta seguro que desea salir del sistema?",
                "Apagar Sistema",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            Enumeration<Object> en = System.getProperties().keys();
            while (en.hasMoreElements()) {
                System.out.println(en.nextElement());
            }
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_center = new javax.swing.JPanel();
        panel_top = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        iconCliente = new javax.swing.JLabel();
        iconAdminstrador = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_admin = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panel_center.setInheritsPopupMenu(true);

        panel_top.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 107, 31), 4, true));

        lbl_title.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbl_title.setText("Selecciona un perfil");

        javax.swing.GroupLayout panel_topLayout = new javax.swing.GroupLayout(panel_top);
        panel_top.setLayout(panel_topLayout);
        panel_topLayout.setHorizontalGroup(
            panel_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_topLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_title)
                .addGap(167, 167, 167))
        );
        panel_topLayout.setVerticalGroup(
            panel_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_topLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(lbl_title)
                .addGap(41, 41, 41))
        );

        iconCliente.setToolTipText("Cliente");
        iconCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        iconCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconClienteMouseExited(evt);
            }
        });

        iconAdminstrador.setToolTipText("Administrador");
        iconAdminstrador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        iconAdminstrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconAdminstrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconAdminstradorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconAdminstradorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconAdminstradorMouseExited(evt);
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

        lbl_admin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_admin.setText("Administrador");

        lbl_cliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_cliente.setText("Cliente");

        javax.swing.GroupLayout panel_centerLayout = new javax.swing.GroupLayout(panel_center);
        panel_center.setLayout(panel_centerLayout);
        panel_centerLayout.setHorizontalGroup(
            panel_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_centerLayout.createSequentialGroup()
                .addGroup(panel_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_centerLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(iconCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(iconAdminstrador, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_centerLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lbl_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        panel_centerLayout.setVerticalGroup(
            panel_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_centerLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(panel_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconAdminstrador, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panel_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_center, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconClienteMouseClicked
        Frame_LogIn.perfil = "Cliente";
        Logger.log("Saving type of account: Cliente");
        this.dispose();
    }//GEN-LAST:event_iconClienteMouseClicked

    private void iconAdminstradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconAdminstradorMouseClicked
        Frame_LogIn.perfil = "Administrador";
        Logger.log("Saving type of account: Administrador");
        this.dispose();
    }//GEN-LAST:event_iconAdminstradorMouseClicked

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        closeProgram();
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        lbl_close.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_lbl_closeMousePressed

    private void lbl_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseReleased
        lbl_close.setBorder(null);
    }//GEN-LAST:event_lbl_closeMouseReleased

    private void iconClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconClienteMouseEntered
        lbl_cliente.setVisible(true);
    }//GEN-LAST:event_iconClienteMouseEntered

    private void iconClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconClienteMouseExited
        lbl_cliente.setVisible(false);
    }//GEN-LAST:event_iconClienteMouseExited

    private void iconAdminstradorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconAdminstradorMouseEntered
        lbl_admin.setVisible(true);
    }//GEN-LAST:event_iconAdminstradorMouseEntered

    private void iconAdminstradorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconAdminstradorMouseExited
        lbl_admin.setVisible(false);
    }//GEN-LAST:event_iconAdminstradorMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconAdminstrador;
    private javax.swing.JLabel iconCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_admin;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel panel_center;
    private javax.swing.JPanel panel_top;
    // End of variables declaration//GEN-END:variables
}
