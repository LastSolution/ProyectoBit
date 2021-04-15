package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;
import com.LastSolutionTeam.tastit.POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Reserva {

    //atributos
    private int nro_reserva;
    private int nro_tarjeta;
    private int metodo_pago;
    private int mesa;
    private int cliente;
    private Date fecha_solicitud;


    //propiedades
    public int getNro_reserva() {
        return nro_reserva;
    }
    public void setNro_reserva(int nro_reserva) {
        this.nro_reserva = nro_reserva;
    }

    public int getNro_tarjeta() {
        return nro_tarjeta;
    }
    public void setNro_tarjeta(int nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public int getMetodo_pago() {
        return metodo_pago;
    }
    public void setMetodo_pago(int metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public int getMesa() {
        return mesa;
    }
    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getCliente() {
        return cliente;
    }
    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }
    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    //constructor
    public Reserva(int pNro, int pTarjeta, int pPago, int pMesa, int pCliente, Date pFecha){
        nro_reserva = pNro;
        nro_tarjeta = pTarjeta;
        metodo_pago = pPago;
        mesa = pMesa;
        cliente = pCliente;
        fecha_solicitud = pFecha;
    }


    //persistencia (privada)
    private static Reserva CrearObjeto(ResultSet rs) throws SQLException
    {
        Reserva r = null;

        r = new Reserva(
                rs.getInt("nro_reserva"),
                rs.getInt("nro_tarjeta"),
                rs.getInt("metodo_pago"),
                rs.getInt("mesa"),
                rs.getInt("cliente"),
                rs.getDate("fecha_solicitud")
        );
        return r;
    }

    private static void IngresarReserva(Reserva r, Mesa m, Cliente c) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO reservas (nro_tarjeta,metodo_pago,mesa,cliente) " +
                    "values (?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(r.getNro_tarjeta()));
            pst.setString(2, String.valueOf(r.getMetodo_pago()));
            pst.setString(3, String.valueOf(m.getNro_mesa()));
            pst.setString(4, String.valueOf(c.getId_cliente()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar la reserva!");
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

    private static void ModificarReserva(Reserva r, Mesa m, Cliente c) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE reservas " +
                    "        SET nro_tarjeta=?," +
                    "            metodo_pago=?," +
                    "            mesa=?," +
                    "            cliente=?," +
                    "        WHERE nro_reserva=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(r.getMetodo_pago()));
            pst.setString(2, String.valueOf(m.getNro_mesa()));
            pst.setString(3, String.valueOf(c.getId_cliente()));
            pst.setString(4, String.valueOf(r.getNro_tarjeta()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar la reserva!");
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

    private static void EliminarReserva(Reserva r) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE reservas WHERE nro_reserva=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(r.getNro_reserva()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar la reserva!");
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

    private static Reserva BuscarReserva(int numero) {

        Reserva r = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM reservas WHERE nro_reserva=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(numero));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                r.nro_reserva = rs.getInt(0);
                r.nro_tarjeta = rs.getInt(1);
                r.metodo_pago = rs.getInt(2);
                r.mesa = rs.getInt(3);
                r.cliente = rs.getInt(4);
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
        return r;
    }


    private static ArrayList<Reserva> ListarReservas() {

        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        Reserva r = null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM mesas";
            PreparedStatement pst = cnn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                r = Reserva.CrearObjeto(rs);
                reservas.add(r);

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
        return reservas;
    }


    //logica (publica)
    public Reserva Buscar(int numero)
    {
        return BuscarReserva(numero);
    }

    public void Ingresar(Reserva r, Mesa m, Cliente c) {
        IngresarReserva(r, m, c);
    }

    public void Modificar(Reserva r, Mesa m, Cliente c) {
        ModificarReserva(r, m, c);
    }

    public void Eliminar(Reserva r) {
        EliminarReserva(r);
    }

    public ArrayList<Reserva> Listar()
    {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        reservas.addAll(ListarReservas());
        return reservas;
    }
}
