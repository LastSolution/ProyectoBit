package com.LastSolutionTeam.tastit.POJO;

public class Empresa {

    //atributos
    private String rut;
    private String nombre;
    private String telefono;
    private String correo;

    //propiedades
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return telefono;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return correo;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //constructor
    public Empresa(String pRut, String pNom, String pTel, String pCorreo){
        rut = pRut;
        nombre = pNom;
        telefono = pTel;
        correo = pCorreo;
    }

//    @Override	// CTRL + Barra
//    public void mostrarDatos(String nombreClase) {
//        // TODO Auto-generated method stub		--> polimorfismo
//        super.mostrarDatos(nombreClase);
//        System.out.println("Corte: " + corte);
//    }
//    @Override
//    public void hacerDevolucion() {
//        // TODO Auto-generated method stub
//        System.out.println("Devolucion de una playera");
//    }
}
