package com.LastSolutionTeam.tastit.Interfaces;

import java.io.*;
import java.io.File;

public class Archivo {

    private File directorio;
    private File archivo_img;

    public void crear_directorio_img(){
        try {
            directorio = new File("Imagenes");
            directorio.mkdirs();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void crear_archivo_img(File dir, String ext){
        try {
            archivo_img = new File(dir, "image." + ext);
            archivo_img.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //    public void traer_ruta_archivo(String nombre_archivo){
    //        try {
    //
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //    }

}

// PARA CREAR LA CARPETA EN LA SD EXTERNA
// <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>