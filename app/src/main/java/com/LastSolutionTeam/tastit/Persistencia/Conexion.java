package com.LastSolutionTeam.tastit.Persistencia;


import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sourceforge.jtds.jdbc.Driver;

public class Conexion {

    private static String IP_PUERTO="192.168.1.5:1433";   // IP DE LA PC : PUERTO DE SQL
    private static String DATABASE="TASTIT";              //NOMBRE DE LA BASE DE DATOS
    private static String INSTANCE="SQLEXPRESS";          //INSTANCIA
    private static String USERNAME="psalazar";            // USER
    private static String PASSWORD="salazar1988";         // PASSWORD


    public static Connection ObtenerConexion()
    {
        Connection Con=null;


        try {
            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Con= DriverManager.getConnection("jdbc:jtds:sqlserver://"+IP_PUERTO+"/"+DATABASE+";instance="+INSTANCE+";user="+USERNAME+";password="+PASSWORD+"");

        }catch (Exception Ex)
        {
            Log.w("Error connection", "" + Ex.getStackTrace().toString() );

        }
        return Con;
    }


}
