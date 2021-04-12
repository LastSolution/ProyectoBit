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

    //persistencia
    private static Local CrearObjeto(ResultSet rs) throws SQLException
    {
        Local local = null;

        local = new Local(
                rs.getInt("nro_local"),
                rs.getString("latitud"),
                rs.getString("longitud"),
                rs.getString("direccion"),
                rs.getString("telefono"),
                rs.getString("empresa")
        );
        return local;
    }

    private static void IngresarLocal(Local local, Empresa emp) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO locales (latitud,longitud,direccion,empresa) values (?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(local.getNro_local()));
            pst.setString(2, local.getLatitud());
            pst.setString(3, local.getLongitud());
            pst.setString(4, local.getDireccion());
            pst.setString(5, local.getTelefono());
            pst.setString(6, emp.getRut());
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el local!");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
    }

    private static void ModificarLocal(Local local, Empresa emp) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE locales " +
                    "        SET latitud=?," +
                    "            longitud=?," +
                    "            direccion=?," +
                    "            telefono=?," +
                    "            empresa=? " +
                    "        WHERE nro_local=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, local.getLatitud());
            pst.setString(2, local.getLongitud());
            pst.setString(3, local.getDireccion());
            pst.setString(4, local.getTelefono());
            pst.setString(5, emp.getRut());
            pst.setString(6, String.valueOf(local.getNro_local()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar el local!");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
    }

    private static void EliminarLocal(Local local) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE locales WHERE nro_local=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(local.getNro_local()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar el local!");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
    }

    private static Local BuscarLocal(int nro) {

        Local loc = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM locales WHERE nro_local=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(nro));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                loc.nro_local = rs.getInt(0);
                loc.latitud = rs.getNString(1);
                loc.longitud = rs.getNString(2);
                loc.direccion = rs.getNString(3);
                loc.telefono = rs.getNString(4);
                loc.rut_empresa = rs.getNString(5);
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
        return loc;
    }

    private static ArrayList<Local> ListarLocalesDeEmpresa(Empresa emp) {

        ArrayList<Local> locales = new ArrayList<Local>();

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM locales WHERE empresa = ?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, emp.rut);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Local local = null;
                local = Local.CrearObjeto(rs);
                locales.add(local);
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
        return locales;
    }


    //logica (publica)
    public Local Buscar(int nro, Context context)
    {
        return BuscarLocal(nro);
    }

    public void Ingresar(Local local, Empresa emp) {
        IngresarLocal(local, emp);
    }

    public void Modificar(Local local, Empresa emp) {
        ModificarLocal(local, emp);
    }

    public void Eliminar(Local local, Context context) {
        EliminarLocal(local);
    }

    public ArrayList<Local> Listar(Empresa emp)
    {
        ArrayList<Local> locales = new ArrayList<Local>();
        locales.addAll(ListarLocalesDeEmpresa(emp));
        return locales;
    }
}