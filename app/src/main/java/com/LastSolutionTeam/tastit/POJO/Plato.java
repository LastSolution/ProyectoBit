package com.LastSolutionTeam.tastit.POJO;

import android.content.Context;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

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
    private String imagen;
    private String nombre_carta;
    private int categoria;

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

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre_carta() {
        return nombre_carta;
    }
    public void setNombre_carta(String nombre_carta) {
        this.nombre_carta = nombre_carta;
    }

    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    //constructor
    public Plato(int pId, String pNom, double pPrecio, int pTam, String pImg, String pCarta, int pCat){
        id_plato = pId;
        nombre_plato = pNom;
        precio = pPrecio;
        tamanio = pTam;
        imagen = pImg;
        nombre_carta = pCarta;
        categoria = pCat;
    }


    //persistencia (privada)
    private static Plato CrearObjeto(ResultSet rs) throws SQLException
    {
        Plato plato = null;

        plato = new Plato(
                rs.getInt("id_plato"),
                rs.getNString("nombre_plato"),
                rs.getDouble("precio_plato"),
                rs.getInt("tamanio"),
                rs.getNString("imagen"),
                rs.getNString("carta"),
                rs.getInt("categoria")
        );
        return plato;
    }

    private static void IngresarPlato(Plato plato, Carta carta, Categoria cat) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO platos (nombre_plato,precio,tamanio,imagen,carta,categoria) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, plato.getNombre_plato());
            pst.setString(2, String.valueOf(plato.getPrecio()));
            pst.setString(3, String.valueOf(plato.getTamanio()));
            pst.setString(4, plato.getImagen());
            pst.setString(5, carta.getNombre_carta());
            pst.setString(6, String.valueOf(cat.getId_categoria()));
            int ret = pst.executeUpdate();

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
    }

    private static void ModificarPlato(Plato plato, Carta carta, Categoria cat) {

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
            pst.setString(5, plato.getImagen());
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

    private static void EliminarPlato(Plato plato) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE platos WHERE id_plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(plato.getId_plato()));
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

    private static Plato BuscarPlato(String nombre) {

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
                plato.imagen = rs.getNString(5);
                plato.nombre_carta = rs.getNString(6);
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


    private static ArrayList<Plato> ListarPlatos() {

        ArrayList<Plato> platos = new ArrayList<Plato>();
        Plato plato = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM Cartas";
            PreparedStatement pst = cnn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                plato=Plato.CrearObjeto(rs);
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

    public void Ingresar(Plato plato, Carta carta, Categoria cat) {
        IngresarPlato(plato, carta, cat);
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
