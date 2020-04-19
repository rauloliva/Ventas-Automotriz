package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.*;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.Constantes.Constants;

public class Frame_Perfil extends javax.swing.JInternalFrame implements Constants<Frame_Perfil> {

    public Frame_Perfil() {
        initComponents();
        setVisible(true);
        initFrame(this);
    }

    private void editarCampos(boolean flag) {
        txt_username.setEditable(flag);
        txt_name.setEditable(flag);
        txt_password.setEditable(flag);
        txt_correo.setEditable(flag);
        txt_telefono.setEditable(flag);
        btn_saveChanges.setEnabled(flag);
    }

    @Override
    public void initFrame(Frame_Perfil c) {
        String name = ReadProperties.props.getProperty("name.Perfil");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        c.panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        //disable all the fields
        editarCampos(false);

        loadSession();
        if (global.getSession().getPerfil().equals("Administrador")) {
            //creating a new Menu item object 
            JMenuItem usuariosOption = new JMenuItem();
            usuariosOption.setText("Usuarios");
            usuariosOption.setIcon(new ImageIcon(this.getClass().getResource(ReadProperties.props.getProperty("icon.lista"))));
            usuariosOption.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            usuariosOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
            usuariosOption.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    verUsuarios();
                }
            });
            usuariosOption.setVisible(true);
            menu_options.add(usuariosOption);
        }

        btn_saveChanges.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.guardar")))
                                .getImage()
                                .getScaledInstance(35, 35, Image.SCALE_DEFAULT)
                )
        );
    }

    private void loadSession() {
        txt_username.setText(global.getSession().getUsername());
        txt_name.setText(global.getSession().getNombre());
        txt_password.setText(new Hashing(global.getSession().getPassword()).decrypt());
        txt_correo.setText(global.getSession().getMail());
        txt_telefono.setText(global.getSession().getTelefono());
    }

    private void validateInputLength(JTextField field, java.awt.event.KeyEvent evt, String props) {
        Validacion validacion = new Validacion(new Object[]{
            field.getText().trim(),
            ReadProperties.props.getProperty(props),
            evt
        }).validateInputLength();

        HashMap propMensaje = validacion.getMessage();
        if (propMensaje != null) {
            JOptionPane.showMessageDialog(this,
                    propMensaje.get("message").toString(),
                    propMensaje.get("title").toString(),
                    Integer.parseInt(propMensaje.get("type").toString()));
        }
    }

    private void updateUser() {
        Validacion validacion = new Validacion(new Object[]{
            txt_username.getText().trim(),
            new Hashing(txt_password.getText().trim()).encrypt(),
            txt_correo.getText().trim(),
            global.getSession().getPerfil(),
            txt_telefono.getText(),
            txt_name.getText().trim()
        }).validateForm("the update user", Constants.UPDATEUSER);

        HashMap propMensaje = validacion.getMessage();

        if (propMensaje != null) { //if a message is ready to show up
            int typeOfMsg;
            JOptionPane.showMessageDialog(this,
                    propMensaje.get("message").toString(),
                    propMensaje.get("title").toString(),
                    typeOfMsg = Integer.parseInt(propMensaje.get("type").toString()));
            if (typeOfMsg == JOptionPane.INFORMATION_MESSAGE) {
                //message to specify to go back to log in
                JOptionPane.showMessageDialog(this,
                        ReadProperties.props.getProperty("perfil.msg.sessionOver"),
                        ReadProperties.props.getProperty("perfil.msg.sessionOver.title"),
                        JOptionPane.INFORMATION_MESSAGE);
                global.getParent().dispose();
                Frame_LogIn.main(null);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    ReadProperties.props.getProperty("perfil.msg.error.userUpdate"),
                    ReadProperties.props.getProperty("perfil.msg.error.userUpdate.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAccount() {
        int option = JOptionPane.showOptionDialog(this,
                ReadProperties.props.getProperty("perfil.msg.question.userDelete"),
                ReadProperties.props.getProperty("perfil.msg.question.userDelete.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"SI", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            Validacion validacion = new Validacion(new Object[]{
                global.getSession().getUsername()
            }).removeUser();

            HashMap message = validacion.getMessage();
            if (message != null) {
                JOptionPane.showMessageDialog(this,
                        message.get("message").toString(),
                        message.get("title").toString(),
                        Integer.parseInt(message.get("type").toString()));

                //Close the session
                global.getParent().dispose();
                Frame_LogIn.main(null);
            } else {
                JOptionPane.showMessageDialog(this,
                        ReadProperties.props.getProperty("perfil.msg.error.userDelete"),
                        ReadProperties.props.getProperty("perfil.msg.error.userDelete.title"),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void goToInicio() {
        this.dispose();
        if (global.getSession().getPerfil().equals("Administrador")) {
            global.getContainer().add(new Frame_Graph());
        } else {
            global.getContainer().add(new Frame_InicioCliente());
        }
    }

    private void verUsuarios() {
        this.dispose();
        global.getContainer().add(new Frame_Usuarios());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chb_editarCampos = new javax.swing.JCheckBox();
        btn_saveChanges = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_options = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_username.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_usernameKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel2.setText("Contraseña");

        txt_password.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_passwordKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel3.setText("Correo");

        txt_correo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_correo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });

        txt_telefono.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel5.setText("Telefono");

        chb_editarCampos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chb_editarCampos.setText("Editar Campos");
        chb_editarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_editarCamposActionPerformed(evt);
            }
        });

        btn_saveChanges.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_saveChanges.setText("Guardar Cambios");
        btn_saveChanges.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_saveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveChangesActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel6.setText("Nombre");

        txt_name.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb_editarCampos)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(30, 30, 30))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGap(56, 56, 56))))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_saveChanges)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_correo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(chb_editarCampos)
                .addGap(18, 18, 18)
                .addComponent(btn_saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        menu_options.setText("Opciones");
        menu_options.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_options.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/eliminar.png"))); // NOI18N
        jMenuItem2.setText("Eliminar Cuenta");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_options.add(jMenuItem2);

        jMenuBar1.add(menu_options);

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

    private void txt_usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usernameKeyTyped
        validateInputLength(txt_username, evt, "signin.length.username");
    }//GEN-LAST:event_txt_usernameKeyTyped

    private void txt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyTyped
        validateInputLength(txt_password, evt, "signin.length.contrasena");
    }//GEN-LAST:event_txt_passwordKeyTyped

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        validateInputLength(txt_correo, evt, "signin.length.correo");
    }//GEN-LAST:event_txt_correoKeyTyped

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        validateInputLength(txt_telefono, evt, "signin.length.telefono");
    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void chb_editarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_editarCamposActionPerformed
        editarCampos(chb_editarCampos.isSelected());
    }//GEN-LAST:event_chb_editarCamposActionPerformed

    private void btn_saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveChangesActionPerformed
        updateUser();
    }//GEN-LAST:event_btn_saveChangesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        deleteAccount();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txt_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyTyped
        validateInputLength(txt_name, evt, "signin.length.name");
    }//GEN-LAST:event_txt_nameKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_saveChanges;
    private javax.swing.JCheckBox chb_editarCampos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menu_options;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_name;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
