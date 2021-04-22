package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;
import com.LastSolutionTeam.tastit.POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente {

    //atributos
    private int id_cliente;
    private String rut_cliente;
    private String nombre_cliente;

    //propiedades
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }
    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }


    //constructor
    public Cliente(int pId, String pRut, String pNom){
        id_cliente = pId;
        rut_cliente = pRut;
        nombre_cliente = pNom;
    }

    //persistencia
    public static Cliente CrearObjeto(ResultSet rs) throws SQLException
    {
        Cliente c = null;

        c = new Cliente(
                rs.getInt("id_categoria"),
                rs.getString("rut_categoria"),
                rs.getString("nombre_categoria")
        );
        return c;
    }

    public static int IngresarCliente(String Nombre,String Rut) {
            int id=0;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO clientes (rut_cliente, nombre_cliente) " +
                    "values (?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, Nombre);
            pst.setString(2, Rut);
            int ret = pst.executeUpdate();
            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el cliente!");
            ResultSet rs=pst.getGeneratedKeys();
            if(rs.next()){
               id=rs.getInt(1);
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
        return id;
    }

    private static void ModificarCliente(Cliente c) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE clientes " +
                    "        SET rut_cliente=?," +
                    "            nombre_cliente=?," +
                    "        WHERE id_cliente=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, c.getRut_cliente());
            pst.setString(2, c.getNombre_cliente());
            pst.setString(3, String.valueOf(c.getId_cliente()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar el cliente!");
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

    private static void EliminarCliente(Cliente c) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE clientes WHERE id_cliente=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(c.getId_cliente()));
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

    public static Cliente BuscarCliente(int id_cliente) {

        Cliente c = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM clientes WHERE nombre_cliente=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, id_cliente);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                c.id_cliente = rs.getInt(0);
                c.rut_cliente = rs.getNString(1);
                c.nombre_cliente = rs.getNString(2);
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
        return c;
    }

    private static ArrayList<Cliente> ListarClientes() {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM clientes";
            PreparedStatement pst = cnn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Cliente c = Cliente.CrearObjeto(rs);
                clientes.add(c);
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
        return clientes;
    }


    //logica



    public void Modificar(Cliente cat) {
        ModificarCliente(cat);
    }

    public void Eliminar(Cliente cat) {
        EliminarCliente(cat);
    }

    public ArrayList<Cliente> Listar() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.addAll(ListarClientes());
        return clientes;
    }

}