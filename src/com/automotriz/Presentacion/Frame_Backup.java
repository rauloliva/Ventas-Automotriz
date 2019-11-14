package com.automotriz.Presentacion;

import com.automotriz.Constantes.Constants;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Frame_Backup extends javax.swing.JDialog implements Constants<Frame_Backup> {

    private Zip zip = new Zip();
    private DefaultTableModel model;
    private HashMap dataObjs;

    public Frame_Backup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_Backup c) {
        setTableProps();
        c.setLocationRelativeTo(null);
        String name = ReadProperties.props.getProperty("name.backup");
        c.setTitle(name);
        lbl_title.setText(name);
        //setting the zip name
        lbl_zipName.setText(ReadProperties.props.getProperty("zip.name"));
        Constants.metohds.setCloseIcon(lbl_close, this);
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        setDefaultPath();
        Constants.metohds.setIconToButton(this, btn_select_path, "icon.select.folder", 35, 35);
        Constants.metohds.setIconToButton(this, btn_resaldar, "icon.backup", 35, 35);
        getDatabaseTables();
        c.setVisible(true);
    }

    private void setTableProps() {
        model = (DefaultTableModel) tbl_tables.getModel();
        tbl_tables.setRowHeight(30);
        TableColumnModel tb = tbl_tables.getColumnModel();
        tb.getColumn(0).setPreferredWidth(50);
        tb.getColumn(1).setPreferredWidth(190);
    }

    private void setDefaultPath() {
        String defaultPath = new JFileChooser().getCurrentDirectory().getAbsolutePath();
        lbl_path.setText(defaultPath);
    }

    private void getDatabaseTables() {
        dataObjs = new Validacion(null).requestDatabaseTables();
        for (Object table : dataObjs.keySet()) {
            model.addRow(new Object[]{false, table});
        }
        tbl_tables.setModel(model);
    }

    private void selectPath() throws Exception {
        zip.newZip();
        lbl_path.setText(zip.getZipPath());
    }

    private void aplicarRespaldo() throws Exception {
        for (int i = 0; i < tbl_tables.getRowCount(); i++) {
            //when the check cell is selected
            if (Boolean.parseBoolean(tbl_tables.getValueAt(i, 0).toString())) {
                String tableName = tbl_tables.getValueAt(i, 1).toString();
                if (((Object[]) dataObjs.get(tableName)).length > 0) {
                    zip.newFile(tableName, dataObjs.get(tableName));
                }
            }
        }
        zip.createZip();

        JOptionPane.showMessageDialog(this,
                ReadProperties.props.getProperty("msg.zip.exported"),
                ReadProperties.props.getProperty("msg.zip.exported.title"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_title = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_zipName = new javax.swing.JLabel();
        btn_select_path = new javax.swing.JButton();
        lbl_path = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tables = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btn_resaldar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        lbl_close.setToolTipText("Cerrar la Aplicacion");
        lbl_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_closeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_closeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl_closeMouseReleased(evt);
            }
        });

        lbl_title.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_close, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Este modulo permite realizar una copia de datos capturados en la base de datos del sistema");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Tener en cuenta que se requeriran permisos de administrador que cuente con el acceso correspondiente");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setText("Nombre del ZIP:");

        lbl_zipName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_zipName.setText("jLabel4");

        btn_select_path.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_select_path.setText("Seleccionar Ruta");
        btn_select_path.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_select_path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_select_pathActionPerformed(evt);
            }
        });

        lbl_path.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_path.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Esta sera la ruta donde se guardara el archivo zip, puede seleccionar otra ruta");

        tbl_tables.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tbl_tables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Check", "Tabla"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_tables);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Marca la casilla 'Check' para aplicar el respaldo a la tabla");

        btn_resaldar.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btn_resaldar.setText("Aplicar Respaldo");
        btn_resaldar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_resaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resaldarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(btn_select_path)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lbl_path, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_zipName))
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(btn_resaldar))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_zipName))
                .addGap(18, 18, 18)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_select_path, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_path))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(35, 35, 35)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(btn_resaldar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        this.dispose();
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        lbl_close.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_lbl_closeMousePressed

    private void lbl_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseReleased
        lbl_close.setBorder(null);
    }//GEN-LAST:event_lbl_closeMouseReleased

    private void btn_select_pathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_select_pathActionPerformed
        try {
            selectPath();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }//GEN-LAST:event_btn_select_pathActionPerformed

    private void btn_resaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resaldarActionPerformed
        try {
            if (new Validacion(null).askAdminRights()) {
                aplicarRespaldo();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            JOptionPane.showMessageDialog(this,
                    ReadProperties.props.getProperty("msg.zip.exported"),
                    ReadProperties.props.getProperty("msg.zip.exported.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_resaldarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_resaldar;
    private javax.swing.JButton btn_select_path;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_path;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_zipName;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTable tbl_tables;
    // End of variables declaration//GEN-END:variables
}
