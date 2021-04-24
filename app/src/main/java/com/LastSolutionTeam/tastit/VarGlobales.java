package com.LastSolutionTeam.tastit;

import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;

public class VarGlobales {
    private static Usuario usuarioActual;
    private static Empresa empresaActual;

    private static Cliente cliente1;
    private static Cliente cliente2;
    private static Cliente cliente3;
    private static Cliente cliente4;
    private static Cliente cliente5;
    private static Cliente cliente6;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        VarGlobales.usuarioActual = usuarioActual;
    }

    public static Empresa getEmpresaActual() {
        return empresaActual;
    }

    public static void setEmpresaActual(Empresa empresaActual) {
        VarGlobales.empresaActual = empresaActual;
    }

    public static Cliente getCliente1() {
        return cliente1;
    }

    public static void setCliente1(Cliente cliente1) {
        VarGlobales.cliente1 = cliente1;
    }

    public static Cliente getCliente2() {
        return cliente2;
    }

    public static void setCliente2(Cliente cliente2) {
        VarGlobales.cliente2 = cliente2;
    }

    public static Cliente getCliente3() {
        return cliente3;
    }

    public static void setCliente3(Cliente cliente3) {
        VarGlobales.cliente3 = cliente3;
    }

    public static Cliente getCliente4() {
        return cliente4;
    }

    public static void setCliente4(Cliente cliente4) {
        VarGlobales.cliente4 = cliente4;
    }
}
