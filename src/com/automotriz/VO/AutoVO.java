package com.automotriz.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AutoVO implements Serializable {

    private int id;
    private int modelo;
    private String imagenes;
    private int kilometros;
    private String descripcion;
    private String marca;
    private String cambio;
    private double precio;
    private String color;
    private String estatus;
    private int id_usuario;
    private static final int nFields = 5;

    public AutoVO(int id, int modelo, String imagenes, int kilometros, String descripcion, String marca, String cambio, double precio, String color, String estatus, int id_usuario) {
        this.id = id;
        this.modelo = modelo;
        this.imagenes = imagenes;
        this.kilometros = kilometros;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cambio = cambio;
        this.precio = precio;
        this.color = color;
        this.estatus = estatus;
        this.id_usuario = id_usuario;
    }

    public AutoVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Generate a list of String Arrays from a list of Autos
     *
     * @param autos the list of AutoVO to convert
     * @return a list of a String matrix
     */
    public static List<String[][]> usuariosAsMatrix(List<AutoVO> autos) {
        String[][] usuariosMatrix = new String[autos.size()][nFields];

        for (int i = 0; i < autos.size(); i++) {
            usuariosMatrix[i][0] = autos.get(i).getMarca();
            usuariosMatrix[i][1] = "" + autos.get(i).getModelo();
            usuariosMatrix[i][2] = autos.get(i).getCambio();
            usuariosMatrix[i][3] = autos.get(i).getColor();
            usuariosMatrix[i][4] = autos.get(i).getEstatus();
        }
        return saveMatrixOnList(usuariosMatrix);
    }

    private static List<String[][]> saveMatrixOnList(String[][] usuarios) {
        List<String[][]> usuariosList = new ArrayList();
        usuariosList.add(usuarios);
        return usuariosList;
    }

}
