package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MesaBienvenida extends AppCompatActivity {

    TextView txtTitulo;
    Spinner SpinComensales;
    Button btnAceptar;
    String NombreEmpresa=VarGlobales.empresaActual.getNombre();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_bienvenida);
        txtTitulo=(TextView) findViewById(R.id.txrTituloBienvenida);
        SpinComensales=(Spinner) findViewById(R.id.SpinComensales);
        btnAceptar=(Button) findViewById(R.id.btnAceptar);
        String[] datos = new String[] {"1", "2", "3", "4", "5","6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        txtTitulo.setText("BIENVENIDO A "+NombreEmpresa);
        SpinComensales.setAdapter(adapter);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}