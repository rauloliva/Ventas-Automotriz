package com.automotriz.Presentacion;

import com.automotriz.VO.UsuarioVO;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import static com.automotriz.Constantes.Global.global;

public class Frame_EnviarCorreo extends javax.swing.JInternalFrame implements Runnable {

    private String destinatario;
    private Thread hiloProgress = new Thread();
    private File fileAttached = null;
    private Thread hiloSend;
    private String vendedorName;

    public Frame_EnviarCorreo(String dest) {
        initComponents();
        destinatario = dest;
        initFrame();
        setVisible(true);
    }

    private void initFrame() {
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        txt_mail_origen.setText(global.getSession().getMail());
        txt_mail_dest.setText(destinatario);
        attachFile_loader.setValue(0);
        attachFile_loader.setStringPainted(true);
        lbl_send.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.sendEmail")))
                                .getImage()
                                .getScaledInstance(53, 41, Image.SCALE_DEFAULT)
                )
        );
        lbl_attachFile.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.attach")))
                                .getImage()
                                .getScaledInstance(53, 41, Image.SCALE_DEFAULT)
                )
        );
        txa_mensaje.setText(setBodyMsg());
    }

    /**
     * uses the name of the vendor to create the beginning of the message
     *
     * @return the body message
     */
    private String setBodyMsg() {
        vendedorName = vendedorName == null ? getNombreVendedor() : vendedorName;

        Calendar c = new GregorianCalendar();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String time;
        if (hour >= 1 && hour < 12) {
            time = "Buenos Dias";
        } else if (hour >= 12 && hour < 6) {
            time = "Buenas Tardes";
        } else {
            time = "Buenas Noches";
        }
        return time + " " + vendedorName.split(" ")[0];
    }

    /**
     * This method gets the name of the vendedor in the body of the mail, it
     * uses their emails (destinatario) to get their names
     */
    private String getNombreVendedor() {
        Validacion validacion = new Validacion(new Object[]{this.destinatario});
        validacion.setObjectVO(new UsuarioVO());
        validacion = validacion.getVendedorName();
        //getting the only vendedor from the list
        UsuarioVO vendedor = validacion.getUsuarios().get(0);
        return vendedor.getNombre();
    }

    @Override
    public void run() {
        //this part pf the thread is for sending the email
        try {
            if (hiloSend != null) {
                hiloSend.sleep(500);
                sendMail();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }

        //this part of the thread is for loading the progress of the JProgressBar
        if (hiloSend == null) {
            int i = 0;
            try {
                while (i <= 100) {
                    // fill the menu bar 
                    attachFile_loader.setValue(i + 10);
                    // delay the thread 
                    hiloProgress.sleep(500);
                    i += 20;
                }
                hiloProgress.stop();
            } catch (Exception e) {
                Logger.error(e.getMessage());
                Logger.error(e.getStackTrace());
            }
        }
        hiloSend = null;
    }

    private void attachFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setName("Attach File");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileAttached = chooser.getSelectedFile();
            lbl_attachedFileName.setText(fileAttached.getName());

            //starts the thread the fill the progressBar
            attachFile_loader.setValue(0);
            hiloProgress = new Thread(this);
            hiloProgress.start();
        }
    }

    private void sendMail() {
        lbl_send.setBorder(new BevelBorder(BevelBorder.LOWERED));
        Validacion validacion = new Validacion(new Object[]{
            /*txt_mail_dest.getText()*/"ralf.raul28@gmail.com",
            txt_asunto.getText(),
            txa_mensaje.getText(),
            fileAttached
        }).sendMailToVendedor();

        HashMap props = validacion.getMessage();
        if (props != null) {
            JOptionPane.showMessageDialog(this,
                    props.get("message").toString(),
                    props.get("title").toString(),
                    Integer.parseInt(props.get("type").toString()));
        } else {
            cleanForm();
        }
        lbl_send.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private void cleanForm() {
        txt_asunto.setText(null);
        txa_mensaje.setText(setBodyMsg());
        lbl_attachedFileName.setText("Ningun archivo seleccionado");
        fileAttached = null;
        attachFile_loader.setValue(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_mail_origen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_mail_dest = new javax.swing.JTextField();
        attachFile_loader = new javax.swing.JProgressBar();
        lbl_attachedFileName = new javax.swing.JLabel();
        lbl_attachFile = new javax.swing.JLabel();
        lbl_send = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_asunto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_mensaje = new javax.swing.JTextArea();

        panelContent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel1.setText("Envio de Correo");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_mail_origen.setEditable(false);
        txt_mail_origen.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_mail_origen.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("De:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Para:");

        txt_mail_dest.setEditable(false);
        txt_mail_dest.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_mail_dest.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        attachFile_loader.setForeground(new java.awt.Color(255, 0, 51));

        lbl_attachedFileName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_attachedFileName.setText("Ningun archivo seleccionado");

        lbl_attachFile.setToolTipText("Añadir Archivo");
        lbl_attachFile.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_attachFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_attachFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_attachFileMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_attachFileMouseExited(evt);
            }
        });

        lbl_send.setToolTipText("Enviar Correo");
        lbl_send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_send.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_sendMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_sendMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_mail_dest, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mail_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(attachFile_loader, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_attachedFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_send, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_attachFile, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(164, 164, 164))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_mail_dest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(attachFile_loader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_attachedFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_mail_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_attachFile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_send, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Titulo");

        txt_asunto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_asunto, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_asunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txa_mensaje.setColumns(20);
        txa_mensaje.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txa_mensaje.setRows(5);
        jScrollPane1.setViewportView(txa_mensaje);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_attachFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_attachFileMouseClicked
        lbl_attachFile.setBorder(new BevelBorder(BevelBorder.LOWERED));
        attachFile();
    }//GEN-LAST:event_lbl_attachFileMouseClicked

    private void lbl_sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sendMouseClicked
        if (!hiloProgress.isAlive()) {
            Frame_Loading.main(null);
            hiloSend = new Thread(this);
            hiloSend.start();
        }
    }//GEN-LAST:event_lbl_sendMouseClicked

    private void lbl_attachFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_attachFileMouseExited
        lbl_attachFile.setBorder(new BevelBorder(BevelBorder.RAISED));
    }//GEN-LAST:event_lbl_attachFileMouseExited

    private void lbl_sendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sendMouseExited
        lbl_send.setBorder(new BevelBorder(BevelBorder.RAISED));
    }//GEN-LAST:event_lbl_sendMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar attachFile_loader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_attachFile;
    private javax.swing.JLabel lbl_attachedFileName;
    private javax.swing.JLabel lbl_send;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTextArea txa_mensaje;
    private javax.swing.JTextField txt_asunto;
    private javax.swing.JTextField txt_mail_dest;
    private javax.swing.JTextField txt_mail_origen;
    // End of variables declaration//GEN-END:variables
}
