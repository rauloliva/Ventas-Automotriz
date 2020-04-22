package com.automotriz.Presentacion;

import com.automotriz.VO.UsuarioVO;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.Constantes.Constants;

public class Frame_Usuarios extends javax.swing.JInternalFrame implements Constants<Frame_Usuarios> {

    private DefaultTableModel model;
    private ArrayList<UsuarioVO> usuariosVO;

    public Frame_Usuarios() {
        initComponents();
        setVisible(true);
        initFrame(this);
    }

    private void goToPerfil() {
        this.dispose();
        global.getContainer().add(new Frame_Perfil());
    }

    @Override
    public void initFrame(Frame_Usuarios c) {
        String name = ReadProperties.props.getProperty("name.Usuarios");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        //load all the estatus available in the app
        loadEstatus();
        //load all the profiles
        loadPerfiles();

        initTable();

        enableFiltros(false);
        btn_listarTodo.setEnabled(true);
    }

    private void initTable() {
        scrollTable.setVisible(false);
        tbl_usuarios.setRowHeight(30);
        resetTable();
        tbl_usuarios.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
    }

    private void loadEstatus() {
        String appEstatus[] = ReadProperties.props.getProperty("app.estatus").split(";");
        cmb_estatus.addItem("--Seleccionar--");
        for (String estatus : appEstatus) {
            cmb_estatus.addItem(estatus);
        }
    }

    private void loadPerfiles() {
        cmb_perfil.addItem("--Seleccionar--");
        String[] perfiles = ReadProperties.props.getProperty("app.perfiles").split(";");
        for (String perfil : perfiles) {
            cmb_perfil.addItem(perfil);
        }
    }

    private void enableFiltros(boolean flag) {
        txt_username.setEditable(flag);
        txt_telefono.setEditable(flag);
        cmb_estatus.setEnabled(flag);
        cmb_perfil.setEnabled(flag);
        btn_filtrar.setEnabled(flag);
    }

    private void resetTable() {
        DefaultTableModel m = new DefaultTableModel(new Object[]{
            "Usuario",
            "Correo",
            "Perfil",
            "Estatus",
            "Telefon"
        }, 0);
        tbl_usuarios.setModel(m);
        model = (DefaultTableModel) tbl_usuarios.getModel();
    }

    public void exportCSV() throws Exception{
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(
                ReadProperties.props.getProperty("usuario.csv.name")));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            //getting the users from database
            Validacion validacion = new Validacion(null);
            validacion.filtrarUsuarios(null);
            ArrayList<UsuarioVO> usuarioVO = validacion.getUsuarios();
            List<String[][]> data = UsuarioVO.usuariosAsMatrix(usuarioVO);

            //filling the CSV with the users data
            Excel csv = new Excel(chooser.getSelectedFile().getAbsolutePath());
            csv.setHeader(new String[]{
                "Usuario", "Correo", "Perfil", "Estatus", "Telefono"
            });
            csv.setData(data);
            if (csv.writeCSV()) {
                JOptionPane.showMessageDialog(
                        this,
                        ReadProperties.props.getProperty("msg.export.csv"),
                        ReadProperties.props.getProperty("msg.export.csv.title"),
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        ReadProperties.props.getProperty("msg.export.csv.failed"),
                        ReadProperties.props.getProperty("msg.export.csv.failed.title"),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void generateReport() {
        new Validacion(null).requestReport("Usuarios");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        panel_filtros = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmb_perfil = new javax.swing.JComboBox();
        txt_telefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_estatus = new javax.swing.JComboBox();
        btn_filtrar = new javax.swing.JButton();
        chb_filtar = new javax.swing.JCheckBox();
        btn_listarTodo = new javax.swing.JButton();
        btn_cleanFields = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        tbl_usuarios = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        panel_filtros.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Usuario");

        txt_username.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Perfil");

        cmb_perfil.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txt_telefono.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Telefono");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Estatus");

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_filtrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_filtrar.setText("Filtrar");
        btn_filtrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarActionPerformed(evt);
            }
        });

        chb_filtar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chb_filtar.setText("Usar Filtros");
        chb_filtar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chb_filtar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_filtarActionPerformed(evt);
            }
        });

        btn_listarTodo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_listarTodo.setText("Listar Todo");
        btn_listarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_listarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarTodoActionPerformed(evt);
            }
        });

        btn_cleanFields.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_cleanFields.setText("Limpiar");
        btn_cleanFields.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cleanFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanFieldsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_filtrosLayout = new javax.swing.GroupLayout(panel_filtros);
        panel_filtros.setLayout(panel_filtrosLayout);
        panel_filtrosLayout.setHorizontalGroup(
            panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtrosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_username)
                    .addComponent(cmb_estatus, 0, 269, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_filtrosLayout.createSequentialGroup()
                        .addComponent(btn_filtrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chb_filtar))
                    .addComponent(cmb_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtrosLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_filtrosLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btn_listarTodo)
                        .addGap(44, 44, 44)
                        .addComponent(btn_cleanFields)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panel_filtrosLayout.setVerticalGroup(
            panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(panel_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_estatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_filtrar)
                    .addComponent(chb_filtar)
                    .addComponent(btn_listarTodo)
                    .addComponent(btn_cleanFields))
                .addGap(20, 20, 20))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Filtros");

        scrollTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbl_usuarios.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbl_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usuariosMouseClicked(evt);
            }
        });
        scrollTable.setViewportView(tbl_usuarios);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(panel_filtros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollTable))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_filtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jMenu1.setText("Perfil");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/perfil.png"))); // NOI18N
        jMenuItem1.setText("Ver mi Perfil");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/reporte.png"))); // NOI18N
        jMenuItem2.setText("Generar Reporte");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/download.png"))); // NOI18N
        jMenuItem3.setText("Exportar CSV");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

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
        goToPerfil();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        if (!(((int) evt.getKeyChar()) >= 48 && ((int) evt.getKeyChar()) <= 57)) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void chb_filtarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_filtarActionPerformed
        if (chb_filtar.isSelected()) {
            enableFiltros(true);
            btn_listarTodo.setEnabled(false);
        } else {
            enableFiltros(false);
            btn_listarTodo.setEnabled(true);
        }
    }//GEN-LAST:event_chb_filtarActionPerformed

    private void btn_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarActionPerformed
        try{
            resetTable();
            Validacion validacion = new Validacion(new Object[]{
                txt_username.getText().trim(),
                txt_telefono.getText().trim(),
                cmb_estatus.getSelectedItem().toString() == "--Seleccionar--"
                ? cmb_estatus.getSelectedItem().toString() : cmb_estatus.getSelectedItem().toString().toUpperCase(),
                cmb_perfil.getSelectedItem().toString()
            });
            //validacion.setTableModel(model);
            model = validacion.filtrarUsuarios(model);
            tbl_usuarios.setModel(model);

            if (model == null) { //if an error message is ready to show up
                //if there is no user, dont show the table
                scrollTable.setVisible(false);
            } else {
                //show the table with the data
                scrollTable.setVisible(true);
                pack();
                usuariosVO = validacion.getUsuarios();
            }
        }catch(Exception e){
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }//GEN-LAST:event_btn_filtrarActionPerformed

    private void btn_listarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarTodoActionPerformed
        try{
            resetTable();
            Validacion validacion = new Validacion(null);
            model = validacion.filtrarUsuarios(model);
            tbl_usuarios.setModel(model);
            usuariosVO = validacion.getUsuarios();
            scrollTable.setVisible(true);
            pack();
        }catch(Exception e){
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }//GEN-LAST:event_btn_listarTodoActionPerformed

    private void btn_cleanFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanFieldsActionPerformed
        resetTable();
        scrollTable.setVisible(false);
        txt_username.setText(null);
        txt_telefono.setText(null);
        cmb_estatus.setSelectedItem("--Seleccionar--");
        cmb_perfil.setSelectedItem("--Seleccionar--");
    }//GEN-LAST:event_btn_cleanFieldsActionPerformed

    private UsuarioVO getUser() {
        JTable t = tbl_usuarios;
        int row = t.getSelectedRow();
        return usuariosVO.get(row);
    }

    private void tbl_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usuariosMouseClicked
        Frame_EditUser e = new Frame_EditUser(null, true, getUser());
        e.setLocationRelativeTo(this);
        e.setVisible(true);
    }//GEN-LAST:event_tbl_usuariosMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        generateReport();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            exportCSV();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cleanFields;
    private javax.swing.JButton btn_filtrar;
    private javax.swing.JButton btn_listarTodo;
    private javax.swing.JCheckBox chb_filtar;
    private javax.swing.JComboBox cmb_estatus;
    private javax.swing.JComboBox cmb_perfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tbl_usuarios;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
