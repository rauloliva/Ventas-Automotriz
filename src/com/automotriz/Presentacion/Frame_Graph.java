package com.automotriz.Presentacion;

import com.automotriz.VO.Session;
import com.automotriz.logger.Logger;
import java.awt.Color;
import javax.swing.*;

public class Frame_Graph extends javax.swing.JInternalFrame {

    private JFrame parent;
    private JDesktopPane container;
    private Session session;

    public Frame_Graph(JFrame parent, JDesktopPane container, Session session) {
        initComponents();
        this.parent = parent;
        this.container = container;
        this.session = session;
        setVisible(true);
        initGraph();
    }

    private void initGraph() {
        String name = ReadProperties.props.getProperty("name.Graph");
        this.setName(name);
        this.setTitle(name);
        Logger.log("Starting " + this.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        Graph graph = new Graph(panelContent);
        graph.setGraphBarra();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuVerPerfil = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        menuVerPerfil.setText("Perfil");
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
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem4.setText("Vender");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setText("Ver Comentarios");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

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
        container.add(new Frame_Perfil(parent, container, session));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.dispose();
        container.add(new Frame_Vender(parent, container, session));
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.dispose();
        container.add(new Frame_Comentarios(parent, container, session));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        container.add(new Frame_Catalogo(parent, container, session));
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu menuVerPerfil;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}
