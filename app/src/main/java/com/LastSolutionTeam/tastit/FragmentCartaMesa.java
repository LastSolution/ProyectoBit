package com.LastSolutionTeam.tastit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapter;
import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapterCarta;
import com.LastSolutionTeam.tastit.POJO.Categoria;
import com.LastSolutionTeam.tastit.POJO.PedidoPlato;
import com.LastSolutionTeam.tastit.POJO.Plato;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCartaMesa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCartaMesa extends Fragment {

    ExpandableListView expandableListView;
    ArrayList<String> listgroup1= new ArrayList<String>();
    HashMap<String,ArrayList<String>> ListChild1=new HashMap<>();
    MAinAdapterCarta mAinAdapterCarta;

    public static FragmentCartaMesa newInstance() {
        FragmentCartaMesa fragment = new FragmentCartaMesa();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_carta_mesa, container, false);
        expandableListView=(ExpandableListView) view.findViewById(R.id.Carta);

        CargarCarta();
        return view;



    }
    public void CargarCarta(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        ArrayList<String>Nombreyprecio=new ArrayList<String>();
        categorias=Categoria.ListarCategorias();
        for(int i=0; i<categorias.size();i++){
            Categoria c=categorias.get(i);
            listgroup1.add(c.getNombre_categoria());
            ArrayList<Plato> platos=new ArrayList<Plato>();
            platos=Plato.BuscarporCategoriayEmpresa(c.getId_categoria(),VarGlobales.getEmpresaActual().getRut());
            for(int ip=0; i<platos.size();ip++){
                Plato plato=platos.get(ip);
                String nomprecio;
                nomprecio=plato.getNombre_plato()+"  "+plato.getPrecio();
                Nombreyprecio.add(nomprecio);

            }
            if(Nombreyprecio.size()==0){
                Nombreyprecio.add("Aun No hay platos en esta categoria");
            }
            ListChild1.put(c.getNombre_categoria(),Nombreyprecio);

        }
        mAinAdapterCarta=new MAinAdapterCarta(listgroup1,ListChild1);
        expandableListView.setAdapter(mAinAdapterCarta);




    }
}