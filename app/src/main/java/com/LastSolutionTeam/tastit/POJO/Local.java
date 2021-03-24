package com.LastSolutionTeam.tastit.POJO;

public class Local {

    //atributos
    private int nro_local;
    private String latitud;
    private String longitud;
    private String direccion;
    private String telefono;
    private String rut_empresa;

    //propiedades
    public int getNro_local() {
        return nro_local;
    }
    public void setNro_local(int nro_local) {
        this.nro_local = nro_local;
    }

    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRut_empresa() {
        return rut_empresa;
    }
    public void setRut_empresa(String rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    //constructor
    public Local(int pNro, String pLat, String pLong, String pDire, String pTel, String pRut){
        nro_local = pNro;
        latitud = pLat;
        longitud = pLong;
        direccion = pDire;
        telefono = pTel;
        rut_empresa = pRut;
    }

}