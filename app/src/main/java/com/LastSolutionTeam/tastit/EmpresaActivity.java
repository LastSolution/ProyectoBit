package com.LastSolutionTeam.tastit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.Adaptadores.EmpresaAdaptador;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class EmpresaActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITO_PERMISO = 1;
    int registro=0;
    Context context;
    EditText NomEmpresa;
    EditText RutEmpresa;
    EditText Direccion;
    EditText TelEmpresa;
    EditText CorreoEmpresa;
    TextView Titulo;
    Button btnAdd_Modify;
    ImageView Logo;
    int Modifica=0;
    String nombreempresa;
    String DireccionEmpresa;
    String rutempresa;
    String telempresa;
    String correoempresa;
    byte[] logo;

    private Activity activity;
    private Bitmap convertirlogoBitMap(byte [] logo){
        Bitmap bmp = BitmapFactory.decodeByteArray(logo, 0, logo.length);
        return bmp;

    }
    public void AbrirAlmacenamiento(View view){
        SolicitarPermiso();
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
       startActivityForResult(intent,10);
    }
    
    public void mostrarSnackbar(View view, String texto){

        Snackbar snackbar = Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#558b2f"));
        snackbar.show();
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

    public Bitmap ObtenerImagen(){
        Logo.buildDrawingCache();
        Bitmap bitmap = Logo.getDrawingCache();
        return bitmap;
    }
    private   byte[] ImagenBlob(Bitmap bitmap){
        // tamaño del baos depende del tamaño de tus imagenes en promedio
        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        byte[] blob = baos.toByteArray();
        return blob;



    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        context=getApplicationContext();
        activity=this;
        Logo =(ImageView) findViewById(R.id.imgLogo);
        NomEmpresa=(EditText) findViewById(R.id.EditEmpNombre);
        RutEmpresa=(EditText) findViewById(R.id.EditEmpRut);
        TelEmpresa=(EditText) findViewById(R.id.EditEmpTel);
        CorreoEmpresa=(EditText) findViewById(R.id.EditEmpCorreo);
        Direccion=(EditText) findViewById(R.id.EditDireccion);
        Titulo=(TextView) findViewById(R.id.tituloEmpresa) ;
        btnAdd_Modify=(Button) findViewById(R.id.BtnEmpresaAdd);

        //obtiene parametros del intent (si tiene Carga los datos para editar(MODIFICACION))
        Bundle parametros =getIntent().getExtras();
        if(parametros!=null && parametros.size()>2){
            Modifica=1;
            nombreempresa=parametros.getString("nombre");
            rutempresa=parametros.getString("rut");
            telempresa=parametros.getString("telefono");
            correoempresa=parametros.getString("correo");
            DireccionEmpresa=parametros.getString("direccion");
            NomEmpresa.setText(nombreempresa);
            RutEmpresa.setText(rutempresa);
            TelEmpresa.setText(telempresa);
            CorreoEmpresa.setText(correoempresa);
            Direccion.setText(DireccionEmpresa);

            btnAdd_Modify.setText("Editar");
            Titulo.setText("MODIFICA LOS DATOS");
            if(parametros.getByteArray("imagen")!=null){
                Logo.setImageBitmap(convertirlogoBitMap(parametros.getByteArray("imagen")));
            }

        }else if (parametros!=null && parametros.size()==1){
            registro=parametros.getInt("registro");
            btnAdd_Modify.setText("Registrarse");

        }

        btnAdd_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Modifica==1){

                nombreempresa=NomEmpresa.getText().toString();
                rutempresa=RutEmpresa.getText().toString();
                telempresa=TelEmpresa.getText().toString();
                DireccionEmpresa=Direccion.getText().toString();
                correoempresa=CorreoEmpresa.getText().toString();
                logo=ImagenBlob(ObtenerImagen());

                Empresa empresa;
                empresa =new Empresa(
                        rutempresa,
                        nombreempresa,
                        telempresa,
                        correoempresa,
                        logo,
                        DireccionEmpresa
                );
                if(Empresa.ModificarEmpresa(empresa)==1)
                {
                    mostrarSnackbar(v,"EMPRESA MODIFICADA CON EXITO");
                    Intent intent = new Intent(v.getContext(), AbmActivity.class);
                    startActivity(intent);
                }
                else   {
                    mostrarSnackbar(v,"ERROR AL MODIFICAR EMPRESA");

                }

            }else {

                nombreempresa=NomEmpresa.getText().toString();
                rutempresa=RutEmpresa.getText().toString();
                telempresa=TelEmpresa.getText().toString();
                DireccionEmpresa=Direccion.getText().toString();
                correoempresa=CorreoEmpresa.getText().toString();
                logo=ImagenBlob(ObtenerImagen());

                Empresa empresa;
                empresa =new Empresa(
                        rutempresa,
                        nombreempresa,
                        telempresa,
                        correoempresa,
                        logo,
                        DireccionEmpresa
                );
                if(Empresa.IngresarEmpresa(empresa,context)==1)
                {

                    if(registro==1){
                    mostrarSnackbar(v,"EMPRESA REGISTRADA CON EXITO");
                    Intent intent = new Intent(v.getContext(), UsuariosActivity.class);
                    intent.putExtra("empresa",empresa.getRut());
                    intent.putExtra("registro",1);
                    startActivity(intent);

                        }else {
                    mostrarSnackbar(v,"EMPRESA INGRESADA CON EXITO");
                    Intent intent = new Intent(v.getContext(), AbmActivity.class);
                    startActivity(intent);
                }


                }
                else   {
                    mostrarSnackbar(v,"ERROR AL INGRESAR EMPRESA");
                }

            }


        };


    });
    }
}