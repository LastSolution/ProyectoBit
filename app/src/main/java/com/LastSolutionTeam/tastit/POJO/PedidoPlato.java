package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoPlato {

    private  int idpedido;
    private int idplato;
    private int cantidad;

    public PedidoPlato(int pedido, int plato, int cantidad) {
        idpedido=pedido;
        idplato=plato;
        this.cantidad=cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdplato() {
        return idplato;
    }

    public void setIdplato(int idplato) {
        this.idplato = idplato;
    }




    private static PedidoPlato CrearObjeto(ResultSet rs) throws SQLException
    {
        PedidoPlato p = null;

        p = new PedidoPlato(
                rs.getInt("pedido"),
                rs.getInt("plato"),
                rs.getInt("cantidad")
        );
        return p;
    }

    public static int IngresarPedido(PedidoPlato pedidoPlato){
        int ret=0;
        try {

            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO pedidos_platos (pedido,plato,cantidad) " +
                    "values (?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, pedidoPlato.getIdpedido());
            pst.setInt(2, pedidoPlato.getIdplato());
            pst.setInt(3, pedidoPlato.getCantidad());
            ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el pedido!");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
      return ret;
    }
    public static int ModificarCantidad(int cantidad,int idpedido){
        int ret=0;
        try {

            Connection cnn = Conexion.ObtenerConexion();
            String sql = "update pedidos_platos set cantidad=? where pedido=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, cantidad);
            pst.setInt(2, idpedido);
            ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo agregar cantidad");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        return ret;
    }
    public static ArrayList<PedidoPlato> ListarPedidos(int idpedido) {

        ArrayList<PedidoPlato> pedidos = new ArrayList<PedidoPlato>();
        PedidoPlato p = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM pedidos_platos where pedido=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, idpedido);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                p = PedidoPlato.CrearObjeto(rs);
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
    public static int VerificarPlatoenPedido(int idpedido,int idplato) {

        int retorno=0;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM pedidos_platos where pedido=? and plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, idpedido);
            pst.setInt(2, idplato);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
              retorno=1;
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
        return retorno;
    }
    public static PedidoPlato Buscar(int idpedido,int idplato) {


        PedidoPlato p = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM pedidos_platos where pedido=? and plato=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setInt(1, idpedido);
            pst.setInt(2, idplato);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                p = PedidoPlato.CrearObjeto(rs);

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

 }
