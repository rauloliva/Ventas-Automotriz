package com.automotriz.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.automotriz.Constantes.Constants;

public class UsuarioVO implements Serializable {

    private int id;
    private String usuario;
    private String contraseña;
    private String correo;
    private String perfil;
    private String estatus;
    private String telefono;
    private String nombre;
    private String permisos;

    public UsuarioVO(int id, String usuario, String contraseña, String correo, String perfil, String estatus, String telefono, String nombre, String permisos) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.perfil = perfil;
        this.estatus = estatus;
        this.telefono = telefono;
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public UsuarioVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "id=" + id + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", correo=" + correo + ", perfil=" + perfil + ", estatus=" + estatus + ", telefono=" + telefono + ", nombre=" + nombre + ", permisos=" + permisos + '}';
    }

    public SessionVO copyToSession() {
        //creating a new session
        SessionVO session = new SessionVO();
        session.setId(this.id);
        session.setNombre(this.nombre);
        session.setUsername(this.usuario);
        session.setPassword(this.contraseña);
        session.setMail(this.correo);
        session.setTelefono(this.telefono);
        session.setPerfil(this.perfil);
        return session;
    }

    /**
     * Generate a list of String Arrays from a list of Usuarios
     *
     * @param usuarios the list of UsuarioVO to convert
     * @return a list of a String matrix
     */
    public static List<String[][]> usuariosAsMatrix(List<UsuarioVO> usuarios) {
        String[][] usuariosMatrix = new String[usuarios.size()][Constants.NUM_FIELDS];

        for (int i = 0; i < usuarios.size(); i++) {
            usuariosMatrix[i][0] = usuarios.get(i).getUsuario();
            usuariosMatrix[i][1] = usuarios.get(i).getCorreo();
            usuariosMatrix[i][2] = usuarios.get(i).getPerfil();
            usuariosMatrix[i][3] = usuarios.get(i).getEstatus();
            usuariosMatrix[i][4] = usuarios.get(i).getTelefono();
            usuariosMatrix[i][5] = usuarios.get(i).getNombre();
        }
        return saveMatrixOnList(usuariosMatrix);
    }

    private static List<String[][]> saveMatrixOnList(String[][] usuarios) {
        List<String[][]> usuariosList = new ArrayList();
        usuariosList.add(usuarios);
        return usuariosList;
    }
}
