package com.LastSolutionTeam.tastit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapterCarta;
import com.LastSolutionTeam.tastit.POJO.Categoria;
import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Empresa;
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
    ArrayList<Categoria> listgroup1= new ArrayList<Categoria>();
    ArrayList<Cliente> ListaClientes=new ArrayList<Cliente>();
    HashMap<Categoria,ArrayList<Plato>> ListChild1=new HashMap<>();
    MAinAdapterCarta mAinAdapterCarta;
    Spinner spinerClientesmesa;
    Cliente clientepedido;
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
        spinerClientesmesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                VarGlobales.setClientepedidoActual((Cliente) parent.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            ArrayList<Plato>platos=new ArrayList<Plato>();
            Categoria c=categorias.get(i);
            platos = Plato.BuscarporCategoriayEmpresa(c.getId_categoria(), rut);
            if (platos.size() != 0) {
            listgroup1.add(c);
            }
        }
        ArrayList<Plato> platos=new ArrayList<Plato>();
        for(int i=0; i<categorias.size();i++) {
            Categoria c = categorias.get(i);    ArrayList<String> Nombreyprecio = new ArrayList<String>();
            platos = Plato.BuscarporCategoriayEmpresa(c.getId_categoria(), rut);
            if (platos.size() != 0) {

                ListChild1.put(c, platos);
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