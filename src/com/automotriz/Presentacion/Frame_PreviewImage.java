package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.automotriz.Constantes.Constants;

public class Frame_PreviewImage extends javax.swing.JDialog implements Constants<Frame_PreviewImage> {

    private String imagePath;

    public Frame_PreviewImage(java.awt.Frame parent, boolean modal, String imagePath) {
        super(parent, modal);
        this.imagePath = imagePath;
        initComponents();
        setLocationRelativeTo(null);
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_PreviewImage c) {
        String name = ReadProperties.props.getProperty("name.PreviewImage");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        seePreview(c.imagePath);
        setVisible(true);
    }

    private void seePreview(String imagePath) {
        ImageIcon myImage = new ImageIcon(imagePath);
        Image image = myImage.getImage();
        image = image.getScaledInstance(lbl_preview.getWidth(), lbl_preview.getHeight(), Image.SCALE_SMOOTH);
        myImage = new ImageIcon(image);
        lbl_preview.setIcon(myImage);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_preview = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl_preview.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_preview, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_preview, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_preview;
    // End of variables declaration//GEN-END:variables
}
