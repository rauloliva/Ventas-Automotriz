package com.automotriz.Presentacion;

import javax.swing.*;
import com.automotriz.VO.*;
import java.awt.Color;
import java.util.*;
import com.automotriz.Constantes.Constants;

public class DataModel {

    private static boolean firstBack = false;
    private static int indice = 0;
    private Object[][] components;
    private JPanel[] paneles;
    private JButton back;
    private JButton next;
    private Object data;
    private List<ComentarioVO> comentariosVO;
    private List<AutoVO> autosVO;

    public static void resetIndice() {
        indice = 0;
    }

    public static void resetFirstBack() {
        firstBack = false;
    }

    /**
     * Creates a DataModel object
     *
     * @param components visual components like JTextField, JLabel, etc
     * @param data the list that contains the correspond VO as data
     */
    public DataModel(Object[][] components, Object data, JButton back, JButton next) {
        this.components = components;
        this.data = data;
        this.back = back;
        this.next = next;
    }

    public void setPaneles(JPanel[] paneles) {
        this.paneles = paneles;
    }

    /**
     *
     */
    public void constructNextButton() {
        this.constructCommentsModel();
    }

    public void constructNextBtnCatalogo() {
        this.constructCatalogoModel();
    }

    /**
     *
     */
    public void constructCommentsModel() {
        clearCommentsFields();
        comentariosVO = (List<ComentarioVO>) data;
        if (!comentariosVO.isEmpty()) {
            /*disable the back button when indice == 0
            otherwise the button will be enabled
             */
            this.back.setEnabled(indice != 0);

            for (int row = 0; row < comentariosVO.size(); row++) {
                if ((row + 1) <= Constants.COMMENTS_PER_PAGE && indice < comentariosVO.size()) {
                    JLabel lbl_nombre = (JLabel) components[row][0];
                    JLabel lbl_fecha = (JLabel) components[row][1];
                    JLabel lbl_valoracion = (JLabel) components[row][2];
                    JTextArea txa_comentario = (JTextArea) components[row][3];
                    paneles[row].setBackground(Color.decode(ReadProperties.props.getProperty("color.verde")));

                    ComentarioVO comentario = comentariosVO.get(indice++);
                    lbl_nombre.setText("Nombre: " + comentario.getNombre());
                    lbl_fecha.setText("Fecha de Publicacion: " + comentario.getFecha());
                    lbl_valoracion.setText("Valoracion:   5/" + comentario.getValoracion());
                    txa_comentario.setText(comentario.getComentario());
                    if (comentario.getComentario().equals("")) {
                        txa_comentario.setBackground(Color.GRAY.brighter());
                    }
                }
            }

            if (indice == comentariosVO.size()) {
                this.next.setEnabled(false);
            }
        }
    }

    public void constructCatalogoModel() {
        clearAutosFields();
        autosVO = (List<AutoVO>) data;
        if (!autosVO.isEmpty()) {
            /*disable the back button when indice == 0
            otherwise the button will be enabled
             */
            this.back.setEnabled(indice != 0);

            for (int row = 0; row < autosVO.size(); row++) {
                if ((row + 1) <= Constants.AUTOS_PER_PAGE && indice < autosVO.size()) {
                    JLabel lbl_marca = (JLabel) components[row][0];
                    JLabel lbl_modelo = (JLabel) components[row][1];
                    JLabel lbl_precio = (JLabel) components[row][2];
                    JLabel lbl_imagenes = (JLabel) components[row][3];

                    AutoVO auto = autosVO.get(indice++);
                    lbl_marca.setText("Marca: " + auto.getMarca());
                    lbl_modelo.setText("Modelo: " + auto.getModelo());
                    lbl_precio.setText("Precio: " + auto.getPrecio());
                    int images = auto.getImagenes().equals("") ? 0 : auto.getImagenes().split(";").length;
                    lbl_imagenes.setText("Imagenes: " + images);
                }
            }

            if (indice == autosVO.size()) {
                this.next.setEnabled(false);
            }
        }
    }

    public void constructBackBtnComentarios() {
        clearCommentsFields();
        comentariosVO = (List<ComentarioVO>) data;
        if (!comentariosVO.isEmpty()) {
            int row, i = 0, res;
            //
            res = !firstBack ? 2 : 1;
            for (indice = row = indice - res; row >= 0; row--, i++) {
                if (i < Constants.COMMENTS_PER_PAGE) {
                    JLabel lbl_nombre = (JLabel) components[i][0];
                    JLabel lbl_fecha = (JLabel) components[i][1];
                    JLabel lbl_valoracion = (JLabel) components[i][2];
                    JTextArea txa_comentario = (JTextArea) components[i][3];

                    ComentarioVO comentario = comentariosVO.get(indice--);
                    lbl_nombre.setText("Nombre: " + comentario.getNombre());
                    lbl_fecha.setText("Fecha de Publicacion: " + comentario.getFecha());
                    lbl_valoracion.setText("Valoracion:   5/" + comentario.getValoracion());
                    txa_comentario.setText(comentario.getComentario());
                    if (comentario.getComentario().equals("")) {
                        txa_comentario.setBackground(Color.GRAY.brighter());
                    }
                }
            }
            firstBack = true;

            /*disable the back button when indice == 0
            otherwise the button will be enabled
             */
            this.back.setEnabled((indice < 0 ? 0 : indice) != 0);
            indice = Constants.COMMENTS_PER_PAGE;
            this.next.setEnabled(true);
        }
    }

    public void constructBackBtnCatalogo() {
        clearAutosFields();
        autosVO = (List<AutoVO>) data;
        if (!autosVO.isEmpty()) {
            int row, i = 0, res;
            //
            res = !firstBack ? 2 : 1;
            for (indice = row = indice - res; row >= 0; row--, i++) {
                if (i < Constants.AUTOS_PER_PAGE) {
                    JLabel lbl_marca = (JLabel) components[row][0];
                    JLabel lbl_modelo = (JLabel) components[row][1];
                    JLabel lbl_precio = (JLabel) components[row][2];
                    JLabel lbl_imagenes = (JLabel) components[row][3];

                    AutoVO auto = autosVO.get(indice--);
                    lbl_marca.setText("Marca: " + auto.getMarca());
                    lbl_modelo.setText("Modelo: " + auto.getModelo());
                    lbl_precio.setText("Precio: " + auto.getPrecio());
                    lbl_imagenes.setText("Imagenes: " + auto.getImagenes().split(";").length);
                }
            }
            firstBack = true;

            /*disable the back button when indice == 0
            otherwise the button will be enabled
             */
            this.back.setEnabled((indice < 0 ? 0 : indice) != 0);
            indice = Constants.AUTOS_PER_PAGE;
            this.next.setEnabled(true);
        }
    }

    private void clearCommentsFields() {
        for (int i = 0; i < 4; i++) {
            ((JLabel) components[i][0]).setText("Nombre: ");
            ((JLabel) components[i][1]).setText("Fecha de Publicacion: ");
            ((JLabel) components[i][2]).setText("Valoracion:   5/");
            ((JTextArea) components[i][3]).setText(null);
            ((JTextArea) components[i][3]).setBackground(Color.white);
        }
    }

    private void clearAutosFields() {
        for (int i = 0; i < 4; i++) {
            ((JLabel) components[i][0]).setText("Marca: ");
            ((JLabel) components[i][1]).setText("Modelo:");
            ((JLabel) components[i][2]).setText("Precio: ");
            ((JLabel) components[i][3]).setText("Imagenes:");
        }
        for (JPanel panel : paneles) {
            panel.setBackground(Color.decode(ReadProperties.props.getProperty("color.default")));
        }
    }

    public int calculateValoracionTotal() {
        int total = 0;
        if (comentariosVO != null) {
            for (int i = 0; i < comentariosVO.size(); i++) {
                ComentarioVO comentario = comentariosVO.get(i);
                total += comentario.getValoracion();
            }
        }
        if (total > 0) {
            total /= comentariosVO.size();
        }
        return total;
    }
}
