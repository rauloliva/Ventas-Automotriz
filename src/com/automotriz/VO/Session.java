package com.automotriz.VO;

import java.io.Serializable;

public class Session implements Serializable {

    private int id;
    private String username;
    private String password;
    private String mail;
    private String telefono;
    private String perfil;
    private String nombre;

    public Session(int id, String username, String password, String mail, String telefono, String perfil, String nombre) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.telefono = telefono;
        this.perfil = perfil;
        this.nombre = nombre;
    }

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", username=" + username + ", password=" + password + ", mail=" + mail + ", telefono=" + telefono + ", perfil=" + perfil + '}';
    }

}
