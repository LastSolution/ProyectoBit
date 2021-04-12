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

public class Carta {

    //atributos
    private int nro_carta;
    private Date fecha_carta;
    private String nombre_carta;
    private String imgCarta;
    private int nro_local;


    //propiedades
    public int getNro_carta() {
        return nro_carta;
    }
    public void setNro_carta(int nro_carta) {
        this.nro_carta = nro_carta;
    }
    public Date getFecha_carta() {
        return fecha_carta;
    }
    public void setFecha_carta(Date fecha_carta) {
        this.fecha_carta = fecha_carta;
    }
    public String getNombre_carta() {
        return nombre_carta;
    }
    public void setNombre_carta(String nombre_carta) {
        this.nombre_carta = nombre_carta;
    }
    public String getImgCarta() {
        return imgCarta;
    }
    public void setImgCarta(String imgCarta) {
        this.imgCarta = imgCarta;
    }
    public int getNro_local() {
        return nro_local;
    }
    public void setNro_local(int nro_local) {
        this.nro_local = nro_local;
    }


    //constructor
    public Carta(int pNro, Date pFecha, String pNom, String pImg, int pLocal){
        nro_carta = pNro;
        fecha_carta = pFecha;
        nombre_carta = pNom;
        imgCarta = pImg;
        nro_local = pLocal;
    }


    //persistencia (privada)
    private static Carta CrearObjeto(ResultSet rs) throws SQLException
    {
        Carta carta = null;

        carta = new Carta(
                rs.getInt("nro_carta"),
                rs.getDate("fecha_carta"),
                rs.getString("nombre_carta"),
                rs.getString("imgCarta"),
                rs.getInt("local")
        );
        return carta;
    }

    private static void IngresarCarta(Carta carta, Local local) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO cartas (fecha_carta,nombre_carta,imgCarta,local) " +
                    "values (?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(carta.getNro_carta()));
            pst.setString(2, carta.getNombre_carta());
            pst.setString(3, carta.getImgCarta());
            pst.setString(4, String.valueOf(local.getNro_local()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar la carta!");
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

    private static void ModificarCarta(Carta carta, Local local) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE cartas " +
                    "        SET fecha_carta=?," +
                    "            nombre_carta=?," +
                    "            imgCarta=?," +
                    "            local=?," +
                    "        WHERE nro_carta=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(carta.getFecha_carta()));
            pst.setString(2, carta.getNombre_carta());
            pst.setString(3, carta.getImgCarta());
            pst.setString(4, String.valueOf(local.getNro_local()));
            pst.setString(5, String.valueOf(carta.getNro_carta()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar la carta!");
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

    private static void EliminarCarta(Carta carta) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE cartas WHERE nro_carta=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(carta.getNro_carta()));
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

    private static Carta BuscarCarta(String nombre) {

        Carta carta = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM cartas WHERE nombre_carta=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, nombre);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                carta.nro_carta = rs.getInt(0);
                carta.fecha_carta = rs.getDate(1);
                carta.nombre_carta = rs.getNString(2);
                carta.imgCarta = rs.getNString(3);
                carta.nro_local = rs.getInt(4);
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
        return carta;
    }


    private static ArrayList<Carta> ListarCartas() {

        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta carta = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM Cartas";
            PreparedStatement pst = cnn.prepareStatement(sql);


            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                carta=Carta.CrearObjeto(rs);
                cartas.add(carta);

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
        return cartas;
    }


    //logica (publica)
    public Carta Buscar(String rut)
    {
        return BuscarCarta(rut);
    }

    public void Ingresar(Carta carta, Local local) {
        IngresarCarta(carta, local);
    }

    public void Modificar(Carta carta, Local local) {
        ModificarCarta(carta, local);
    }

    public void Eliminar(Carta carta, Local local) {

        EliminarCarta(carta);
    }

    public ArrayList<Carta> Listar()
    {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.addAll(ListarCartas());
        return cartas;
    }
}
