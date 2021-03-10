package com.LastSolutionTeam.tastit.Persistencia;


import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sourceforge.jtds.jdbc.Driver;

public class Conexion {

    private static String I P_PUERTO="";           // IP DE LA PC : PUERTO DE SQL        
    private static String DATABASE="";             //NOMBRE DE LA BASE DE DATOS
    private static String INSTANCE="";            //INSTANCIA
    private static String USERNAME="";            // USER
    private static String PASSWORD="";           // PASSWORD


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
