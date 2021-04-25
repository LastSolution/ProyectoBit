package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.LastSolutionTeam.tastit.AbmActivity;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;


public class MainActivity extends AppCompatActivity {


    Button btnconectar;
    EditText etNombre;
    EditText etPass;
    Usuario USUARIO;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        etNombre = (EditText) findViewById(R.id.etNombre);

        etPass = (EditText) findViewById(R.id.Pass);
        btnconectar = (Button) findViewById(R.id.btnconectar);

        btnconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = etNombre.getText().toString();

                String Pass = etPass.getText().toString();

                if (VerificarDatos(Nombre, Pass))
                {

                    USUARIO=Usuario.Login(Nombre,Pass,context);

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
                        Toast.makeText(context, "NOMBRE O PASS INCORRECTO", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                    Toast.makeText(context, "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                    }


            }
        });

    }

    protected Boolean VerificarDatos(String NOMBRE, String PASS) {
        if (NOMBRE.equals("")||PASS.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    protected void LimpiarDatos()
    {
        etNombre.setText("");
        etPass.setText("");

    }
}




