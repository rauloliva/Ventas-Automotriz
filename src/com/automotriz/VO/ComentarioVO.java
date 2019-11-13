package com.automotriz.VO;

import java.io.Serializable;

public class ComentarioVO implements Serializable {

    private int id;
    private String nombre;
    private String comentario;
    private int valoracion;
    private int id_usuario;
    private String fecha;

    public ComentarioVO(int id, String nombre, String comentario, int valoracion, int id_usuario, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
    }

    public ComentarioVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Id: " + id + ","
                + "Nombre: " + nombre + ","
                + "Comentario: " + comentario + ","
                + "Valoracion: " + valoracion + ","
                + "Id_usuario: " + id_usuario + ","
                + "Fecha: " + fecha;
    }

}
