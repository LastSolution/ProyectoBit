package com.LastSolutionTeam.tastit.AdaptadoresSpinners;

public class EmpresaSpinner {
    private String Rut;
    private  String Nombre;

    public EmpresaSpinner(String rut, String nombre) {
        Rut = rut;
        Nombre = nombre;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        Rut = rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
