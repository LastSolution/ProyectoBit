package com.LastSolutionTeam.tastit.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permisos {


    public boolean ChequearPermisoAlmacenamiento(Context context){
        int resultado= ContextCompat.checkSelfPermission(context, Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        if(resultado== PackageManager.PERMISSION_GRANTED){
            return  true;
        }else{
            return false;
        }

    }
    public void SolicitarPermisoAlmacenamiento(Activity activity, int CODIGO_SOLICITO_PERMISO){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity ,Manifest.permission.MANAGE_EXTERNAL_STORAGE )){

        }else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.MANAGE_EXTERNAL_STORAGE},CODIGO_SOLICITO_PERMISO);
        }
    }

}
