package com.automotriz.Presentacion;

import com.automotriz.VO.AutoVO;
import com.automotriz.VO.Session;
import java.awt.Color;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Frame_Catalogo extends javax.swing.JInternalFrame implements Runnable {

    private JFrame parent;
    private JDesktopPane container;
    private Session session;
    private List<AutoVO> autosVO;

    public Frame_Catalogo(JFrame parent, JDesktopPane container, Session session) {
        initComponents();
        this.parent = parent;
        this.container = container;
        this.session = session;
        initFrame();
        this.setVisible(true);
    }

    private void initFrame() {
        panelFiltros.setVisible(false);
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        getCatalogo("");
        //
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (!this.isDisplayable()) {
                DataModel.resetIndice();
                DataModel.resetFirstBack();
                System.out.print("");
                break;
            }
            /*
            somehow putting this line the thread works correctly
             */
            System.out.print("");
        }
    }

    private void getCatalogo(String flag) {
        Validacion validacion = new Validacion(new Object[]{
            session.getId()
        }, new AutoVO()).getCatalogo();
        autosVO = validacion.getAutos();
        if (autosVO != null) {
            //lbl_empty_catalogo.setText(null);
            DataModel model = new DataModel(new Object[][]{
                {lbl_marca1, lbl_modelo1, lbl_precio1, lbl_imagenes1},
                {lbl_marca2, lbl_modelo2, lbl_precio2, lbl_imagenes2},
                {lbl_marca3, lbl_modelo3, lbl_precio3, lbl_imagenes3},
                {lbl_marca4, lbl_modelo4, lbl_precio4, lbl_imagenes4},
                {lbl_marca5, lbl_modelo5, lbl_precio5, lbl_imagenes5},
                {lbl_marca6, lbl_modelo6, lbl_precio6, lbl_imagenes6},
                {lbl_marca7, lbl_modelo7, lbl_precio7, lbl_imagenes7},
                {lbl_marca8, lbl_modelo8, lbl_precio8, lbl_imagenes8},
                {lbl_marca9, lbl_modelo9, lbl_precio9, lbl_imagenes9},
                {lbl_marca10, lbl_modelo10, lbl_precio10, lbl_imagenes10},
                {lbl_marca11, lbl_modelo11, lbl_precio11, lbl_imagenes11},
                {lbl_marca12, lbl_modelo12, lbl_precio12, lbl_imagenes12}
            }, autosVO, btn_atras, btn_siguiente);

            if (flag.equals("")) {
                model.constructCatalogoModel();
            } else if (flag.equals("next")) {
                model.constructNextButton();
            } else {
                model.constructBackBtnComentarios();
            }
        } else {
            btn_atras.setEnabled(false);
            btn_siguiente.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelFiltros = new javax.swing.JPanel();
        btn_atras = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelAuto1 = new javax.swing.JPanel();
        lbl_marca1 = new javax.swing.JLabel();
        lbl_modelo1 = new javax.swing.JLabel();
        lbl_precio1 = new javax.swing.JLabel();
        lbl_imagenes1 = new javax.swing.JLabel();
        panelAuto2 = new javax.swing.JPanel();
        lbl_marca2 = new javax.swing.JLabel();
        lbl_modelo2 = new javax.swing.JLabel();
        lbl_precio2 = new javax.swing.JLabel();
        lbl_imagenes2 = new javax.swing.JLabel();
        panelAuto3 = new javax.swing.JPanel();
        lbl_marca3 = new javax.swing.JLabel();
        lbl_modelo3 = new javax.swing.JLabel();
        lbl_precio3 = new javax.swing.JLabel();
        lbl_imagenes3 = new javax.swing.JLabel();
        panelAuto4 = new javax.swing.JPanel();
        lbl_marca4 = new javax.swing.JLabel();
        lbl_modelo4 = new javax.swing.JLabel();
        lbl_precio4 = new javax.swing.JLabel();
        lbl_imagenes4 = new javax.swing.JLabel();
        panelAuto5 = new javax.swing.JPanel();
        lbl_marca5 = new javax.swing.JLabel();
        lbl_modelo5 = new javax.swing.JLabel();
        lbl_precio5 = new javax.swing.JLabel();
        lbl_imagenes5 = new javax.swing.JLabel();
        panelAuto6 = new javax.swing.JPanel();
        lbl_marca6 = new javax.swing.JLabel();
        lbl_modelo6 = new javax.swing.JLabel();
        lbl_precio6 = new javax.swing.JLabel();
        lbl_imagenes6 = new javax.swing.JLabel();
        panelAuto7 = new javax.swing.JPanel();
        lbl_marca7 = new javax.swing.JLabel();
        lbl_modelo7 = new javax.swing.JLabel();
        lbl_precio7 = new javax.swing.JLabel();
        lbl_imagenes7 = new javax.swing.JLabel();
        panelAuto8 = new javax.swing.JPanel();
        lbl_marca8 = new javax.swing.JLabel();
        lbl_modelo8 = new javax.swing.JLabel();
        lbl_precio8 = new javax.swing.JLabel();
        lbl_imagenes8 = new javax.swing.JLabel();
        panelAuto9 = new javax.swing.JPanel();
        lbl_marca9 = new javax.swing.JLabel();
        lbl_modelo9 = new javax.swing.JLabel();
        lbl_precio9 = new javax.swing.JLabel();
        lbl_imagenes9 = new javax.swing.JLabel();
        panelAuto10 = new javax.swing.JPanel();
        lbl_marca10 = new javax.swing.JLabel();
        lbl_modelo10 = new javax.swing.JLabel();
        lbl_precio10 = new javax.swing.JLabel();
        lbl_imagenes10 = new javax.swing.JLabel();
        panelAuto11 = new javax.swing.JPanel();
        lbl_marca11 = new javax.swing.JLabel();
        lbl_modelo11 = new javax.swing.JLabel();
        lbl_precio11 = new javax.swing.JLabel();
        lbl_imagenes11 = new javax.swing.JLabel();
        panelAuto12 = new javax.swing.JPanel();
        lbl_marca12 = new javax.swing.JLabel();
        lbl_modelo12 = new javax.swing.JLabel();
        lbl_precio12 = new javax.swing.JLabel();
        lbl_imagenes12 = new javax.swing.JLabel();
        btn_filtros = new javax.swing.JButton();

        panelFiltros.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btn_atras.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_atras.setText("Atras");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        btn_siguiente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_siguiente.setText("Siguiente");
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        panelAuto1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto1MouseExited(evt);
            }
        });

        lbl_marca1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca1.setText("Marca:");

        lbl_modelo1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo1.setText("Modelo:");

        lbl_precio1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio1.setText("Precio:");

        lbl_imagenes1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes1.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto1Layout = new javax.swing.GroupLayout(panelAuto1);
        panelAuto1.setLayout(panelAuto1Layout);
        panelAuto1Layout.setHorizontalGroup(
            panelAuto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto1Layout.setVerticalGroup(
            panelAuto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto2MouseExited(evt);
            }
        });

        lbl_marca2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca2.setText("Marca:");

        lbl_modelo2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo2.setText("Modelo:");

        lbl_precio2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio2.setText("Precio:");

        lbl_imagenes2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes2.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto2Layout = new javax.swing.GroupLayout(panelAuto2);
        panelAuto2.setLayout(panelAuto2Layout);
        panelAuto2Layout.setHorizontalGroup(
            panelAuto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto2Layout.setVerticalGroup(
            panelAuto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto3MouseExited(evt);
            }
        });

        lbl_marca3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca3.setText("Marca:");

        lbl_modelo3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo3.setText("Modelo:");

        lbl_precio3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio3.setText("Precio:");

        lbl_imagenes3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes3.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto3Layout = new javax.swing.GroupLayout(panelAuto3);
        panelAuto3.setLayout(panelAuto3Layout);
        panelAuto3Layout.setHorizontalGroup(
            panelAuto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes3, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto3Layout.setVerticalGroup(
            panelAuto3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto4MouseExited(evt);
            }
        });

        lbl_marca4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca4.setText("Marca:");

        lbl_modelo4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo4.setText("Modelo:");

        lbl_precio4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio4.setText("Precio:");

        lbl_imagenes4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes4.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto4Layout = new javax.swing.GroupLayout(panelAuto4);
        panelAuto4.setLayout(panelAuto4Layout);
        panelAuto4Layout.setHorizontalGroup(
            panelAuto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes4, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto4Layout.setVerticalGroup(
            panelAuto4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto5MouseExited(evt);
            }
        });

        lbl_marca5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca5.setText("Marca:");

        lbl_modelo5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo5.setText("Modelo:");

        lbl_precio5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio5.setText("Precio:");

        lbl_imagenes5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes5.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto5Layout = new javax.swing.GroupLayout(panelAuto5);
        panelAuto5.setLayout(panelAuto5Layout);
        panelAuto5Layout.setHorizontalGroup(
            panelAuto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes5, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto5Layout.setVerticalGroup(
            panelAuto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto6MouseExited(evt);
            }
        });

        lbl_marca6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca6.setText("Marca:");

        lbl_modelo6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo6.setText("Modelo:");

        lbl_precio6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio6.setText("Precio:");

        lbl_imagenes6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes6.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto6Layout = new javax.swing.GroupLayout(panelAuto6);
        panelAuto6.setLayout(panelAuto6Layout);
        panelAuto6Layout.setHorizontalGroup(
            panelAuto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes6, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto6Layout.setVerticalGroup(
            panelAuto6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto7MouseExited(evt);
            }
        });

        lbl_marca7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca7.setText("Marca:");

        lbl_modelo7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo7.setText("Modelo:");

        lbl_precio7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio7.setText("Precio:");

        lbl_imagenes7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes7.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto7Layout = new javax.swing.GroupLayout(panelAuto7);
        panelAuto7.setLayout(panelAuto7Layout);
        panelAuto7Layout.setHorizontalGroup(
            panelAuto7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes7, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto7Layout.setVerticalGroup(
            panelAuto7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto8MouseExited(evt);
            }
        });

        lbl_marca8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca8.setText("Marca:");

        lbl_modelo8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo8.setText("Modelo:");

        lbl_precio8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio8.setText("Precio:");

        lbl_imagenes8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes8.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto8Layout = new javax.swing.GroupLayout(panelAuto8);
        panelAuto8.setLayout(panelAuto8Layout);
        panelAuto8Layout.setHorizontalGroup(
            panelAuto8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes8, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto8Layout.setVerticalGroup(
            panelAuto8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto9MouseExited(evt);
            }
        });

        lbl_marca9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca9.setText("Marca:");

        lbl_modelo9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo9.setText("Modelo:");

        lbl_precio9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio9.setText("Precio:");

        lbl_imagenes9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes9.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto9Layout = new javax.swing.GroupLayout(panelAuto9);
        panelAuto9.setLayout(panelAuto9Layout);
        panelAuto9Layout.setHorizontalGroup(
            panelAuto9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes9, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto9Layout.setVerticalGroup(
            panelAuto9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto10MouseExited(evt);
            }
        });

        lbl_marca10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca10.setText("Marca:");

        lbl_modelo10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo10.setText("Modelo:");

        lbl_precio10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio10.setText("Precio:");

        lbl_imagenes10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes10.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto10Layout = new javax.swing.GroupLayout(panelAuto10);
        panelAuto10.setLayout(panelAuto10Layout);
        panelAuto10Layout.setHorizontalGroup(
            panelAuto10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes10, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto10Layout.setVerticalGroup(
            panelAuto10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto11MouseExited(evt);
            }
        });

        lbl_marca11.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca11.setText("Marca:");

        lbl_modelo11.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo11.setText("Modelo:");

        lbl_precio11.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio11.setText("Precio:");

        lbl_imagenes11.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes11.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto11Layout = new javax.swing.GroupLayout(panelAuto11);
        panelAuto11.setLayout(panelAuto11Layout);
        panelAuto11Layout.setHorizontalGroup(
            panelAuto11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes11, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto11Layout.setVerticalGroup(
            panelAuto11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAuto12.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0)));
        panelAuto12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelAuto12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAuto12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelAuto12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelAuto12MouseExited(evt);
            }
        });

        lbl_marca12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_marca12.setText("Marca:");

        lbl_modelo12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_modelo12.setText("Modelo:");

        lbl_precio12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_precio12.setText("Precio:");

        lbl_imagenes12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_imagenes12.setText("Imagenes:");

        javax.swing.GroupLayout panelAuto12Layout = new javax.swing.GroupLayout(panelAuto12);
        panelAuto12.setLayout(panelAuto12Layout);
        panelAuto12Layout.setHorizontalGroup(
            panelAuto12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAuto12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_marca12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_modelo12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_precio12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_imagenes12, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAuto12Layout.setVerticalGroup(
            panelAuto12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAuto12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_marca12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_modelo12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_precio12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_imagenes12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAuto9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAuto10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(panelAuto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelAuto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelAuto4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(panelAuto11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelAuto12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(panelAuto7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelAuto8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(1202, 1202, 1202))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAuto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelAuto5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAuto9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAuto12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        btn_filtros.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btn_filtros.setText("Mostrar Filtros");
        btn_filtros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_filtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btn_siguiente)
                        .addGap(85, 85, 85)
                        .addComponent(btn_filtros)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_atras)
                    .addComponent(btn_siguiente)
                    .addComponent(btn_filtros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        getCatalogo("back");
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        getCatalogo("next");
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void panelAuto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto1MouseClicked
        if (!lbl_marca1.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(0), container, session);
        }
    }//GEN-LAST:event_panelAuto1MouseClicked

    private void panelAuto1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto1MouseEntered
        panelAuto1.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto1MouseEntered

    private void panelAuto1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto1MouseExited
        panelAuto1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto1MouseExited

    private void panelAuto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto2MouseClicked
        if (!lbl_marca2.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(1), container, session);
        }
    }//GEN-LAST:event_panelAuto2MouseClicked

    private void panelAuto2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto2MouseEntered
        panelAuto2.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto2MouseEntered

    private void panelAuto2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto2MouseExited
        panelAuto2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto2MouseExited

    private void panelAuto3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto3MouseClicked
        if (!lbl_marca3.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(2), container, session);
        }
    }//GEN-LAST:event_panelAuto3MouseClicked

    private void panelAuto3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto3MouseEntered
        panelAuto3.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto3MouseEntered

    private void panelAuto3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto3MouseExited
        panelAuto3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto3MouseExited

    private void panelAuto4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto4MouseClicked
        if (!lbl_marca4.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(3), container, session);
        }
    }//GEN-LAST:event_panelAuto4MouseClicked

    private void panelAuto4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto4MouseEntered
        panelAuto4.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto4MouseEntered

    private void panelAuto4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto4MouseExited
        panelAuto4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto4MouseExited

    private void panelAuto5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto5MouseClicked
        if (!lbl_marca5.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(4), container, session);
        }
    }//GEN-LAST:event_panelAuto5MouseClicked

    private void panelAuto5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto5MouseEntered
        panelAuto5.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto5MouseEntered

    private void panelAuto5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto5MouseExited
        panelAuto5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto5MouseExited

    private void panelAuto6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto6MouseClicked
        if (!lbl_marca6.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(5), container, session);
        }
    }//GEN-LAST:event_panelAuto6MouseClicked

    private void panelAuto6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto6MouseEntered
        panelAuto6.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto6MouseEntered

    private void panelAuto6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto6MouseExited
        panelAuto6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto6MouseExited

    private void panelAuto7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto7MouseClicked
        if (!lbl_marca7.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(6), container, session);
        }
    }//GEN-LAST:event_panelAuto7MouseClicked

    private void panelAuto7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto7MouseEntered
        panelAuto7.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto7MouseEntered

    private void panelAuto7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto7MouseExited
        panelAuto7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto7MouseExited

    private void panelAuto8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto8MouseClicked
        if (!lbl_marca8.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(7), container, session);
        }
    }//GEN-LAST:event_panelAuto8MouseClicked

    private void panelAuto8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto8MouseEntered
        panelAuto8.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto8MouseEntered

    private void panelAuto8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto8MouseExited
        panelAuto8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto8MouseExited

    private void panelAuto9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto9MouseClicked
        if (!lbl_marca9.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(8), container, session);
        }
    }//GEN-LAST:event_panelAuto9MouseClicked

    private void panelAuto9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto9MouseEntered
        panelAuto9.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto9MouseEntered

    private void panelAuto9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto9MouseExited
        panelAuto9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto9MouseExited

    private void panelAuto10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto10MouseClicked
        if (!lbl_marca10.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(9), container, session);
        }
    }//GEN-LAST:event_panelAuto10MouseClicked

    private void panelAuto10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto10MouseEntered
        panelAuto10.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto10MouseEntered

    private void panelAuto10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto10MouseExited
        panelAuto10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto10MouseExited

    private void panelAuto11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto11MouseClicked
        if (!lbl_marca11.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(10), container, session);
        }
    }//GEN-LAST:event_panelAuto11MouseClicked

    private void panelAuto11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto11MouseEntered
        panelAuto11.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto11MouseEntered

    private void panelAuto11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto11MouseExited
        panelAuto11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto11MouseExited

    private void panelAuto12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto12MouseClicked
        if (!lbl_marca12.getText().trim().equals("Marca:")) {
            new Frame_AutoInfo(parent, true, autosVO.get(11), container, session);
        }
    }//GEN-LAST:event_panelAuto12MouseClicked

    private void panelAuto12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto12MouseEntered
        panelAuto12.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }//GEN-LAST:event_panelAuto12MouseEntered

    private void panelAuto12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAuto12MouseExited
        panelAuto12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
    }//GEN-LAST:event_panelAuto12MouseExited

    private void btn_filtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrosActionPerformed
        if (btn_filtros.getText().equalsIgnoreCase("Mostrar Filtros")) {
            panelFiltros.setVisible(true);
            btn_filtros.setText("Ocultar Filtros");
        } else {
            panelFiltros.setVisible(false);
            btn_filtros.setText("Mostrar Filtros");
        }
    }//GEN-LAST:event_btn_filtrosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_filtros;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_imagenes1;
    private javax.swing.JLabel lbl_imagenes10;
    private javax.swing.JLabel lbl_imagenes11;
    private javax.swing.JLabel lbl_imagenes12;
    private javax.swing.JLabel lbl_imagenes2;
    private javax.swing.JLabel lbl_imagenes3;
    private javax.swing.JLabel lbl_imagenes4;
    private javax.swing.JLabel lbl_imagenes5;
    private javax.swing.JLabel lbl_imagenes6;
    private javax.swing.JLabel lbl_imagenes7;
    private javax.swing.JLabel lbl_imagenes8;
    private javax.swing.JLabel lbl_imagenes9;
    private javax.swing.JLabel lbl_marca1;
    private javax.swing.JLabel lbl_marca10;
    private javax.swing.JLabel lbl_marca11;
    private javax.swing.JLabel lbl_marca12;
    private javax.swing.JLabel lbl_marca2;
    private javax.swing.JLabel lbl_marca3;
    private javax.swing.JLabel lbl_marca4;
    private javax.swing.JLabel lbl_marca5;
    private javax.swing.JLabel lbl_marca6;
    private javax.swing.JLabel lbl_marca7;
    private javax.swing.JLabel lbl_marca8;
    private javax.swing.JLabel lbl_marca9;
    private javax.swing.JLabel lbl_modelo1;
    private javax.swing.JLabel lbl_modelo10;
    private javax.swing.JLabel lbl_modelo11;
    private javax.swing.JLabel lbl_modelo12;
    private javax.swing.JLabel lbl_modelo2;
    private javax.swing.JLabel lbl_modelo3;
    private javax.swing.JLabel lbl_modelo4;
    private javax.swing.JLabel lbl_modelo5;
    private javax.swing.JLabel lbl_modelo6;
    private javax.swing.JLabel lbl_modelo7;
    private javax.swing.JLabel lbl_modelo8;
    private javax.swing.JLabel lbl_modelo9;
    private javax.swing.JLabel lbl_precio1;
    private javax.swing.JLabel lbl_precio10;
    private javax.swing.JLabel lbl_precio11;
    private javax.swing.JLabel lbl_precio12;
    private javax.swing.JLabel lbl_precio2;
    private javax.swing.JLabel lbl_precio3;
    private javax.swing.JLabel lbl_precio4;
    private javax.swing.JLabel lbl_precio5;
    private javax.swing.JLabel lbl_precio6;
    private javax.swing.JLabel lbl_precio7;
    private javax.swing.JLabel lbl_precio8;
    private javax.swing.JLabel lbl_precio9;
    private javax.swing.JPanel panelAuto1;
    private javax.swing.JPanel panelAuto10;
    private javax.swing.JPanel panelAuto11;
    private javax.swing.JPanel panelAuto12;
    private javax.swing.JPanel panelAuto2;
    private javax.swing.JPanel panelAuto3;
    private javax.swing.JPanel panelAuto4;
    private javax.swing.JPanel panelAuto5;
    private javax.swing.JPanel panelAuto6;
    private javax.swing.JPanel panelAuto7;
    private javax.swing.JPanel panelAuto8;
    private javax.swing.JPanel panelAuto9;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelFiltros;
    // End of variables declaration//GEN-END:variables
}
