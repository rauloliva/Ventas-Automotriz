package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Color;
import javax.swing.*;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.Constantes.Constants;

public class Frame_AddComentario extends javax.swing.JInternalFrame implements Constants<Frame_AddComentario> {

    private int valoracion = 0;

    public Frame_AddComentario() {
        initComponents();
        setVisible(true);
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_AddComentario c) {
        String name = ReadProperties.props.getProperty("name.InicioCliente");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");

        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        panelStars.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        //initicializong starts 
        lbl_start1.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start2.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start3.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        
        Constants.metohds.setIconToButton(this, btn_clear_valoracion, "icon.clear", 35, 35);
        Constants.metohds.setIconToButton(this, btn_submit, "icon.submit", 35, 35);
    }

    private void submit() {
        Validacion validacion = new Validacion(new Object[]{
            txt_nombre.getText(),
            chb_anonimo.isSelected(),
            txa_comentarios.getText(),
            valoracion,
            global.getSession().getId(),
            Validacion.generateDate()
        });
        boolean response = validacion.submitComentario();

        if (response) {
            clearFields();
        }
    }

    private void clearFields() {
        txt_nombre.setText(null);
        txt_nombre.setEnabled(true);
        chb_anonimo.setSelected(false);
        txa_comentarios.setText(null);
        lbl_start1.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start2.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start3.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        panel_content3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        chb_anonimo = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        panelStars = new javax.swing.JPanel();
        lbl_start1 = new javax.swing.JLabel();
        lbl_start3 = new javax.swing.JLabel();
        lbl_start2 = new javax.swing.JLabel();
        lbl_start4 = new javax.swing.JLabel();
        lbl_start5 = new javax.swing.JLabel();
        btn_clear_valoracion = new javax.swing.JButton();
        btn_submit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_comentarios = new javax.swing.JTextArea();

        panel_content3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        jLabel1.setText("El proposito de este software es darle a nuestros usuarios la mejor experiencia al vender o comprar vehiculos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        jLabel2.setText("de segunda mano");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("Nos gustaria que nos compartieras tu Continous feedback al respecto para poder mejorar en el futuro");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Tu Nombre");

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

        chb_anonimo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        chb_anonimo.setText("Quiero que sea Anonimo");
        chb_anonimo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chb_anonimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_anonimoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Valoracion");

        panelStars.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lbl_start1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_start1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_start1MouseClicked(evt);
            }
        });

        lbl_start3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_start3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_start3MouseClicked(evt);
            }
        });

        lbl_start2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_start2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_start2MouseClicked(evt);
            }
        });

        lbl_start4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_start4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_start4MouseClicked(evt);
            }
        });

        lbl_start5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_start5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_start5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelStarsLayout = new javax.swing.GroupLayout(panelStars);
        panelStars.setLayout(panelStarsLayout);
        panelStarsLayout.setHorizontalGroup(
            panelStarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStarsLayout.createSequentialGroup()
                .addComponent(lbl_start1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_start2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_start3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_start4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_start5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelStarsLayout.setVerticalGroup(
            panelStarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_start1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbl_start3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbl_start2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbl_start4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbl_start5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_clear_valoracion.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_clear_valoracion.setText("Limpiar Valoracion");
        btn_clear_valoracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clear_valoracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_valoracionActionPerformed(evt);
            }
        });

        btn_submit.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_submit.setText("Submit");
        btn_submit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Comentarios");

        txa_comentarios.setColumns(20);
        txa_comentarios.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txa_comentarios.setRows(5);
        txa_comentarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txa_comentariosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txa_comentarios);

        javax.swing.GroupLayout panel_content3Layout = new javax.swing.GroupLayout(panel_content3);
        panel_content3.setLayout(panel_content3Layout);
        panel_content3Layout.setHorizontalGroup(
            panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_content3Layout.createSequentialGroup()
                .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_content3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addGroup(panel_content3Layout.createSequentialGroup()
                                .addGap(455, 455, 455)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel_content3Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_content3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_content3Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_content3Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(panelStars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_clear_valoracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(chb_anonimo))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panel_content3Layout.setVerticalGroup(
            panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_content3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_content3Layout.createSequentialGroup()
                        .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chb_anonimo)
                        .addGap(18, 18, 18)
                        .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_clear_valoracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(panelStars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_content3Layout.createSequentialGroup()
                        .addGroup(panel_content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_submit, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_content3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(panel_content3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void chb_anonimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_anonimoActionPerformed
        if (chb_anonimo.isSelected()) {
            txt_nombre.setText(null);
            txt_nombre.setEnabled(false);
        } else {
            txt_nombre.setEnabled(true);
        }
    }//GEN-LAST:event_chb_anonimoActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        submit();
    }//GEN-LAST:event_btn_submitActionPerformed

    private void lbl_start1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_start1MouseClicked
        lbl_start1.setIcon((Icon) Constants.STAR_FILLED);
        //empty the last 4 starts
        lbl_start2.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start3.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        valoracion = 1;
    }//GEN-LAST:event_lbl_start1MouseClicked

    private void lbl_start2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_start2MouseClicked
        lbl_start1.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start2.setIcon((Icon) Constants.STAR_FILLED);
        //empty the last 3 starts
        lbl_start3.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        valoracion = 2;
    }//GEN-LAST:event_lbl_start2MouseClicked

    private void lbl_start3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_start3MouseClicked
        lbl_start1.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start2.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start3.setIcon((Icon) Constants.STAR_FILLED);
        //empty the last 2 starts
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        valoracion = 3;
    }//GEN-LAST:event_lbl_start3MouseClicked

    private void lbl_start4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_start4MouseClicked
        lbl_start1.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start2.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start3.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start4.setIcon((Icon) Constants.STAR_FILLED);
        //empty the last start
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        valoracion = 4;
    }//GEN-LAST:event_lbl_start4MouseClicked

    private void lbl_start5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_start5MouseClicked
        lbl_start1.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start2.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start3.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start4.setIcon((Icon) Constants.STAR_FILLED);
        lbl_start5.setIcon((Icon) Constants.STAR_FILLED);
        valoracion = 5;
    }//GEN-LAST:event_lbl_start5MouseClicked

    private void btn_clear_valoracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_valoracionActionPerformed
        lbl_start1.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start2.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start3.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start4.setIcon((Icon) Constants.STAR_EMPTY);
        lbl_start5.setIcon((Icon) Constants.STAR_EMPTY);
        valoracion = 0;
    }//GEN-LAST:event_btn_clear_valoracionActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        if (txt_nombre.getText().length() == 57 || evt.getKeyChar() == '\'') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txa_comentariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txa_comentariosKeyTyped
        if (txa_comentarios.getText().length() == 245 || evt.getKeyChar() == '\'') {
            evt.consume();
        }
    }//GEN-LAST:event_txa_comentariosKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear_valoracion;
    private javax.swing.JButton btn_submit;
    private javax.swing.JCheckBox chb_anonimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_start1;
    private javax.swing.JLabel lbl_start2;
    private javax.swing.JLabel lbl_start3;
    private javax.swing.JLabel lbl_start4;
    private javax.swing.JLabel lbl_start5;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelStars;
    private javax.swing.JPanel panel_content3;
    private javax.swing.JTextArea txa_comentarios;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
