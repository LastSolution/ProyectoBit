package com.LastSolutionTeam.tastit.POJO;

public class Usuario {

    //atributos
    private int id_usuario;
    private String username;
    private String password;
    private int tipo;
    private String rut_empresa;

    //propiedades
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRut_empresa() {
        return rut_empresa;
    }
    public void setRut_empresa(String rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    //constructor
    public Usuario(int pId, String pUser, String pPass, int pTipo, String pRut){
        id_usuario = pId;
        username = pUser;
        password = pPass;
        tipo = pTipo;
        rut_empresa = pRut;
    }
}