package com.LastSolutionTeam.tastit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.POJO.Empresa;

public class EmpresaActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITO_PERMISO = 1;

    Context context;
    EditText NomEmpresa;
    EditText RutEmpresa;
    EditText TelEmpresa;
    EditText CorreoEmpresa;
    TextView Titulo;
    Button btnAdd_Modify;
    ImageView Logo;

    String nombreempresa;
    String rutempresa;
    String telempresa;
    String correoempresa;

    private Activity activity;
    public void AbrirAlmacenamiento(View view){
        SolicitarPermiso();
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
       startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK ){
            Uri path=data.getData();
            Logo.setImageURI(path);

        }
    }

    public boolean ChequearPermisoAlmacenamiento(){
        int resultado= ContextCompat.checkSelfPermission(context, Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        if(resultado== PackageManager.PERMISSION_GRANTED){
            return  true;
        }else{
            return false;
        }

    }
    public void SolicitarPermiso(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.MANAGE_EXTERNAL_STORAGE )){

        }else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.MANAGE_EXTERNAL_STORAGE},CODIGO_SOLICITO_PERMISO);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITO_PERMISO:
                if(ChequearPermisoAlmacenamiento()){
                    Toast.makeText(context,"EL PERMISO PARA ALMACENAMIENTO YA ESTA ACTIVO",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(context,"EL PERMISO PARA ALMACENAMIENTO NO ESTA ACTIVO",Toast.LENGTH_SHORT);
                }



        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        context=this;
        activity=this;
        Logo =(ImageView) findViewById(R.id.imgLogo);
        NomEmpresa=(EditText) findViewById(R.id.EditEmpNombre);
        RutEmpresa=(EditText) findViewById(R.id.EditEmpRut);
        TelEmpresa=(EditText) findViewById(R.id.EditEmpTel);
        CorreoEmpresa=(EditText) findViewById(R.id.EditEmpCorreo);
        Titulo=(TextView) findViewById(R.id.tituloEmpresa) ;
        btnAdd_Modify=(Button) findViewById(R.id.BtnEmpresaAdd);
        Bundle parametros =getIntent().getExtras();
        if(parametros!=null){

            nombreempresa=parametros.getString("nombre");
            rutempresa=parametros.getString("rut");
            telempresa=parametros.getString("telefono");
            correoempresa=parametros.getString("correo");

            NomEmpresa.setText(nombreempresa);
            RutEmpresa.setText(rutempresa);
            TelEmpresa.setText(telempresa);
            CorreoEmpresa.setText(correoempresa);
            btnAdd_Modify.setText("Editar");
            Titulo.setText("MODIFICA LOS DATOS");

        }

        btnAdd_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombreempresa=NomEmpresa.getText().toString();
                rutempresa=RutEmpresa.getText().toString();
                telempresa=TelEmpresa.getText().toString();
                correoempresa=CorreoEmpresa.getText().toString();

                Empresa empresa;
                empresa =new Empresa(
                        rutempresa,
                        nombreempresa,
                        telempresa,
                        correoempresa,
                        "Logo"
                       );
               if(Empresa.IngresarEmpresa(empresa,context)==1){
                   Toast.makeText(context,"Empresa Ingresada Con Exito",Toast.LENGTH_SHORT);
               }
             else   {
                   Toast.makeText(context,"Error Al ingresar empresa",Toast.LENGTH_SHORT);
               }
            }
        });

    }
}