package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pedido {

    //atributos
    private int id_pedido;
    private int estado;
    private double precio_total;

    //propiedades
    public int getId_pedido() {
        return id_pedido;
    }
    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getPrecio_total() {
        return precio_total;
    }
    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    //constructor
    public Pedido(int pId, int pEstado, double pPrecio){
        id_pedido = pId;
        estado = pEstado;
        precio_total = pPrecio;
    }


    //persistencia (privada)
    private static Pedido CrearObjeto(ResultSet rs) throws SQLException
    {
        Pedido p = null;

        p = new Pedido(
                rs.getInt("id_plato"),
                rs.getInt("estado"),
                rs.getDouble("precio_total")
        );
        return p;
    }

    private static void IngresarPedido(Pedido pedido) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO pedido (estado,precio_total) " +
                    "values (?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(pedido.getEstado()));
            pst.setString(2, String.valueOf(pedido.getPrecio_total()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el pedido!");
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

    private static void RegistrarPedido(Pedido pedido, Plato plato, int cantidad) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO pedidos_platos (pedido, plato, cantidad) " +
                    "values (?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(pedido.getId_pedido()));
            pst.setString(2, String.valueOf(plato.getId_plato()));
            pst.setString(3, String.valueOf(cantidad));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el pedidos/platos/cantidad!");
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



    private static void ModificarPedido(Pedido pedido) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE pedidos " +
                    "        SET estado=?," +
                    "            precio_total=?," +
                    "        WHERE id_pedido=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(pedido.getEstado()));
            pst.setString(2, String.valueOf(pedido.getPrecio_total()));
            pst.setString(3, String.valueOf(pedido.getId_pedido()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar el pedido!");
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

    private static void EliminarPedido(Pedido pedido) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE pedido WHERE id_pedido=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(pedido.getId_pedido()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar el pedido!");
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

    private static Pedido BuscarPedido(int id) {

        Pedido p = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM pedidos WHERE id_pedido=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                p.id_pedido = rs.getInt(0);
                p.estado = rs.getInt(1);
                p.precio_total = rs.getDouble(2);
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
        return p;
    }


    private static ArrayList<Pedido> ListarPedidos() {

        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido p = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM Pedidos";
            PreparedStatement pst = cnn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                p = Pedido.CrearObjeto(rs);
                pedidos.add(p);
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
        return pedidos;
    }


    //logica (publica)
    public Pedido Buscar(int p)
    {
        return BuscarPedido(p);
    }

    public void Ingresar(Pedido p) {
        IngresarPedido(p);
    }

    public void Registrar(Pedido p, Plato c, int k) {
        RegistrarPedido(p, c, k);
    }


    public void Modificar(Pedido p) {
        ModificarPedido(p);
    }

    public void Eliminar(Pedido p) {
        EliminarPedido(p);
    }

    public ArrayList<Pedido> Listar() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.addAll(ListarPedidos());
        return pedidos;
    }

}
