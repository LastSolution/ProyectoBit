package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;
import com.LastSolutionTeam.tastit.POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Mesa {

    //atributos
    private int nro_mesa;
    private String x;
    private String y;
    private String img;
    private int carta;
    private int usuario;
    private int pedido;
    private int local;
    private Date fecha_atencion;
    private int comensales;


    //propiedades
    public int getNro_mesa() {
        return nro_mesa;
    }
    public void setNro_mesa(int nro_mesa) {
        this.nro_mesa = nro_mesa;
    }

    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public int getCarta() {
        return carta;
    }
    public void setCarta(int carta) {
        this.carta = carta;
    }

    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getPedido() {
        return pedido;
    }
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public int getLocal() {
        return local;
    }
    public void setLocal(int local) {
        this.local = local;
    }

    public Date getFecha_atencion() {
        return fecha_atencion;
    }
    public void setFecha_atencion(Date fecha_atencion) {
        this.fecha_atencion = fecha_atencion;
    }

    public int getComensales() {
        return comensales;
    }
    public void setComensales(int comensales) {
        this.comensales = comensales;
    }


    //constructor
    public Mesa(int pNro, String pX, String pY, String pImg, int pCarta, int pUser, int pPedido,
                 int pLocal, Date pFecha, int pCom){
        nro_mesa = pNro;
        x = pX;
        y = pY;
        img = pImg;
        carta = pCarta;
        usuario = pUser;
        pedido = pPedido;
        local = pLocal;
        fecha_atencion = pFecha;
        comensales = pCom;
    }


    //persistencia (privada)
    private static Mesa CrearObjeto(ResultSet rs) throws SQLException
    {
        Mesa mesa = null;

        mesa = new Mesa(
                rs.getInt("nro_mesa"),
                rs.getString("x"),
                rs.getString("y"),
                rs.getString("img"),
                rs.getInt("carta"),
                rs.getInt("usuario"),
                rs.getInt("pedido"),
                rs.getInt("local"),
                rs.getDate("fecha_atencion"),
                rs.getInt("cant_comensales")
        );
        return mesa;
    }

    private static void IngresarMesa(Mesa mesa, Usuario user, Pedido pedido, Local local) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO mesas (x,y,img,carta,usuario,pedido,local,fecha_atencion,cant_comensales) " +
                    "values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, mesa.getX());
            pst.setString(2, mesa.getY());
            pst.setString(3, mesa.getImg());
            pst.setString(4, String.valueOf(mesa.getCarta()));
            pst.setString(5, String.valueOf(user.getId_usuario()));
            pst.setString(6, String.valueOf(pedido.getId_pedido()));
            pst.setString(7, String.valueOf(local.getNro_local()));
            pst.setString(8, String.valueOf(mesa.getFecha_atencion()));
            pst.setString(9, String.valueOf(mesa.getComensales()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar la mesa!");
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

    private static void ModificarMesa(Mesa mesa, Usuario user, Pedido pedido, Local local) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE mesas " +
                    "        SET x=?," +
                    "            y=?," +
                    "            img=?," +
                    "            carta=?," +
                    "            usuario=?," +
                    "            pedido=?," +
                    "            local=?," +
                    "            fecha_atencion=?," +
                    "            cant_comensales=?," +
                    "        WHERE nro_mesa=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, mesa.getX());
            pst.setString(2, mesa.getY());
            pst.setString(3, mesa.getImg());
            pst.setString(4, String.valueOf(user.getId_usuario()));
            pst.setString(5, String.valueOf(pedido.getId_pedido()));
            pst.setString(6, String.valueOf(local.getNro_local()));
            pst.setString(7, String.valueOf(mesa.getFecha_atencion()));
            pst.setString(8, String.valueOf(mesa.getComensales()));
            pst.setString(9, String.valueOf(mesa.getNro_mesa()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar la mesa!");
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

    private static void EliminarMesa(Mesa mesa) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE mesas WHERE nro_mesa=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(mesa.getNro_mesa()));
            int ret = pst.executeUpdate();



            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar la mesa!");
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

    private static Mesa BuscarMesa(int numero) {

        Mesa mesa = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM mesas WHERE nro_mesa=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(numero));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                mesa.nro_mesa = rs.getInt(0);
                mesa.x = rs.getNString(1);
                mesa.y = rs.getNString(2);
                mesa.img = rs.getNString(3);
                mesa.carta = rs.getInt(4);
                mesa.usuario = rs.getInt(5);
                mesa.pedido = rs.getInt(6);
                mesa.local = rs.getInt(7);
                mesa.fecha_atencion = rs.getDate(8);
                mesa.comensales = rs.getInt(9);
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
        return mesa;
    }


    private static ArrayList<Mesa> ListarMesas() {

        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        Mesa m = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM mesas";
            PreparedStatement pst = cnn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                m = Mesa.CrearObjeto(rs);
                mesas.add(m);

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
        return mesas;
    }


    //logica (publica)
    public Mesa Buscar(int numero)
    {
        return BuscarMesa(numero);
    }

    public void Ingresar(Mesa mesa, Usuario user, Pedido pedido, Local local) {
        IngresarMesa(mesa, user, pedido, local);
    }

    public void Modificar(Mesa mesa, Usuario user, Pedido pedido, Local local) {
        ModificarMesa(mesa, user, pedido, local);
    }

    public void Eliminar(Mesa mesa) {
        EliminarMesa(mesa);
    }

    public ArrayList<Mesa> Listar()
    {
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        mesas.addAll(ListarMesas());
        return mesas;
    }
}
