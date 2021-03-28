package com.LastSolutionTeam.tastit.Persistencia;

import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sourceforge.jtds.jdbc.Driver;

public class Conexion {

    private static String IP_PUERTO="192.168.1.5:1433";    // IP:PUERTO
    private static String DATABASE ="TastIT";              // BASE DE DATOS
    private static String INSTANCE ="lastsolution";        // INSTANCIA
    private static String USERNAME ="sa";                  // USER
    private static String PASSWORD ="Last2021-";           // PASSWORD

    public static String stringConnection = "jdbc:jtds:sqlserver://"+IP_PUERTO+"/"+
            DATABASE+";instance="+INSTANCE+";user="+USERNAME+";password="+PASSWORD+"";

    public static Connection cnn = null;

    public static String Cnn() {
        return stringConnection;
    }

    public Conexion()
    {
        //constructor
    }

    public static Connection ObtenerConexion()
    {
        try {
            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection(stringConnection);
        }
        catch (Exception Ex)
        {
            Log.w("Error connection", "" + Ex.getStackTrace().toString() );
        }
        return cnn;
    }

    /*public ResultSet consultarSql(String sql)
    {
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs;
        }
        catch(SQLException e)
        {
            return null;
        }
    }*/

    /*public static void Conectar()
    {
        try {
            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection(stringConnection);
        }
        catch (Exception Ex)
        {
            Log.w("Error connection", "" + Ex.getStackTrace().toString() );
        }
    }

    public static void Desconectar()
    {
        try {
            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection(stringConnection);
        }
        catch (Exception Ex)
        {
            Log.w("Error connection", "" + Ex.getStackTrace().toString() );
        }
    }*/

}
