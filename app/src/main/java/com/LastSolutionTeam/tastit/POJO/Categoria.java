package com.LastSolutionTeam.tastit.POJO;

import android.content.Context;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;
import com.LastSolutionTeam.tastit.POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Categoria {

    //atributos
    private int id_categoria;
    private String nombre_categoria;

    //propiedades
    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }
    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }


    //constructor
    public Categoria(int pId, String pNom){
        id_categoria = pId;
        nombre_categoria = pNom;
    }

    //persistencia
    private static Categoria CrearObjeto(ResultSet rs) throws SQLException
    {
        Categoria cat = null;

        cat = new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria")
        );
        return cat;
    }

    private static void IngresarCategoria(Categoria cat) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO categorias (nombre_categoria) " +
                    "values (?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, cat.getNombre_categoria());
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar la categoria!");
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

    private static void ModificarCategoria(Categoria cat) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE categorias " +
                    "        SET nombre_categoria=?," +
                    "        WHERE id_categoria=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, cat.getNombre_categoria());
            pst.setString(2, String.valueOf(cat.getId_categoria()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar la categoria!");
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

    private static void EliminarCategoria(Categoria cat) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE categorias WHERE id_categoria=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(cat.getId_categoria()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar la categoria!");
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

    private static Categoria BuscarCategoriaPorNombre(String nombre) {

        Categoria cat = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM categorias WHERE nombre_categoria=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                cat.id_categoria = rs.getInt(0);
                cat.nombre_categoria = rs.getNString(1);
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
        return cat;
    }

    private static ArrayList<Categoria> ListarCategorias() {

        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM categorias";
            PreparedStatement pst = cnn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Categoria cat = Categoria.CrearObjeto(rs);
                categorias.add(cat);
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
        return categorias;
    }


    //logica
    public Categoria Buscar(String nombre) {
        return BuscarCategoriaPorNombre(nombre);
    }

    public void Ingresar(Categoria cat) {
        IngresarCategoria(cat);
    }

    public void Modificar(Categoria cat) {
        ModificarCategoria(cat);
    }

    public void Eliminar(Categoria cat) {
        EliminarCategoria(cat);
    }

    public ArrayList<Categoria> Listar() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        categorias.addAll(ListarCategorias());
        return categorias;
    }

}