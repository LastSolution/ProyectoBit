package com.LastSolutionTeam.tastit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.POJO.Categoria;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Plato;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PlatosActivity extends AppCompatActivity {
TextView txttitulo;
EditText etnombre;
EditText etprecio;
EditText descripcion;
ImageView imgPlato;
Spinner spinCat;
Spinner spinEmp;
String rutempresa;
Button btnNuevoPlato;
Context context;
Categoria categoria;
Empresa empresaselect;
int modificar=0;
private ArrayList<Categoria> ListaCategorias=new ArrayList<Categoria>();
    private ArrayList<Empresa> ListaEmpresas=new ArrayList<Empresa>();


    public void AbrirAlmacenamiento(View view){

        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent,10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK ){
            Uri path=data.getData();
            imgPlato.setImageURI(path);

        }
    }
    public Bitmap ObtenerImagen(){
        imgPlato.buildDrawingCache();
        Bitmap bitmap = imgPlato.getDrawingCache();
        return bitmap;
    }
    private   byte[] ImagenBlob(Bitmap bitmap){
        // tamaño del baos depende del tamaño de tus imagenes en promedio
        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        byte[] blob = baos.toByteArray();
        return blob;

    }
    private Bitmap convertirlogoBitMap(byte [] imgplato){
        Bitmap bmp = BitmapFactory.decodeByteArray(imgplato, 0, imgplato.length);
        return bmp;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);
        context=this;
        txttitulo=(TextView) findViewById(R.id.TITULOPLATO);
        etnombre=(EditText) findViewById(R.id.etNombrePlato);
        etprecio=(EditText) findViewById(R.id.etPrecio);
        descripcion=(EditText) findViewById(R.id.descripcionplato);
        imgPlato=(ImageView) findViewById(R.id.imgplato);
        spinCat=(Spinner) findViewById(R.id.spinCategorias);
        spinEmp=(Spinner) findViewById(R.id.spinEmpresaPlatos);
        if(VarGlobales.getUsuarioActual().getTipo().equals("Empresa")) {
        spinEmp.setVisibility(View.GONE);
        }
        btnNuevoPlato=(Button) findViewById(R.id.btnAgregarPlato);
        ListaCategorias=Categoria.ListarCategorias();
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(context, android.R.layout.simple_spinner_dropdown_item, ListaCategorias);
        spinCat.setAdapter(adapter);
        ListaEmpresas=Empresa.BuscarTodas();
        ArrayAdapter<Empresa> adapterempresa=new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,ListaEmpresas);
        spinEmp.setAdapter(adapterempresa);
        //obtiene parametros del intent (si tiene Carga los datos para editar(MODIFICACION))
        Bundle parametros =getIntent().getExtras();
        if(parametros!=null){
            modificar=1;
            etnombre.setText(parametros.getString("nombre"));
            etprecio.setText(Double.toString(parametros.getDouble("precio")));
            descripcion.setText(parametros.getString("descripcion"));
            spinCat.setSelection(adapter.getPosition(Categoria.BuscarCategoriaPorID(parametros.getInt("categoria")))-1);
            imgPlato.setImageBitmap(convertirlogoBitMap(parametros.getByteArray("imagen")));


            btnNuevoPlato.setText("MODIFICA LOS DATOS");
            txttitulo.setText("MODIFICAR PLATO");
        }
        spinCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoria=null;
                categoria= (Categoria) parent.getSelectedItem();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoria=null;
            }
        });
        spinEmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                empresaselect=(Empresa) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnNuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String precio=etprecio.getText().toString();
                if(VarGlobales.getUsuarioActual().getTipo().equals("Administrador")){
                    Plato plato=new Plato(etnombre.getText().toString(),Double.parseDouble(precio),descripcion.getText().toString(),ImagenBlob(ObtenerImagen()),categoria.getId_categoria(),empresaselect.getRut());
                    if(modificar==0){

                        if(Plato.IngresarPlato(plato)==1){
                            Toast.makeText(context,"Plato ingresado con exito",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context,"Error al ingresar plato",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(Plato.ModificarPlato(plato)==1){
                            Toast.makeText(context,"Plato ingresado con exito",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Error al ingresar plato",Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Plato plato=new Plato(etnombre.getText().toString(),Double.parseDouble(precio),descripcion.getText().toString(),ImagenBlob(ObtenerImagen()),categoria.getId_categoria(),VarGlobales.getEmpresaActual().getRut());
                    if(modificar==0){

                        if(Plato.IngresarPlato(plato)==1){
                            Toast.makeText(context,"Plato ingresado con exito",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context,"Error al ingresar plato",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(Plato.ModificarPlato(plato)==1){
                            Toast.makeText(context,"Plato ingresado con exito",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Error al ingresar plato",Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });


    }
}