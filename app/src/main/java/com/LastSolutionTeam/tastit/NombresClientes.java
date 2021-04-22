package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.LastSolutionTeam.tastit.POJO.Cliente;

public class NombresClientes extends AppCompatActivity {

    EditText Cliente1;
    EditText Cliente2;
    EditText Cliente3;
    EditText Cliente4;
    EditText Cliente5;
    EditText Cliente6;
    Button btnAceptar;

    int CantidadClientes;

    private void OcultarEditText(int Cantidad){

        switch (Cantidad){

        case 5:Cliente6.setVisibility(View.GONE);
            break;
        case 4:Cliente6.setVisibility(View.GONE);
            Cliente5.setVisibility(View.GONE);
            break;
        case 3:Cliente6.setVisibility(View.GONE);
            Cliente5.setVisibility(View.GONE);
            Cliente4.setVisibility(View.GONE);
            break;
        case 2:Cliente6.setVisibility(View.GONE);
            Cliente5.setVisibility(View.GONE);
            Cliente4.setVisibility(View.GONE);
            Cliente3.setVisibility(View.GONE);
            break;
        case 1:Cliente6.setVisibility(View.GONE);
            Cliente5.setVisibility(View.GONE);
            Cliente4.setVisibility(View.GONE);
            Cliente3.setVisibility(View.GONE);
            Cliente2.setVisibility(View.GONE);
            break;

    }

    }
    private void INGRESARCLIENTES(int Cantidad){
        String Nombre1="";
        String Nombre2="";
        String Nombre3="";
        String Nombre4="";
        String Nombre5="";
        String Nombre6="";

        switch (Cantidad){
            case 6:Nombre1= Cliente1.getText().toString();
                   Nombre2=Cliente1.getText().toString();
                   Nombre3=Cliente1.getText().toString();
                   Nombre4= Cliente1.getText().toString();
                   Nombre5=Cliente1.getText().toString();
                   Nombre6=Cliente1.getText().toString();
                   VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                  VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                 VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
                VarGlobales.cliente4= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre4,""));
                VarGlobales.cliente5= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre5,""));
                VarGlobales.cliente6= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre6,""));
                   break;
     case 5:Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente1.getText().toString();
                Nombre3=Cliente1.getText().toString();
                Nombre4= Cliente1.getText().toString();
                Nombre5=Cliente1.getText().toString();
         VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
         VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
         VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
         VarGlobales.cliente4= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre4,""));
         VarGlobales.cliente5= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre5,""));
                break;
            case 4:
                Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente1.getText().toString();
                Nombre3=Cliente1.getText().toString();
                Nombre4= Cliente1.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
                VarGlobales.cliente4= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre4,""));
                break;
            case 3:Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente1.getText().toString();
                Nombre3=Cliente1.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
                break;
            case 2:Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente1.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));


                break;
            case 1:
                Nombre1= Cliente1.getText().toString();
                Cliente.IngresarCliente(Nombre1,"");
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_clientes);
        Cliente1=(EditText) findViewById(R.id.user1);
        Cliente2=(EditText) findViewById(R.id.user2);
        Cliente3=(EditText) findViewById(R.id.user3);
        Cliente4=(EditText) findViewById(R.id.user4);
        Cliente5=(EditText) findViewById(R.id.user5);
        Cliente6=(EditText) findViewById(R.id.user6);
        btnAceptar=(Button) findViewById(R.id.btnaceptarClientes) ;

        Bundle parametros =getIntent().getExtras();
        CantidadClientes=parametros.getInt("CantClientes");
        OcultarEditText(CantidadClientes);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                INGRESARCLIENTES(CantidadClientes);
            }
        });


    }
}