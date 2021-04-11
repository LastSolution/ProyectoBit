package com.LastSolutionTeam.tastit.POJO;

import android.content.Context;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Empresa {

    //atributos

     String rut;
     String nombre;
     String telefono;
     String correo;
     String Logo;

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    //propiedades
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
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
    public Empresa(String pRut, String pNom, String pTel, String pCorreo, String logo){
        rut = pRut;
        nombre = pNom;
        telefono = pTel;
        correo = pCorreo;
    }
    public static int IngresarEmpresa( Empresa empresa, Context context ) {
        int ret=0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Connection Conn= Conexion.ObtenerConexion();

        if(Conn!=null)
        {
            try {
                PreparedStatement pst=Conn.prepareStatement("insert into EMPRESAS (rut,nombre,telefono,correo,logo) values (?,?,?,?,?)");
                pst.setString(1,empresa.getRut());
                pst.setString(2,empresa.getNombre());
                pst.setString(3,empresa.getTelefono());
                pst.setString(4, empresa.getCorreo());
                pst.setString(5, empresa.getLogo());
                ret= pst.executeUpdate();


            }catch (SQLException E)
            {
                return ret;
            }
        }else {
            return 3;
        }
        return ret;
    }
    private static Empresa CrearObjeto(ResultSet rs) throws SQLException
    {
        Empresa empresa = null;

        empresa =new Empresa(

                rs.getString("rut"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("logo"));
        return empresa;
    }

    public static ArrayList<Empresa> BuscarTodas() {

        ArrayList<Empresa> empresas=new ArrayList<Empresa>();
        Empresa empresa=null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM Empresas";
            PreparedStatement pst = cnn.prepareStatement(sql);


            ResultSet rs = pst.executeQuery();

            while(rs.next()){
             empresa=Empresa.CrearObjeto(rs);
            empresas.add(empresa);

            }
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
        return empresas;
    }
    public static int EliminarEmpresa(String rut){

        int ret=0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Connection Conn= Conexion.ObtenerConexion();

        if(Conn!=null)
        {
            try {
                PreparedStatement pst=Conn.prepareStatement("delete from EMPRESAS where rut =?");
                pst.setString(1,rut);

                ret= pst.executeUpdate();


            }catch (SQLException E)
            {
                return ret;
            }
        }else {
            return 3;
        }
        return ret;
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