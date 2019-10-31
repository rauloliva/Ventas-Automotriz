package com.automotriz.Presentacion;

import com.automotriz.VO.Session;
import com.automotriz.logger.Logger;
import java.awt.Image;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame_InicioCliente extends javax.swing.JInternalFrame {

    private JFrame parent;
    private JDesktopPane container;
    private Session session;
    private Wallpaper wallpaper;

    public Frame_InicioCliente(JFrame parent, JDesktopPane container, Session session) {
        initComponents();
        this.parent = parent;
        this.container = container;
        this.session = session;
        initFrame();
        setVisible(true);
    }

    public void initFrame() {
        wallpaper = new Wallpaper(lbl_fondo);
        menu_vender.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.vender")))
                                .getImage()
                                .getScaledInstance(32, 32, Image.SCALE_DEFAULT)
                )
        );

        menu_feedback.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.feedback")))
                                .getImage()
                                .getScaledInstance(32, 32, Image.SCALE_DEFAULT)
                )
        );

        lbl_welcome.setIcon(
                new ImageIcon(
                        new ImageIcon(getClass().getResource(ReadProperties.props.getProperty("icon.welcome")))
                                .getImage()
                                .getScaledInstance(540, 160, Image.SCALE_DEFAULT)
                )
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_welcome = new javax.swing.JLabel();
        lbl_fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuVerPerfil = new javax.swing.JMenu();
        menu_verPerfil = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menu_vender = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menu_feedback = new javax.swing.JMenuItem();

        panelContent.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        panelContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/verisign.jpg"))); // NOI18N
        panelContent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, 139));
        panelContent.add(lbl_welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 540, 160));
        panelContent.add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 560));

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        menuVerPerfil.setText("Perfil");
        menuVerPerfil.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        menu_verPerfil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menu_verPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/perfil.png"))); // NOI18N
        menu_verPerfil.setText("Ver Perfil");
        menu_verPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_verPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_verPerfilActionPerformed(evt);
            }
        });
        menuVerPerfil.add(menu_verPerfil);

        jMenuBar1.add(menuVerPerfil);

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        menu_vender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menu_vender.setText("Vender");
        menu_vender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_venderActionPerformed(evt);
            }
        });
        jMenu1.add(menu_vender);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/catalogo.png"))); // NOI18N
        jMenuItem1.setText("Ver Catalogo");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menu_feedback.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menu_feedback.setText("Añadir Comentario");
        menu_feedback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_feedbackActionPerformed(evt);
            }
        });
        jMenu1.add(menu_feedback);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_verPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_verPerfilActionPerformed
        wallpaper.stop();
        this.dispose();
        container.add(new Frame_Perfil(parent, container, session));
    }//GEN-LAST:event_menu_verPerfilActionPerformed

    private void menu_venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_venderActionPerformed
        wallpaper.stop();
        this.dispose();
        container.add(new Frame_Vender(parent, container, session));
    }//GEN-LAST:event_menu_venderActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        wallpaper.stop();
        this.dispose();
        container.add(new Frame_Catalogo(parent, container, session));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menu_feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_feedbackActionPerformed
        wallpaper.stop();
        this.dispose();
        container.add(new Frame_AddComentario(parent, container, session));
    }//GEN-LAST:event_menu_feedbackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_welcome;
    private javax.swing.JMenu menuVerPerfil;
    private javax.swing.JMenuItem menu_feedback;
    private javax.swing.JMenuItem menu_vender;
    private javax.swing.JMenuItem menu_verPerfil;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}

/**
 *
 * @author oliva
 */
class Wallpaper implements Runnable {

    private JLabel lbl_fondo;
    private Thread hilo;
    private Properties props = ReadProperties.props;

    public Wallpaper(JLabel lbl_fondo) {
        this.lbl_fondo = lbl_fondo;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 1; i <= 5; i++) {
                    setImageBackground(i);
                    hilo.sleep(Integer.parseInt(props.getProperty("cliente.back.time")));
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }

    private void setImageBackground(int index) {
        ImageIcon img = new ImageIcon(getClass().getResource(props.getProperty("cliente.img" + index)));
        Image i = img.getImage();
        i = i.getScaledInstance(1190, 560, Image.SCALE_DEFAULT);
        lbl_fondo.setIcon(new ImageIcon(i));
    }

    public void stop() {
        hilo.stop();
    }
}
