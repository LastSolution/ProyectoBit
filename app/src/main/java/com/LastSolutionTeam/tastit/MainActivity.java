package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.LastSolutionTeam.tastit.POJO.Restaurante;





public class MainActivity extends AppCompatActivity {


    Button btnconectar;
    EditText etNombre;
    EditText etRut;
    EditText etTipo;
    Restaurante RESTAURANTE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNombre = (EditText) findViewById(R.id.etNombre);
        etRut = (EditText) findViewById(R.id.etRut);
        etTipo = (EditText) findViewById(R.id.EtTipo);
        btnconectar = (Button) findViewById(R.id.btnconectar);

        btnconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = etNombre.getText().toString().toUpperCase();
                String Rut = etRut.getText().toString();
                String Tipo = etTipo.getText().toString().toUpperCase();

                if (VerificarDatos(Nombre, Rut, Tipo))
                {
                    RESTAURANTE = new Restaurante(Nombre, Rut, Tipo);
                    int RESULTADO=Restaurante.IngresarRestaurante(RESTAURANTE);

                    if (RESULTADO == 1)
                    {
                        Toast.makeText(getApplicationContext(), "Ingresado Con Exito", Toast.LENGTH_SHORT).show();
                        LimpiarDatos();

                    }else if(RESULTADO == 2)
                    {
                        Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_SHORT).show();
                    }else if(RESULTADO == 3)
                    {
                        Toast.makeText(getApplicationContext(), "Revise su Conexion a Internet", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                    }


            }
        });

    }

    protected Boolean VerificarDatos(String NOMBRE, String TIPO, String RUT) {
        if (NOMBRE.equals("")||TIPO.equals("")||RUT.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    protected void LimpiarDatos()
    {
        etNombre.setText("");
        etTipo.setText("");
        etRut.setText("");
    }
}




