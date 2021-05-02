package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button btnregistro;
    Button btnconectar;
    EditText etNombre;
    EditText etPass;
    Usuario USUARIO;
    Context context;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        btnregistro=(Button) findViewById(R.id.btnregistro) ;
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EmpresaActivity.class);
                intent.putExtra("registro",1);
                startActivity(intent);
            }
        });
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPass = (EditText) findViewById(R.id.Pass);
        btnconectar = (Button) findViewById(R.id.btnconectar);
        btnconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = etNombre.getText().toString();

                String Pass = etPass.getText().toString();

                if (VerificarDatos(Nombre, Pass)==0)
                {

                    USUARIO=Usuario.Login(Nombre,Pass);

                    if (USUARIO != null )
                    {
                    VarGlobales.setUsuarioActual(USUARIO);
                    VarGlobales.setEmpresaActual(Empresa.BuscarPorRut(USUARIO.getRut_empresa()));
                    if(USUARIO.getTipo().equals("Empresa")){
                        Intent intent = new Intent(v.getContext(), abm_empresa.class);
                        startActivity(intent);
                    }if(USUARIO.getTipo().equals("Administrador")){
                        Intent intent = new Intent(v.getContext(), AbmActivity.class);
                        startActivity(intent);
                    }


                    }else
                    {

                    mostrarSnackbar(v,"NOMBRE O PASS INCORRECTO");
                    }
                }
                else
                    {
                        mostrarSnackbar(v,"CAMPOS VACIOS, VERIFIQUE");
                    }


            }
        });

    }

    protected int VerificarDatos(String NOMBRE, String PASS) {
        if (NOMBRE.equals("")||PASS.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }
    protected void LimpiarDatos()
    {
        etNombre.setText("");
        etPass.setText("");

    }
    @SuppressLint("ResourceAsColor")
    public void mostrarSnackbar(View view, String texto){

        Snackbar snackbar = Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#558b2f"));
        snackbar.show();
    }
}




