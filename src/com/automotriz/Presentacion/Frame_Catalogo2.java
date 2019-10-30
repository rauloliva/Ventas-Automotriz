package com.automotriz.Presentacion;

import com.automotriz.VO.AutoVO;
import com.automotriz.VO.Session;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class Frame_Catalogo2 extends javax.swing.JInternalFrame implements Runnable {

    private JFrame parent;
    private JDesktopPane container;
    private Session session;
    private List<AutoVO> autosVO;
    
    public Frame_Catalogo2(JFrame parent, JDesktopPane container, Session session) {
        initComponents();
        this.setVisible(true);
    }
    
    @Override
    public void run(){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1191, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
