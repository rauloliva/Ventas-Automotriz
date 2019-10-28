package com.automotriz.Presentacion;

import com.automotriz.VO.AutoVO;
import com.automotriz.VO.Session;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Frame_MyCars extends javax.swing.JDialog {

    private JFrame parent;
    private JDesktopPane container;
    private Session session;
    private DefaultTableModel model;
    private ArrayList<AutoVO> autosVO;

    public Frame_MyCars(java.awt.Frame parent, boolean modal, JDesktopPane container, Session session) {
        super(parent, modal);
        initComponents();
        this.parent = (JFrame) parent;
        this.container = container;
        this.session = session;
        setLocationRelativeTo(null);
        initFrame();
    }

    private void initFrame() {
        String name = ReadProperties.props.getProperty("name.MyCars");
        this.setName(name);
        this.setTitle(name);
        Logger.log("Starting " + this.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        initTable();
        getCars();
    }

    private void initTable() {
        tbl_cars.setRowHeight(35);
        tbl_cars.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
    }

    private void getCars() {
        setTable();
        model = (DefaultTableModel) tbl_cars.getModel();
        Validacion validacion = new Validacion(new Object[]{session.getId()}, new AutoVO());

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(443, Short.MAX_VALUE))
            .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelContentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(458, Short.MAX_VALUE))
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
        container.removeAll();
        container.add(new Frame_Vender(parent, container, session, getSelectedCar()));
    }//GEN-LAST:event_tbl_carsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelContent;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tbl_cars;
    // End of variables declaration//GEN-END:variables
}
