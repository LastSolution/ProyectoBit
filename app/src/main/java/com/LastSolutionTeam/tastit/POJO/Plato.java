package com.LastSolutionTeam.tastit.POJO;

public class Plato {
    //atributos
    private String nombre_plato;
    private double precio;
    private int tamanio;
    private int categoria;

    //propiedades
    public String getNombre_plato() {
        return nombre_plato;
    }
    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTamanio() {
        return tamanio;
    }
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    //constructor
    public Plato(String pNom, double pPrecio, int pTam, int pCat){
        nombre_plato = pNom;
        precio = pPrecio;
        tamanio = pTam;
        categoria = pCat;
    }
}
