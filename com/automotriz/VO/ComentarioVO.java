package com.automotriz.VO;

public class ComentarioVO {

    String nombre;
    String comentario;
    int valoracion;
    String fecha;

    public ComentarioVO(String nombre, String comentario, int valoracion, String fecha) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fecha = fecha;
    }

    public ComentarioVO() {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
