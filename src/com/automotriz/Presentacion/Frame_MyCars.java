package com.automotriz.Presentacion;

import static com.automotriz.Constantes.Global.global;
import com.automotriz.VO.AutoVO;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.automotriz.Constantes.Constants;
import javax.swing.border.BevelBorder;

public class Frame_MyCars extends javax.swing.JDialog implements Constants<Frame_MyCars> {

    private DefaultTableModel model;
    private ArrayList<AutoVO> autosVO;

    public Frame_MyCars(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_MyCars c) {
        String name = ReadProperties.props.getProperty("name.MyCars");
        c.setName(name);
        c.setTitle(name);
        lbl_title_frame.setText(name);
        Logger.log("Starting " + c.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        initTable();
        getCars();
        Constants.metohds.setCloseIcon(lbl_close, c);
    }

    private void initTable() {
        tbl_cars.setRowHeight(35);
        tbl_cars.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
    }

    private void getCars() {
        setTable();
        model = (DefaultTableModel) tbl_cars.getModel();
        Validacion validacion = new Validacion(new Object[]{global.getSession().getId()}, new AutoVO());

        validacion.setTableModel(model);
        validacion.listUserAutos();
        model = validacion.getTableModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    ReadProperties.props.getProperty("vender.msg.adv.noCars"),
                    ReadProperties.props.getProperty("vender.msg.adv.noCars.title"),
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            tbl_cars.setModel(validacion.getTableModel());
            scrollTable.setVisible(true);
            pack();
            //getting all the autosVo
            autosVO = validacion.getAutos();
            setVisible(true);
        }
    }

    private void setTable() {
        DefaultTableModel m = new DefaultTableModel(new Object[]{
            "ID",
            "Marca",
            "Modelo",
            "Precio",
            "Color",
            "Estatus"
        }, 0);
        tbl_cars.setModel(m);
        model = (DefaultTableModel) tbl_cars.getModel();
    }

    private AutoVO getSelectedCar() {
        AutoVO auto = autosVO.get(tbl_cars.getSelectedRow());
        return auto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        tbl_cars = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_title_frame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelContent.setBackground(new java.awt.Color(238, 238, 238));
        panelContent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        scrollTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbl_cars.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbl_cars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_cars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_carsMouseClicked(evt);
            }
        });
        scrollTable.setViewportView(tbl_cars);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Selecciona una fila para editar en el formulario");

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

        lbl_title_frame.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_title_frame.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title_frame.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title_frame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title_frame)
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(lbl_close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(443, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelContentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(451, Short.MAX_VALUE))
            .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                    .addContainerGap(41, Short.MAX_VALUE)
                    .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
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

    private void tbl_carsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_carsMouseClicked
        this.dispose();
        global.getContainer().removeAll();
        global.getContainer().add(new Frame_Vender(getSelectedCar()));
    }//GEN-LAST:event_tbl_carsMouseClicked

    private void lbl_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseClicked
        this.dispose();
    }//GEN-LAST:event_lbl_closeMouseClicked

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        lbl_close.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_lbl_closeMousePressed

    private void lbl_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMouseReleased
        lbl_close.setBorder(null);
    }//GEN-LAST:event_lbl_closeMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_title_frame;
    private javax.swing.JPanel panelContent;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tbl_cars;
    // End of variables declaration//GEN-END:variables
}
