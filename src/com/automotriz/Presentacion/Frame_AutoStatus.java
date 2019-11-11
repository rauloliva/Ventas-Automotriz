package com.automotriz.Presentacion;

import com.automotriz.Constantes.Constants;
import static com.automotriz.Constantes.Global.global;
import com.automotriz.VO.AutoVO;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class Frame_AutoStatus extends javax.swing.JDialog implements Constants<Frame_AutoStatus> {

    private DefaultTableModel model;
    private ArrayList<AutoVO> autosVO;

    public Frame_AutoStatus(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initFrame(this);
    }

    @Override
    public void initFrame(Frame_AutoStatus c) {
        setLocationRelativeTo(null);
        Constants.metohds.setCloseIcon(lbl_close, this);
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        initTable();
        setAutoEstatus();
        boolean existCars = getCars();
        setVisible(existCars);
    }
    
    private void setAutoEstatus(){
        cmb_estatus.addItem("--Seleccionar--");
        String estatus[] = ReadProperties.props.getProperty("auto.status").split(";");
        for (String e : estatus) {
            cmb_estatus.addItem(e);
        }
    }

    private void initTable() {
        tbl_cars.setRowHeight(35);
        tbl_cars.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
    }

    private boolean getCars() {
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
            return false;
        } else {
            tbl_cars.setModel(validacion.getTableModel());
            scrollTable.setVisible(true);
            pack();
            //getting all the autosVo
            autosVO = validacion.getAutos();
            setVisible(true);
            return true;
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

        jPanel1 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();
        lbl_title_frame = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        tbl_cars = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cmb_estatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(715, 50));

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

        lbl_title_frame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_title_frame.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title_frame.setText("Estatus de mis Vehiculos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title_frame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_close, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title_frame, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Estatus");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setText("Eliminar Auto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_estatus, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_estatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tbl_carsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_carsMouseClicked
        String estatus = getSelectedCar().getEstatus();
        cmb_estatus.setSelectedItem(estatus);
    }//GEN-LAST:event_tbl_carsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int option = JOptionPane.showOptionDialog(this,
                ReadProperties.props.getProperty("msg.delete.auto"),
                ReadProperties.props.getProperty("msg.delete.auto.title"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Continuar", "NO"}, "NO");

        if (option == JOptionPane.YES_OPTION) {
            Validacion validacion = new Validacion(new Object[]{
                getSelectedCar().getId()
            }).deleteAuto();
            
            HashMap props = validacion.getMessage();
            if(props != null){
                JOptionPane.showMessageDialog(this,
                    props.get("message").toString(),
                    props.get("title").toString(),
                    Integer.parseInt(props.get("type").toString()));
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_title_frame;
    private javax.swing.JPanel panelContent;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tbl_cars;
    // End of variables declaration//GEN-END:variables
}
