package com.LastSolutionTeam.tastit.ui.abmusuarios;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.LastSolutionTeam.tastit.Adaptadores.EmpresaAdaptador;
import com.LastSolutionTeam.tastit.Adaptadores.UsuarioAdaptador;
import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.R;
import com.LastSolutionTeam.tastit.UsuariosActivity;
import com.LastSolutionTeam.tastit.ui.abmrestaurante.RestauranteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class UsuariosFragment extends Fragment {


    private RecyclerView ListaUsuarios;
    private ArrayList<Usuario> usuario;
    private Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_abm_usuarios, container, false);
        ListaUsuarios = root.findViewById(R.id.rvUsuarios);
        activity=getActivity();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListaUsuarios.setLayoutManager(linearLayoutManager);
        ListarUsuarios();
        inicializaAdaptador();

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
        usuario=Usuario.BuscarTodos();
    }
    public UsuarioAdaptador Uadaptador;
    private void inicializaAdaptador(){
        Uadaptador=new UsuarioAdaptador(usuario,activity );
        ListaUsuarios.setAdapter(Uadaptador);
        Uadaptador.notifyDataSetChanged();}
}