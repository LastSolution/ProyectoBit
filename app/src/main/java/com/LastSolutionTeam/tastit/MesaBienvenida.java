package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.LastSolutionTeam.tastit.POJO.Categoria;

public class MesaBienvenida extends AppCompatActivity {
    Context context;
    TextView txtTitulo;
    Spinner SpinComensales;
    Button btnAceptar;
    ImageView imglogo;
    int cantidadClientes=0;

    String NombreEmpresa="";

    private Bitmap convertirlogoBitMap(byte [] imgplato){
        Bitmap bmp = BitmapFactory.decodeByteArray(imgplato, 0, imgplato.length);
        return bmp;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_bienvenida);
        NombreEmpresa=VarGlobales.getEmpresaActual().getNombre();
        context=this;
        imglogo=(ImageView) findViewById(R.id.logoempresabienvenida);
        imglogo.setImageBitmap(convertirlogoBitMap(VarGlobales.getEmpresaActual().getLogo()));
        txtTitulo=(TextView) findViewById(R.id.txrTituloBienvenida);
        SpinComensales=(Spinner) findViewById(R.id.SpinComensales);
        btnAceptar=(Button) findViewById(R.id.btnAceptar);
        txtTitulo.setText("Bienvenido a "+NombreEmpresa);
        String[] datos = new String[] {"1", "2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);

        SpinComensales.setAdapter(adapter);
        SpinComensales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             String cant= (String) parent.getSelectedItem();
             cantidadClientes= Integer.valueOf(cant);;
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), NombresClientes.class);
                myIntent.putExtra("cantidad",cantidadClientes);
                startActivity(myIntent);

            }
        });

    }
}