package com.LastSolutionTeam.tastit.ui.abmusuarios;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.LastSolutionTeam.tastit.Adaptadores.EmpresaAdaptador;
import com.LastSolutionTeam.tastit.Adaptadores.UsuarioAdaptador;
import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.R;
import com.LastSolutionTeam.tastit.UsuariosActivity;
import com.LastSolutionTeam.tastit.VarGlobales;
import com.LastSolutionTeam.tastit.ui.abmrestaurante.RestauranteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class UsuariosFragment extends Fragment {
    ArrayList<Empresa> ListaEmpresas;
    Spinner spinnerEmpresa;
    private RecyclerView ListaUsuarios;
    private ArrayList<Usuario> usuario;
    private Activity activity;
    Context context;
    String  RutEmpresa;
    TextView txtempresa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_abm_usuarios, container, false);
        ListaUsuarios = root.findViewById(R.id.rvUsuarios);
        context=getActivity();
        activity=getActivity();
        txtempresa=(TextView) root.findViewById(R.id.txtempresauser);
        spinnerEmpresa=(Spinner) root.findViewById(R.id.spinerempresausuario);
        if(VarGlobales.getUsuarioActual().getTipo().equals("Administrador")){

            ListaEmpresas = Empresa.BuscarTodas();
            ArrayAdapter<Empresa> adapter = new ArrayAdapter<Empresa>(context, android.R.layout.simple_spinner_dropdown_item, ListaEmpresas);
            spinnerEmpresa.setAdapter(adapter);
            spinnerEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Empresa empresa=(Empresa) parent.getSelectedItem();
                    RutEmpresa=empresa.getRut();
                    ListarUsuarios();
                    inicializaAdaptador();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            txtempresa.setVisibility(View.GONE);
            spinnerEmpresa.setVisibility(View.GONE);
            RutEmpresa=VarGlobales.getEmpresaActual().getRut();
            ListarUsuarios();
            inicializaAdaptador();
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListaUsuarios.setLayoutManager(linearLayoutManager);


        FloatingActionButton fab = root.findViewById(R.id.FabNuevoUsuario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UsuariosActivity.class);
                startActivity(myIntent);
            }
        });

        return root ;
}
    public void ListarUsuarios(){
        usuario=Usuario.BuscarUsuariosEmpresa(RutEmpresa);
    }
    public UsuarioAdaptador Uadaptador;
    private void inicializaAdaptador(){
        Uadaptador=new UsuarioAdaptador(usuario,activity );
        ListaUsuarios.setAdapter(Uadaptador);
        Uadaptador.notifyDataSetChanged();}
}