package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.automotriz.Constantes.Constants;

public class Frame_Loading extends javax.swing.JFrame implements Runnable, Constants<Frame_Loading> {
    
    public static boolean stop = false;
    private Thread hilo;
    
    public Frame_Loading() {
        initComponents();
        this.setLocationRelativeTo(null);
        initFrame(this);
    }
    
    @Override
    public void initFrame(Frame_Loading c) {
        //start the thread 
        hilo = new Thread(c);
        hilo.start();
        new Imagen(lbl_img);
    }
    
    @Override
    public void run() {
        try {
            hilo.sleep(2000);
            stop = true; //this will tell the thread in Imagen class to stop
            this.dispose();
            stop = false;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        lbl_img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            java.util.logging.Logger.getLogger(Frame_Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Loading().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_img;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}

/**
 * This class place an GIF image to a JLabel in a second thread
 *
 * @author oliva
 */
class Imagen implements Runnable {
    
    private JLabel img;
    private Thread hilo;

    /**
     * receives the JLabel where the image will be placed
     *
     * @param img return the Imagen object
     */
    public Imagen(JLabel img) {
        this.img = img;
        hilo = new Thread(this);
        hilo.start();
    }
    
    @Override
    public void run() {
        try {
            img.setIcon(
                    new ImageIcon(
                            new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.loading")))
                                    .getImage()
                                    .getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_DEFAULT)
                    )
            );

            //every 500 milisecond the thread will stop until the flag in Frame_Loading has changed
            while (!Frame_Loading.stop) {
                hilo.sleep(500);
            }
            img.setIcon(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
