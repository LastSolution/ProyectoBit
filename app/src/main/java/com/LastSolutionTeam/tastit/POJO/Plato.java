package com.LastSolutionTeam.tastit.POJO;

import android.content.Context;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;
import com.LastSolutionTeam.tastit.POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Plato {

    //atributos
    private int id_plato;
    private String nombre_plato;
    private double precio;
    private String descripcion;
    private byte[] imagen;
    private int categoria;
    private String empresa;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    //propiedades
    public int getId_plato() {
        return id_plato;
    }
    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

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

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String tamanio) {
        this.descripcion = tamanio;
    }

    public byte[] getImagen() {
        return imagen;
    }
    public void setImagen(byte[]  imagen) {
        this.imagen = imagen;
    }


    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public String toString(){
        return nombre_plato +"        $"+precio;
    }
    //constructor
    public Plato(int pId, String pNom, double pPrecio, String desc, byte[] pImg, int pCat,String pEmpresa){
        id_plato = pId;
        nombre_plato = pNom;
        precio = pPrecio;
        descripcion = desc;
        imagen = pImg;
        categoria = pCat;
        empresa=pEmpresa;

    }
    public Plato( String pNom, double pPrecio, String desc, byte[] pImg, int pCat,String pEmpresa){

        nombre_plato = pNom;
        precio = pPrecio;
        descripcion = desc;
        imagen = pImg;
        categoria = pCat;
        empresa=pEmpresa;

    }


    //persistencia (privada)
    public static Plato CrearObjeto(ResultSet rs) throws SQLException
    {
        Plato plato = null;

        plato = new Plato(
                rs.getInt("id_plato"),
                rs.getString("nombre_plato"),
                rs.getDouble("precio"),
                rs.getString("descripcion"),
                rs.getBytes("imagen"),
                rs.getInt("categoria"),
                rs.getString("empresa")
        );
        return plato;
    }

    public static int IngresarPlato(Plato plato) {
        int ret=0;
        try {

            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO platos (nombre_plato,precio,imagen,categoria,empresa,descripcion) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, plato.getNombre_plato());
            pst.setString(2, String.valueOf(plato.getPrecio()));
            pst.setBytes(3, plato.getImagen());
            pst.setInt(4,plato.getCategoria());
            pst.setString(5,plato.getEmpresa());
            pst.setString(6, plato.getDescripcion());
            ret = pst.executeUpdate();

        }
        catch (SQLException ex)
        {

        }
        finally
        {
            //cnn.close();
        }
        return ret;
    }

    public static int ModificarPlato(Plato plato) {
        int ret=0;
        try {

            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE platos " +
                    "        SET nombre_plato=?," +
                    "            precio=?," +
                    "            categoria=?," +
                    "            imagen=?," +
                    "            descripcion=?"+
                    "        WHERE id_plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, plato.getNombre_plato());
            pst.setString(2, String.valueOf(plato.getPrecio()));
            pst.setInt(3, plato.getCategoria());
            pst.setBytes(4, plato.getImagen());
            pst.setString(5, plato.getDescripcion());
            pst.setInt(6,plato.getId_plato());

            ret = pst.executeUpdate();

        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
        return ret;
    }

    public static void EliminarPlato(Plato plato) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "Update platos set deleted=1 WHERE id_plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1,plato.getId_plato());
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar la carta!");
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

    public static Plato BuscarPlato(int  id) {

        Plato plato = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM platos WHERE id_plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                plato = Plato.CrearObjeto(rs);

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
        return plato;
    }


    public static ArrayList<Plato> ListarPlatos() {

        ArrayList<Plato> platos = new ArrayList<Plato>();
        Plato plato = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM platos where deleted=0";
            PreparedStatement pst = cnn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                plato = Plato.CrearObjeto(rs);
                platos.add(plato);
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
        return platos;
    }
    public static ArrayList<Plato> BuscarporCategoriayEmpresa(int categoria,String RutEmpresa) {

        ArrayList<Plato> platos = new ArrayList<Plato>();
        Plato plato = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM platos where empresa=? and categoria=? and deleted=0";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, RutEmpresa);
            pst.setInt(2, categoria);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                plato = Plato.CrearObjeto(rs);
                platos.add(plato);
            }
        }
        catch (SQLException ex)
        {

        }

        return platos;
    }






    public void Eliminar(Plato plato) {
        EliminarPlato(plato);
    }

    public ArrayList<Plato> Listar() {
        ArrayList<Plato> platos = new ArrayList<Plato>();
        platos.addAll(ListarPlatos());
        return platos;
    }

}
