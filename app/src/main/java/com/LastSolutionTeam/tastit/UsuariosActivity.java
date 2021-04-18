package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.AdaptadoresSpinners.EmpresaSpinner;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<EmpresaSpinner> empresaList;
    Context context;
    String Rut="";
    EditText nombreUsuario;
    EditText tipoUsuario;
    EditText passUsuario;
    Button btnAgregar;

    private ArrayList<Empresa>ListaEmpresas(){
    ArrayList<Empresa> empresas=Empresa.BuscarTodas();
    return empresas;
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
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        spinner = (Spinner) findViewById(R.id.SpinEmpresas);
        context=this;
        nombreUsuario= (EditText) findViewById(R.id.EtUsername);
        tipoUsuario=(EditText) findViewById(R.id.EttipoUsuario);
        passUsuario=(EditText)findViewById(R.id.etPassUsuario);
        btnAgregar=(Button) findViewById(R.id.btnAgregarusuario);

       btnAgregar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Usuario user=new Usuario(
                     nombreUsuario.getText().toString(),
                     passUsuario.getText().toString(),
                     tipoUsuario.getText().toString(),
                       Rut
               );
               if(Usuario.IngresarUsuario(user) !=0){
                   Toast.makeText(context,"USUARIO INGRESADO CON EXITO",Toast.LENGTH_LONG).show();

                   Intent intent = new Intent(v.getContext(),AbmActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(context,"ERROR AL INGRESAR USUARIO",Toast.LENGTH_LONG).show();
               }
           }
       });
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

    }
}