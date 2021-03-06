package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.AdaptadoresSpinners.EmpresaSpinner;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<EmpresaSpinner> empresaList;
    Context context;
    String Rut="";
    String TipoUsuarioseleccionado;
    EditText nombreUsuario;
    TextView txtempresa;
    EditText passUsuario;
    Button btnAgregar;
    Spinner TipoUsuario;
    TextView textspinuser;
    String Empresaregistro="";
    EditText passusuario2;
    int registro=0;
    private ArrayList<Empresa>ListaEmpresas(){
    ArrayList<Empresa> empresas=Empresa.BuscarTodas();
    return empresas;
}
    public void mostrarSnackbar(View view, String texto){

        Snackbar snackbar = Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#558b2f"));
        snackbar.show();
    }
    private void CargarSpinnerEmpresas(ArrayList<Empresa> Lista){
        empresaList=new ArrayList<EmpresaSpinner>();
    for (int i=0; i<Lista.size(); i++)
    {
        Empresa empresa;
        empresa = Lista.get(i);
        empresaList.add(new EmpresaSpinner(empresa.getRut(),empresa.getNombre()));
        ArrayAdapter<EmpresaSpinner> adapter = new ArrayAdapter<EmpresaSpinner>(context, android.R.layout.simple_spinner_dropdown_item, empresaList);
        spinner.setAdapter(adapter);
    }
}
    private String EmpresaUsuario(String rut){
        String NombreEmpresa=Empresa.BuscarPorRut(Rut).getNombre();
        return NombreEmpresa;
}
    private void CargarSpinnerTipoUser(){
        ArrayList<String> Lista=new ArrayList<String>();
        Lista.add("Empresa");
        Lista.add("Administrador");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Lista);
        TipoUsuario.setAdapter(adapter);

    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Bundle parametros =getIntent().getExtras();
        if(parametros!=null ){
        registro=parametros.getInt("registro");
        Empresaregistro=parametros.getString("empresa");
         }
        context=this;
        passusuario2=(EditText) findViewById(R.id.etPassUsuario2);
        spinner = (Spinner) findViewById(R.id.SpinEmpresas);
        textspinuser=(TextView) findViewById(R.id.textspinuser);
        nombreUsuario= (EditText) findViewById(R.id.EtUsername);
        TipoUsuario=(Spinner) findViewById(R.id.spintipoUsuario);
        passUsuario=(EditText)findViewById(R.id.etPassUsuario);
        btnAgregar=(Button) findViewById(R.id.btnAgregarusuario);
        txtempresa=(TextView) findViewById(R.id.textempresa);
        if(registro==0){
            if(VarGlobales.getUsuarioActual().getTipo().equals("Empresa")){
                TipoUsuario.setVisibility(View.GONE);
                textspinuser.setVisibility(View.GONE);
                txtempresa.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);


            }   else {
                CargarSpinnerEmpresas(ListaEmpresas());
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        EmpresaSpinner empresaSpinner = (EmpresaSpinner) parent.getSelectedItem();
                        Rut=empresaSpinner.getRut();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Rut=null;
                    }
                });
                CargarSpinnerTipoUser();
                TipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TipoUsuarioseleccionado=(String) parent.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }else{
            TipoUsuario.setVisibility(View.GONE);
            textspinuser.setVisibility(View.GONE);
            txtempresa.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        }



           btnAgregar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Usuario user=null;

               if(passUsuario.getText().toString().equals (passusuario2.getText().toString())){

                   if(registro==0){
                       if(VarGlobales.getUsuarioActual().getTipo().equals("Empresa")){
                           Rut=VarGlobales.getEmpresaActual().getRut();
                           user=new Usuario(
                                   nombreUsuario.getText().toString(),
                                   passUsuario.getText().toString(),
                                   "Empresa",
                                   Rut
                           );
                       }else{
                           user=new Usuario(
                                   nombreUsuario.getText().toString(),
                                   passUsuario.getText().toString(),
                                   TipoUsuarioseleccionado,
                                   Rut
                           );
                       }
                       if(Usuario.IngresarUsuario(user) !=0){
                           if(VarGlobales.getUsuarioActual().getTipo().equals("Empresa")){
                               mostrarSnackbar(v,"USUARIO INGRESADO CON EXITO");
                               Intent intent = new Intent(v.getContext(),abm_empresa.class);
                               startActivity(intent);
                           }else{
                               mostrarSnackbar(v,"USUARIO INGRESADO CON EXITO");
                               Intent intent = new Intent(v.getContext(),AbmActivity.class);
                               startActivity(intent);
                           }

                       }else{
                           mostrarSnackbar(v,"ERROR AL INGRESAR USUARIO");
                       }



                   }else{
                       user=new Usuario(
                               nombreUsuario.getText().toString(),
                               passUsuario.getText().toString(),
                               "Empresa",
                               Empresaregistro
                       );
                       if(Usuario.IngresarUsuario(user) !=0){
                           mostrarSnackbar(v,"USUARIO REGISTRADO CON EXITO");
                           Intent intent = new Intent(v.getContext(),MainActivity.class);
                           startActivity(intent);
                       }else{
                           mostrarSnackbar(v,"ERROR AL REGISTRAR USUARIO");
                       }
                   }


               }else{
                   mostrarSnackbar(v,"LOS PASSWORDS NO COINCIDEN");
               }
               }


       });


    }
}