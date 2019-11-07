package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.Constantes.Constants;

public class Frame_Graph extends javax.swing.JInternalFrame implements Constants<Frame_Graph> {

    public Frame_Graph() {
        initComponents();
        setVisible(true);
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_Graph c) {
        String name = ReadProperties.props.getProperty("name.Graph");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        Graph graph = new Graph(panelContent);
        graph.setGraphBarra();
        menu_vender.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.vender")))
                                .getImage()
                                .getScaledInstance(32, 32, Image.SCALE_DEFAULT)
                )
        );

        menu_feedback.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.feedback")))
                                .getImage()
                                .getScaledInstance(32, 32, Image.SCALE_DEFAULT)
                )
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuVerPerfil = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menu_vender = new javax.swing.JMenuItem();
        menu_feedback = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        menuVerPerfil.setText("Perfil");
        menuVerPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuVerPerfil.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/perfil.png"))); // NOI18N
        jMenuItem1.setText("Ver mi Perfil");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuVerPerfil.add(jMenuItem1);

        jMenuBar1.add(menuVerPerfil);

        jMenu1.setText("Opciones");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        menu_vender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menu_vender.setText("Vender");
        menu_vender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_venderActionPerformed(evt);
            }
        });
        jMenu1.add(menu_vender);

        menu_feedback.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menu_feedback.setText("Ver Comentarios");
        menu_feedback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_feedbackActionPerformed(evt);
            }
        });
        jMenu1.add(menu_feedback);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/catalogo.png"))); // NOI18N
        jMenuItem3.setText("Ver Catalogo");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        global.getContainer().add(new Frame_Perfil());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menu_venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_venderActionPerformed
        this.dispose();
        global.getContainer().add(new Frame_Vender());
    }//GEN-LAST:event_menu_venderActionPerformed

    private void menu_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_feedbackActionPerformed
        this.dispose();
        global.getContainer().add(new Frame_Comentarios());
    }//GEN-LAST:event_menu_feedbackActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        global.getContainer().add(new Frame_Catalogo());
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenu menuVerPerfil;
    private javax.swing.JMenuItem menu_feedback;
    private javax.swing.JMenuItem menu_vender;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}
