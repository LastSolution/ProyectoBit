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
    private int tamanio;
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

    public int getTamanio() {
        return tamanio;
    }
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
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

    //constructor
    public Plato(int pId, String pNom, double pPrecio, int pTam, byte[] pImg, int pCat,String pEmpresa){
        id_plato = pId;
        nombre_plato = pNom;
        precio = pPrecio;
        tamanio = pTam;
        imagen = pImg;
        categoria = pCat;
        empresa=pEmpresa;

    }
    public Plato( String pNom, double pPrecio, int pTam, byte[] pImg, int pCat,String pEmpresa){

        nombre_plato = pNom;
        precio = pPrecio;
        tamanio = pTam;
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
                rs.getInt("tamanio"),
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
            String sql = "INSERT INTO platos (nombre_plato,precio,tamanio,imagen,categoria,empresa) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, plato.getNombre_plato());
            pst.setString(2, String.valueOf(plato.getPrecio()));
            pst.setString(3, String.valueOf(plato.getTamanio()));
            pst.setBytes(4, plato.getImagen());
            pst.setInt(5,plato.getCategoria());
            pst.setString(6,plato.getEmpresa());
            ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el plato!");
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

    public static void ModificarPlato(Plato plato, Carta carta, Categoria cat) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE cartas " +
                    "        SET nombre_plato=?," +
                    "            precio=?," +
                    "            tamanio=?," +
                    "            categoria=?," +
                    "            imagen=?," +
                    "            carta=?," +
                    "        WHERE nro_carta=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, plato.getNombre_plato());
            pst.setString(2, String.valueOf(plato.getPrecio()));
            pst.setString(3, String.valueOf(plato.getTamanio()));
            pst.setString(4, String.valueOf(cat.getId_categoria()));
            pst.setBytes(5, plato.getImagen());
            pst.setString(6, carta.getNombre_carta());
            pst.setString(7, String.valueOf(plato.getNombre_plato()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar el plato!");
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

    public static void EliminarPlato(Plato plato) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE platos WHERE id_plato=?";
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

    public static Plato BuscarPlato(String nombre) {

        Plato plato = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM platos WHERE nombre_plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                plato.id_plato = rs.getInt(0);
                plato.nombre_plato = rs.getNString(1);
                plato.precio = rs.getDouble(2);
                plato.tamanio = rs.getInt(3);
                plato.categoria = rs.getInt(4);
                plato.imagen = rs.getBytes(5);

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
            String sql = "SELECT * FROM platos";
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
            String sql = "SELECT * FROM platos where empresa=? and categoria=?";
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
            throw new RuntimeException(ex);
        }
        finally
        {
            //cnn.close();
        }
        return platos;
    }


    //logica (publica)
    public Plato Buscar(String nombre)
    {
        return BuscarPlato(nombre);
    }



    public void Modificar(Plato plato, Carta carta, Categoria cat) {
        ModificarPlato(plato, carta, cat);
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
