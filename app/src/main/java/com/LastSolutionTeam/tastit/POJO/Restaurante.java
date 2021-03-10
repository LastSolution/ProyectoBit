package com.LastSolutionTeam.tastit.POJO;

import android.app.Activity;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Restaurante {

   int ID;
   String NOMBRE;
   String RUT;
   String TIPO;
   Date FECHA;

   //Constructor con ID
    public Restaurante(int ID, String NOMBRE, String RUT, String TIPO, Date FECHA) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.RUT = RUT;
        this.TIPO = TIPO;
        this.FECHA = FECHA;
    }
    //Constructor sin ID
    public Restaurante(String NOMBRE, String RUT, String TIPO) {
        this.NOMBRE = NOMBRE;
        this.RUT = RUT;
        this.TIPO = TIPO;
    }

//GETTER AND SETTERS
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getNOMBRE()
    {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE)
    {
        this.NOMBRE = NOMBRE;
    }

    public String getRUT()
    {
        return RUT;
    }

    public void setRUT(String RUT)
    {
        this.RUT = RUT;
    }

    public String getTIPO()
    {
        return TIPO;
    }

    public void setTIPO(String TIPO)
    {
        this.TIPO = TIPO;
    }

    public Date getFECHA()
    {
        return FECHA;
    }

    public void setFECHA(Date FECHA)
    {
        this.FECHA = FECHA;
    }


    public static int IngresarRestaurante(Restaurante Rest) {
        int ret=0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Connection Conn=Conexion.ObtenerConexion();

        if(Conn!=null)
        {
        try {
            PreparedStatement pst=Conn.prepareStatement("insert into RESTAURANTES (NOMBRE,RUT,TIPO,FECHA) values (?,?,?,?)");
            pst.setString(1,Rest.getNOMBRE());
            pst.setString(2,Rest.getRUT());
            pst.setString(3,Rest.getTIPO());
            pst.setString(4, formatter.format(date));
            ret= pst.executeUpdate();


        }catch (SQLException E)
        {
           return ret;
        }
        }else {
             return 3;
        }
        return ret;
    }

}
