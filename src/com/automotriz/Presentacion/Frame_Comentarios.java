package com.automotriz.Presentacion;

import com.automotriz.VO.*;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import com.automotriz.Constantes.Constants;

public class Frame_Comentarios extends javax.swing.JInternalFrame implements Runnable, Constants<Frame_Comentarios> {

    public Frame_Comentarios() {
        initComponents();
        this.setVisible(true);
        initFrame(this);
        this.setTitle("Comentarios");
        getFeedBack("");
        /* start a thread to know when the frame closes 
            so it can reset the index from DataModel class
         */
        new Thread(this).start();
    }

    /**
     *
     */
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
            int garbageVar = 0;
        }
    }

    @Override
    public void initFrame(Frame_Comentarios c) {
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        String name = ReadProperties.props.getProperty("name.comentarios");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        panelComentario1.setBackground(Color.decode(ReadProperties.props.getProperty("color.orangeWhite")));
        panelComentario2.setBackground(Color.decode(ReadProperties.props.getProperty("color.orangeWhite")));
        panelComentario3.setBackground(Color.decode(ReadProperties.props.getProperty("color.orangeWhite")));
        panelComentario4.setBackground(Color.decode(ReadProperties.props.getProperty("color.orangeWhite")));
        setStarsImages();
    }

    private void setStarsImages() {
        ImageIcon imageStart = new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.emptyStart")));
        start1.setIcon(imageStart);
        start2.setIcon(imageStart);
        start3.setIcon(imageStart);
        start4.setIcon(imageStart);
        start5.setIcon(imageStart);
    }

    private void getFeedBack(String flag) {
        Validacion validacion = new Validacion(null, new ComentarioVO()).getFeedBack();
        List<ComentarioVO> comentariosVO = validacion.getComentarios();
        if (comentariosVO != null) {
            lbl_no_comments.setText(null);
            DataModel model = new DataModel(new Object[][]{
                {lbl_nombre_1, lbl_fecha_1, lbl_validacion_1, txa_comentario_1},
                {lbl_nombre_2, lbl_fecha_2, lbl_validacion_2, txa_comentario_2},
                {lbl_nombre_3, lbl_fecha_3, lbl_validacion_3, txa_comentario_3},
                {lbl_nombre_4, lbl_fecha_4, lbl_validacion_4, txa_comentario_4}
            }, comentariosVO, btn_atras, btn_siguiente);

            if (flag.equals("")) {
                model.constructCommentsModel();
            } else if (flag.equals("next")) {
                model.constructNextButton();
            } else {
                model.constructBackBtnComentarios();
            }
            showValoracion(model);
        } else {
            btn_atras.setEnabled(false);
            btn_siguiente.setEnabled(false);
        }
    }

    private void showValoracion(DataModel model) {
        int val = model.calculateValoracionTotal();
        ImageIcon imageStart = new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.filledStart")));
        switch (val) {
            case 1:
                start1.setIcon(imageStart);
                break;
            case 2:
                start1.setIcon(imageStart);
                start2.setIcon(imageStart);
                break;
            case 3:
                start1.setIcon(imageStart);
                start2.setIcon(imageStart);
                start3.setIcon(imageStart);
                break;
            case 4:
                start1.setIcon(imageStart);
                start2.setIcon(imageStart);
                start3.setIcon(imageStart);
                start4.setIcon(imageStart);
                break;
            case 5:
                start1.setIcon(imageStart);
                start2.setIcon(imageStart);
                start3.setIcon(imageStart);
                start4.setIcon(imageStart);
                start5.setIcon(imageStart);
                break;
        }
        start1.setToolTipText(val + "/5");
        start2.setToolTipText(val + "/5");
        start3.setToolTipText(val + "/5");
        start4.setToolTipText(val + "/5");
        start5.setToolTipText(val + "/5");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        btn_atras = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelComentario1 = new javax.swing.JPanel();
        lbl_nombre_1 = new javax.swing.JLabel();
        lbl_validacion_1 = new javax.swing.JLabel();
        lbl_fecha_1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_comentario_1 = new javax.swing.JTextArea();
        panelComentario2 = new javax.swing.JPanel();
        lbl_nombre_2 = new javax.swing.JLabel();
        lbl_fecha_2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txa_comentario_2 = new javax.swing.JTextArea();
        lbl_validacion_2 = new javax.swing.JLabel();
        panelComentario3 = new javax.swing.JPanel();
        lbl_nombre_3 = new javax.swing.JLabel();
        lbl_fecha_3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txa_comentario_3 = new javax.swing.JTextArea();
        lbl_validacion_3 = new javax.swing.JLabel();
        panelComentario4 = new javax.swing.JPanel();
        lbl_nombre_4 = new javax.swing.JLabel();
        lbl_fecha_4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txa_comentario_4 = new javax.swing.JTextArea();
        lbl_validacion_4 = new javax.swing.JLabel();
        lbl_no_comments = new javax.swing.JLabel();
        starsPanel = new javax.swing.JPanel();
        start1 = new javax.swing.JLabel();
        start2 = new javax.swing.JLabel();
        start3 = new javax.swing.JLabel();
        start4 = new javax.swing.JLabel();
        start5 = new javax.swing.JLabel();

        btn_atras.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_atras.setText("Atras");
        btn_atras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        btn_siguiente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_siguiente.setText("Siguiente");
        btn_siguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        panelComentario1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_nombre_1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_nombre_1.setText("Nombre:");

        lbl_validacion_1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_validacion_1.setText("Valoracion:   5/");

        lbl_fecha_1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_fecha_1.setText("Fecha de publicacion:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("Comentario(s):");

        txa_comentario_1.setEditable(false);
        txa_comentario_1.setColumns(20);
        txa_comentario_1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txa_comentario_1.setRows(5);
        txa_comentario_1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txa_comentario_1.setSelectionColor(new java.awt.Color(204, 204, 0));
        jScrollPane1.setViewportView(txa_comentario_1);

        javax.swing.GroupLayout panelComentario1Layout = new javax.swing.GroupLayout(panelComentario1);
        panelComentario1.setLayout(panelComentario1Layout);
        panelComentario1Layout.setHorizontalGroup(
            panelComentario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComentario1Layout.createSequentialGroup()
                        .addComponent(lbl_nombre_1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_validacion_1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComentario1Layout.createSequentialGroup()
                        .addGroup(panelComentario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fecha_1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelComentario1Layout.setVerticalGroup(
            panelComentario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre_1)
                    .addComponent(lbl_validacion_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_fecha_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        panelComentario2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_nombre_2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_nombre_2.setText("Nombre:");

        lbl_fecha_2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_fecha_2.setText("Fecha de publicacion:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("Comentario(s):");

        txa_comentario_2.setEditable(false);
        txa_comentario_2.setColumns(20);
        txa_comentario_2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txa_comentario_2.setRows(5);
        txa_comentario_2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txa_comentario_2.setSelectionColor(new java.awt.Color(204, 204, 0));
        jScrollPane2.setViewportView(txa_comentario_2);

        lbl_validacion_2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_validacion_2.setText("Valoracion:   5/");

        javax.swing.GroupLayout panelComentario2Layout = new javax.swing.GroupLayout(panelComentario2);
        panelComentario2.setLayout(panelComentario2Layout);
        panelComentario2Layout.setHorizontalGroup(
            panelComentario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComentario2Layout.createSequentialGroup()
                        .addComponent(lbl_nombre_2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_validacion_2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComentario2Layout.createSequentialGroup()
                        .addGroup(panelComentario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fecha_2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelComentario2Layout.setVerticalGroup(
            panelComentario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre_2)
                    .addComponent(lbl_validacion_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_fecha_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelComentario3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_nombre_3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_nombre_3.setText("Nombre:");

        lbl_fecha_3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_fecha_3.setText("Fecha de publicacion:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setText("Comentario(s):");

        txa_comentario_3.setEditable(false);
        txa_comentario_3.setColumns(20);
        txa_comentario_3.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txa_comentario_3.setRows(5);
        txa_comentario_3.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txa_comentario_3.setSelectionColor(new java.awt.Color(204, 204, 0));
        jScrollPane3.setViewportView(txa_comentario_3);

        lbl_validacion_3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_validacion_3.setText("Valoracion:   5/");

        javax.swing.GroupLayout panelComentario3Layout = new javax.swing.GroupLayout(panelComentario3);
        panelComentario3.setLayout(panelComentario3Layout);
        panelComentario3Layout.setHorizontalGroup(
            panelComentario3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComentario3Layout.createSequentialGroup()
                        .addComponent(lbl_nombre_3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_validacion_3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComentario3Layout.createSequentialGroup()
                        .addGroup(panelComentario3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fecha_3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelComentario3Layout.setVerticalGroup(
            panelComentario3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre_3)
                    .addComponent(lbl_validacion_3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_fecha_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelComentario4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_nombre_4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_nombre_4.setText("Nombre:");

        lbl_fecha_4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_fecha_4.setText("Fecha de publicacion:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setText("Comentario(s):");

        txa_comentario_4.setEditable(false);
        txa_comentario_4.setColumns(20);
        txa_comentario_4.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txa_comentario_4.setRows(5);
        txa_comentario_4.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txa_comentario_4.setSelectionColor(new java.awt.Color(204, 204, 0));
        jScrollPane4.setViewportView(txa_comentario_4);

        lbl_validacion_4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_validacion_4.setText("Valoracion:   5/");

        javax.swing.GroupLayout panelComentario4Layout = new javax.swing.GroupLayout(panelComentario4);
        panelComentario4.setLayout(panelComentario4Layout);
        panelComentario4Layout.setHorizontalGroup(
            panelComentario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComentario4Layout.createSequentialGroup()
                        .addComponent(lbl_nombre_4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_validacion_4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelComentario4Layout.createSequentialGroup()
                        .addGroup(panelComentario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fecha_4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelComentario4Layout.setVerticalGroup(
            panelComentario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentario4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComentario4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre_4)
                    .addComponent(lbl_validacion_4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_fecha_4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelComentario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentario3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelComentario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentario4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelComentario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelComentario3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentario4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lbl_no_comments.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_no_comments.setForeground(new java.awt.Color(255, 51, 0));
        lbl_no_comments.setText("No hay comentarios para mostrar");

        starsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        starsPanel.setToolTipText("Valoracion Total");

        start1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        start2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        start3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        start4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        start5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout starsPanelLayout = new javax.swing.GroupLayout(starsPanel);
        starsPanel.setLayout(starsPanelLayout);
        starsPanelLayout.setHorizontalGroup(
            starsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starsPanelLayout.createSequentialGroup()
                .addComponent(start1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        starsPanelLayout.setVerticalGroup(
            starsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(start1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(start2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(start3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(start4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(start5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(lbl_no_comments, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(btn_siguiente)
                        .addGap(81, 81, 81)
                        .addComponent(starsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_atras)
                        .addComponent(btn_siguiente)
                        .addComponent(lbl_no_comments))
                    .addComponent(starsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
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

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        getFeedBack("next");
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        getFeedBack("back");
    }//GEN-LAST:event_btn_atrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_fecha_1;
    private javax.swing.JLabel lbl_fecha_2;
    private javax.swing.JLabel lbl_fecha_3;
    private javax.swing.JLabel lbl_fecha_4;
    private javax.swing.JLabel lbl_no_comments;
    private javax.swing.JLabel lbl_nombre_1;
    private javax.swing.JLabel lbl_nombre_2;
    private javax.swing.JLabel lbl_nombre_3;
    private javax.swing.JLabel lbl_nombre_4;
    private javax.swing.JLabel lbl_validacion_1;
    private javax.swing.JLabel lbl_validacion_2;
    private javax.swing.JLabel lbl_validacion_3;
    private javax.swing.JLabel lbl_validacion_4;
    private javax.swing.JPanel panelComentario1;
    private javax.swing.JPanel panelComentario2;
    private javax.swing.JPanel panelComentario3;
    private javax.swing.JPanel panelComentario4;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel starsPanel;
    private javax.swing.JLabel start1;
    private javax.swing.JLabel start2;
    private javax.swing.JLabel start3;
    private javax.swing.JLabel start4;
    private javax.swing.JLabel start5;
    private javax.swing.JTextArea txa_comentario_1;
    private javax.swing.JTextArea txa_comentario_2;
    private javax.swing.JTextArea txa_comentario_3;
    private javax.swing.JTextArea txa_comentario_4;
    // End of variables declaration//GEN-END:variables
}
