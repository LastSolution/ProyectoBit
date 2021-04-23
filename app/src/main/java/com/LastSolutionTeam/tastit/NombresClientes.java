package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Pedido;

public class NombresClientes extends AppCompatActivity {

    EditText Cliente1;
    EditText Cliente2;
    EditText Cliente3;
    EditText Cliente4;

    Button btnAceptar;
    int ClienteVacio=0;
    Context context;
    int CantidadClientes;

    private void CrearpedidosClientes(Cliente cliente){
        Pedido pedido=new Pedido(1,0,cliente.getId_cliente());
        Pedido.IngresarPedido(pedido);
    }
    private void OcultarEditText(int Cantidad){

        switch (Cantidad){

        case 3:
            Cliente4.setVisibility(View.GONE);
            break;
        case 2:
            Cliente4.setVisibility(View.GONE);
            Cliente3.setVisibility(View.GONE);
            break;
        case 1:
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


        switch (Cantidad){

            case 4:
                Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente2.getText().toString();
                Nombre3=Cliente3.getText().toString();
                Nombre4= Cliente4.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
                VarGlobales.cliente4= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre4,""));
                CrearpedidosClientes(VarGlobales.cliente1);
                CrearpedidosClientes(VarGlobales.cliente2);
                CrearpedidosClientes(VarGlobales.cliente3);
                CrearpedidosClientes(VarGlobales.cliente4);
                break;
            case 3:Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente2.getText().toString();
                Nombre3=Cliente3.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                VarGlobales.cliente3= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre3,""));
                CrearpedidosClientes(VarGlobales.cliente1);
                CrearpedidosClientes(VarGlobales.cliente2);
                CrearpedidosClientes(VarGlobales.cliente3);
                break;
            case 2:Nombre1= Cliente1.getText().toString();
                Nombre2=Cliente2.getText().toString();
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                VarGlobales.cliente2= Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre2,""));
                CrearpedidosClientes(VarGlobales.cliente1);
                CrearpedidosClientes(VarGlobales.cliente2);
                break;
            case 1:
                Nombre1= Cliente1.getText().toString();
                Cliente.IngresarCliente(Nombre1,"");
                VarGlobales.cliente1=Cliente.BuscarCliente(Cliente.IngresarCliente(Nombre1,""));
                CrearpedidosClientes(VarGlobales.cliente1);
                break;

        }

    }
    private void ValidarEditVacio(EditText editText){
        if(editText.getVisibility() == View.VISIBLE) {
            if(editText.getText().toString()==""){
                ClienteVacio=1;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_clientes);
        context=this.context;
        Cliente1=(EditText) findViewById(R.id.user1);
        Cliente2=(EditText) findViewById(R.id.user2);
        Cliente3=(EditText) findViewById(R.id.user3);
        Cliente4=(EditText) findViewById(R.id.user4);

        btnAceptar=(Button) findViewById(R.id.btnaceptarClientes) ;

        Bundle parametros =getIntent().getExtras();
        CantidadClientes=parametros.getInt("cantidad");
        OcultarEditText(CantidadClientes);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarEditVacio(Cliente1);
                ValidarEditVacio(Cliente2);
                ValidarEditVacio(Cliente3);
                ValidarEditVacio(Cliente4);

                if(ClienteVacio==0){
                    INGRESARCLIENTES(CantidadClientes);
                    Intent myIntent = new Intent(v.getContext(), Activity_Mesa.class);
                    myIntent.putExtra("cantidad",CantidadClientes);
                    startActivity(myIntent);
                }else{
                    Toast.makeText(context,"NO PUEDE INGRESAR UN CLIENTE SIN NOMBRE",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}