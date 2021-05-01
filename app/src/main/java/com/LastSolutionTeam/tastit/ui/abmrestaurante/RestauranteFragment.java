package com.LastSolutionTeam.tastit.ui.abmrestaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.AbmActivity;
import com.LastSolutionTeam.tastit.Adaptadores.EmpresaAdaptador;
import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class RestauranteFragment extends Fragment {

    private RecyclerView ListaEmpresas;
    private RestauranteViewModel restauranteViewModel;
    private ArrayList<Empresa> Empresas;
    private Activity activity;
    public EmpresaAdaptador Eadaptador;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_restaurantes, container, false);
        ListaEmpresas = root.findViewById(R.id.rvEmpresas);
        activity=getActivity();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListaEmpresas.setLayoutManager(linearLayoutManager);
        ListarEmpresas();
        inicializaAdaptador();

        FloatingActionButton fab = root.findViewById(R.id.NuevaEmpresa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), EmpresaActivity.class);
                startActivity(myIntent);
            }
        });

        return root;
    }
    public  void CargarYsetearAdaptador(){
        ListarEmpresas();
        inicializaAdaptador();
    }
    public void ListarEmpresas(){
    Empresas=Empresa.BuscarTodas();
    }

    private void inicializaAdaptador(){
    Eadaptador=new EmpresaAdaptador(Empresas,activity );
    ListaEmpresas.setAdapter(Eadaptador);
    Eadaptador.notifyDataSetChanged();
        

    }
}