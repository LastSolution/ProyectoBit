package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.AdaptadoresSpinners.EmpresaSpinner;
import com.LastSolutionTeam.tastit.POJO.Empresa;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<EmpresaSpinner> empresaList;
    Context context;
    String Rut;



    private ArrayList<Empresa>ListaEmpresas(){
    ArrayList<Empresa> empresas=Empresa.BuscarTodas();
    return empresas;
}
    private void CargarSpinnerEmpresas(ArrayList<Empresa> Lista){
    for (int i=0; i<Lista.size(); i++)
    {
        Empresa empresa;
        empresa = Lista.get(i);
        empresaList.add(new EmpresaSpinner(empresa.getRut(),empresa.getNombre()));
        ArrayAdapter<EmpresaSpinner> adapter = new ArrayAdapter<EmpresaSpinner>(context, android.R.layout.simple_spinner_dropdown_item, empresaList);
        spinner.setAdapter(adapter);
    }
}
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        spinner = (Spinner) findViewById(R.id.SpinEmpresas);
        context=this;
        CargarSpinnerEmpresas(ListaEmpresas());
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            EmpresaSpinner empresaSpinner = (EmpresaSpinner) parent.getSelectedItem();
            Rut=empresaSpinner.getRut();
            Toast.makeText(context, "rut empresa: "+empresaSpinner.getRut()+",  NOMBRE EMPRESA : "+empresaSpinner.getNombre(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });

    }
}