package com.LastSolutionTeam.tastit.POJO;

import com.LastSolutionTeam.tastit.Persistencia.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sugerencia {

     int id;
     String Sugerencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSugerencia() {
        return Sugerencia;
    }

    public void setSugerencia(String sugerencia) {
        Sugerencia = sugerencia;
    }

    public Sugerencia(String sugerencia){

        this.Sugerencia=sugerencia;
    }
    public Sugerencia(int id,String sugerencia){
    this.id=id;
    this.Sugerencia=sugerencia;
    }

    public static int IngresarSugerencia(Sugerencia sugerencia) {
        int ret=0;
        try {

            Connection cnn = Conexion.ObtenerConexion();
            String sql = "INSERT INTO Sugerencias (sugerencia) " +
                    "values (?)";
            PreparedStatement pst = cnn.prepareStatement(sql);
            pst.setString(1, sugerencia.Sugerencia);
            ret = pst.executeUpdate();

        }
        catch (SQLException ex)
        {

        }
        finally
        {
            //cnn.close();
        }
        return ret;
    }

}
