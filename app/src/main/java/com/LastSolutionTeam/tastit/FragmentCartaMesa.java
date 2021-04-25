package com.LastSolutionTeam.tastit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapter;
import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapterCarta;
import com.LastSolutionTeam.tastit.POJO.Categoria;
import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.PedidoPlato;
import com.LastSolutionTeam.tastit.POJO.Plato;

import java.lang.reflect.Array;
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
    ArrayList<Cliente> ListaClientes=new ArrayList<Cliente>();
    HashMap<String,ArrayList<String>> ListChild1=new HashMap<>();
    MAinAdapterCarta mAinAdapterCarta;
    Spinner spinerClientesmesa;
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
        Empresa empresaactual=null;
        spinerClientesmesa=(Spinner) view.findViewById(R.id.spinclientes);
        CargarClienteASpinner(VarGlobales.getCliente1());
        CargarClienteASpinner(VarGlobales.getCliente2());
        CargarClienteASpinner(VarGlobales.getCliente3());
        CargarClienteASpinner(VarGlobales.getCliente4());
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ListaClientes);
        spinerClientesmesa.setAdapter(adapter);
        empresaactual=VarGlobales.getEmpresaActual();
        CargarCarta();
        return view;



    }
    public void CargarCarta(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        Empresa empresaactual=null;
        empresaactual=VarGlobales.getEmpresaActual();
        String rut=empresaactual.getRut();
        categorias=Categoria.ListarCategorias();
        for(int i=0; i<categorias.size();i++){
            Categoria c=categorias.get(i);
            listgroup1.add(c.getNombre_categoria());
        }
        ArrayList<Plato> platos=new ArrayList<Plato>();
        for(int i=0; i<categorias.size();i++){
            Categoria c=categorias.get(i);
            ArrayList<String>Nombreyprecio=new ArrayList<String>();
            platos=Plato.BuscarporCategoriayEmpresa(c.getId_categoria(),rut);
            if(platos.size()!=0){

                for(int ip=0; ip<platos.size();ip++){
                    Plato plato=platos.get(ip);
                    String nomprecio;
                    if(plato!=null){
                        nomprecio=plato.getNombre_plato()+"  "+plato.getPrecio();
                        Nombreyprecio.add(nomprecio);

                    }
                    ListChild1.put(c.getNombre_categoria(),Nombreyprecio);
                }

                }else{ Nombreyprecio.add("Aun No hay platos en esta categoria");
                ListChild1.put(c.getNombre_categoria(),Nombreyprecio);


        }
        }
        mAinAdapterCarta=new MAinAdapterCarta(listgroup1,ListChild1,getActivity());
        expandableListView.setAdapter(mAinAdapterCarta);




    }
    public void CargarClienteASpinner(Cliente cliente){
        if(cliente!=null)
        {
            ListaClientes.add(cliente);
        }
    }
}