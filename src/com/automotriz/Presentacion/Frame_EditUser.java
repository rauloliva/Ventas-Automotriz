package com.automotriz.Presentacion;

import com.automotriz.VO.UsuarioVO;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Frame_EditUser extends javax.swing.JDialog {

    private UsuarioVO usuario;

    public Frame_EditUser(java.awt.Frame parent, boolean modal, UsuarioVO usuario) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Editar Usuario");
        this.usuario = usuario;
        initFrame();
    }

    private void initFrame() {
        String name = ReadProperties.props.getProperty("name.EditUser");
        this.setName(name);
        this.setTitle(name);
        Logger.log("Starting " + this.getName() + " frame...");
        lbl_username.setText("Usuario: " + usuario.getUsuario().toUpperCase());

        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        //fill the combo box
        loadEstatus();
        cmb_estatus.setSelectedItem(usuario.getEstatus());

        btn_save.setText(ReadProperties.props.getProperty("btn.saveUser"));

        prepareForm();
    }

    private void loadEstatus() {
        String[] perfiles = ReadProperties.props.getProperty("app.estatus").split(";");
        for (String perfil : perfiles) {
            cmb_estatus.addItem(perfil);
        }
    }

    private void prepareForm() {
        switch (usuario.getEstatus()) {
            case "ACTIVO":
                lbl_aviso.setText(ReadProperties.props.getProperty("editUser.msg.activo"));
                lbl_aviso.setForeground(Color.decode(ReadProperties.props.getProperty("color.verde")).darker());
                btn_generarCodigo.setEnabled(false);
                break;
            case "BLOCKED":
                lbl_aviso.setText(ReadProperties.props.getProperty("editUser.msg.blocked"));
                lbl_aviso.setForeground(Color.decode(ReadProperties.props.getProperty("color.red")).darker());
                break;
            case "DISABLED":
                lbl_aviso.setText(ReadProperties.props.getProperty("editUser.msg.disabled"));
                lbl_aviso.setForeground(Color.decode(ReadProperties.props.getProperty("color.red")).darker());
                btn_generarCodigo.setEnabled(false);
        }
    }

    private void generarCodigo() {
        int codigo = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
        txt_codigo.setText("" + codigo);
    }

    private void saveUser() {
        Validacion validacion = new Validacion(new Object[]{
            new Hashing(txt_codigo.getText()).encrypt(),
            usuario.getUsuario(),
            cmb_estatus.getSelectedItem().toString(),
            usuario.getEstatus().toUpperCase(),
            usuario.getCorreo()
        }).updateUserAsAdmin();

        HashMap propsMessage = validacion.getMessage();
        if (propsMessage != null) {
            JOptionPane.showMessageDialog(
                    this,
                    propsMessage.get("message"),
                    propsMessage.get("title").toString(),
                    Integer.parseInt(propsMessage.get("type").toString()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        cmb_estatus = new javax.swing.JComboBox();
        btn_save = new javax.swing.JButton();
        lbl_aviso = new javax.swing.JLabel();
        btn_generarCodigo = new javax.swing.JButton();
        jLabel138 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        lbl_username = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel141.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel141.setText("Estatus");

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_save.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btn_save.setText("jButton1");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        lbl_aviso.setBackground(new java.awt.Color(255, 255, 255));
        lbl_aviso.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_aviso.setText("jLabel136");

        btn_generarCodigo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_generarCodigo.setText("Generar Codigo");
        btn_generarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarCodigoActionPerformed(evt);
            }
        });

        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel138.setText("Esta opcion genera un codigo que servira de contraseña para el");

        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel140.setText("usuario que lo requiera");

        txt_codigo.setEditable(false);
        txt_codigo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel141)
                        .addGap(97, 97, 97)
                        .addComponent(cmb_estatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel140)
                            .addComponent(jLabel138))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_generarCodigo)
                        .addGap(26, 26, 26)
                        .addComponent(txt_codigo)))
                .addGap(57, 57, 57))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_aviso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_aviso)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_estatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_generarCodigo)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel138)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel140)
                .addGap(37, 37, 37)
                .addComponent(btn_save)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        lbl_username.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        saveUser();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_generarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarCodigoActionPerformed
        generarCodigo();
    }//GEN-LAST:event_btn_generarCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generarCodigo;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox cmb_estatus;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_aviso;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables
}
