package com.LastSolutionTeam.tastit.ui.abmplatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.Adaptadores.PlatoAdaptador;
import com.LastSolutionTeam.tastit.Adaptadores.UsuarioAdaptador;
import com.LastSolutionTeam.tastit.AdaptadoresSpinners.EmpresaSpinner;
import com.LastSolutionTeam.tastit.POJO.Categoria;
import com.LastSolutionTeam.tastit.POJO.Plato;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.PlatosActivity;
import com.LastSolutionTeam.tastit.R;
import com.LastSolutionTeam.tastit.UsuariosActivity;
import com.LastSolutionTeam.tastit.VarGlobales;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.ContentHandler;
import java.util.ArrayList;

public class PlatosFragment extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<Plato> platos;
    private Activity activity;
    private Spinner spinEmpresa;
    private Spinner spinCategoria;
    private TextView txtspinEmpresa;
    private Spinner spinCat;
    private ArrayList<Categoria> ListaCategorias=new ArrayList<Categoria>();
    private Context context;
    private String RutEmpresa=VarGlobales.getEmpresaActual().getRut();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_platos, container, false);
        recyclerView = root.findViewById(R.id.rvPlatos);
        activity=getActivity();
        context=getContext();
        spinEmpresa=(Spinner) root.findViewById(R.id.spinEmpresaPlatos);

        spinCategoria=(Spinner) root.findViewById(R.id.spincategoriaplato);
        txtspinEmpresa=(TextView) root.findViewById(R.id.txtspinEmpresa);
        if(VarGlobales.getUsuarioActual().getTipo().equals("Empresa")){
            spinEmpresa.setVisibility(View.GONE);
            txtspinEmpresa.setVisibility(View.GONE);
        }
        ListarPlatos(1,RutEmpresa);
        inicializaAdaptador();
        ListaCategorias=Categoria.ListarCategorias();
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(context, android.R.layout.simple_spinner_dropdown_item, ListaCategorias);
        spinCategoria.setAdapter(adapter);
        spinCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Categoria categoria= (Categoria) parent.getSelectedItem();

               ListarPlatos(categoria.getId_categoria(),RutEmpresa);
               inicializaAdaptador();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = root.findViewById(R.id.fabNuevoplato);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PlatosActivity.class);
                startActivity(myIntent);
            }
        });
        return root;
    }



    private void ListarPlatos(int Categoria,String Empresa){
        platos=Plato.BuscarporCategoriayEmpresa(Categoria,Empresa);
    }
    public PlatoAdaptador platoAdaptador;
    private void inicializaAdaptador(){
        platoAdaptador=new PlatoAdaptador(platos,activity);
        recyclerView.setAdapter(platoAdaptador);
        platoAdaptador.notifyDataSetChanged();
      }
}