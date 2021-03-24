package com.LastSolutionTeam.tastit.POJO;

import java.util.Date;

public class Carta {
    //atributos
    private int nro_carta;
    private Date fecha;
    private int tamanio;
    private int categoria;

    //propiedades


    //constructor
    public Carta(int pNro, double pPrecio, int pTam, int pCat){
        nro_carta = pNro;
        // = pPrecio;
        tamanio = pTam;
        categoria = pCat;
    }
}
