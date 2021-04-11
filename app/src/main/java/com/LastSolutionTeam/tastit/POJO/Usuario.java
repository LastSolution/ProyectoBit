package com.LastSolutionTeam.tastit.POJO;

import android.content.Context;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

    //atributos
    private int id_usuario;
    private String username;
    private String password;
    private String tipo;
    private String rut_empresa;

    //propiedades
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRut_empresa() {
        return rut_empresa;
    }
    public void setRut_empresa(String rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    //constructor
    public Usuario(int pId, String pUser, String pPass, String pTipo, String pRut) {
        id_usuario = pId;
        username = pUser;
        password = pPass;
        tipo = pTipo;
        rut_empresa = pRut;
    }
    public Usuario(String pUser, String pPass) {

        username = pUser;
        password = pPass;

    }

    //persistencia
    private static Usuario CrearObjeto(ResultSet rs) throws SQLException
    {
        Usuario user = null;

        user =new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("tipo"),
                rs.getString("empresa")
        );
        return user;
    }

    public static void IngresarUsuario(Usuario user, Context context) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO usuarios (username,password,tipo,empresa) values (?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(user.getId_usuario()));
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getPassword());
            pst.setString(4, String.valueOf(user.getTipo()));
            pst.setString(5, user.getRut_empresa());
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo ingresar el usuario!");
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

    public static void ModificarUsuario(Usuario user) {

        try {
            Connection cnn= Conexion.ObtenerConexion();
            String sql = "UPDATE usuarios " +
                    "        SET username=?," +
                    "            password=?," +
                    "            tipo=?," +
                    "            empresa=? " +
                    "        WHERE id_usuario=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, String.valueOf(user.getTipo()));
            pst.setString(4, user.getRut_empresa());
            pst.setString(5, String.valueOf(user.getId_usuario()));

            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo modificar el usuario!");
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

    public static void EliminarUsuario(Usuario user,Context context) {

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "DELETE usuarios WHERE id_usuario=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(user.getId_usuario()));
            int ret = pst.executeUpdate();

            if (ret == 0)
                throw new RuntimeException("No se pudo eliminar el usuario!");
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

    public static Usuario BuscarUsuario(int id,Context context) {

        Usuario user = null;

        try {
            Connection cnn = Conexion.ObtenerConexion();
            String sql = "SELECT * FROM usuarios WHERE id_usuario=?";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                user.id_usuario = rs.getInt(0);
                user.username = rs.getNString(1);
                user.password = rs.getNString(2);
                user.tipo=rs.getString(3);
                user.rut_empresa=rs.getNString(4);


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
        return user;
    }

    public static Usuario Login(String username, String password, Context context) {


        Usuario user=null;
        try {
            Connection cnn = Conexion.ObtenerConexion();
            if(cnn!=null){
                String sql = "SELECT * FROM usuarios WHERE username=? AND password=?";
                PreparedStatement pst = cnn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    user=CrearObjeto(rs);

                }
            }else{
                Toast.makeText(context,"Error al conectar",Toast.LENGTH_SHORT);
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
        return user;

    }


}