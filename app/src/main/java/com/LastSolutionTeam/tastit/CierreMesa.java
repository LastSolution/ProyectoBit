package com.LastSolutionTeam.tastit;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.LastSolutionTeam.tastit.Adaptadores.PlatoAdaptador;
import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Pedido;
import com.LastSolutionTeam.tastit.POJO.PedidoPlato;
import com.LastSolutionTeam.tastit.POJO.Plato;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CierreMesa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CierreMesa extends Fragment {

    Pedido Pedido1;
    Pedido Pedido2;
    Pedido Pedido3;
    Pedido Pedido4;
    ArrayList <Plato> PlatosTotales=new ArrayList<Plato>();
    RecyclerView rvCierre;
    Activity activity;
    TextView txttotal;
    double preciotal=0;
    public CierreMesa() {

    }
    public void CargarPedidosClientes(Cliente cliente,Pedido pedido){
        ArrayList<PedidoPlato>PlatosPedidos=new ArrayList<PedidoPlato>();
        ArrayList<Plato>Platos=new ArrayList<Plato>();
        if(cliente!=null){
            pedido=Pedido.BuscarPedidoporcliente(cliente.getId_cliente());
            preciotal=preciotal+pedido.getPrecio_total();
            PlatosPedidos= PedidoPlato.ListarPedidos(pedido.getId_pedido());
            for (int i=0;i<PlatosPedidos.size();i++
                 ) {
                PedidoPlato pp=PlatosPedidos.get(i);
                Plato plato=Plato.BuscarPlato(pp.getIdplato());
                if(pp.getCantidad()!=1){
                    int c=1;
                    while (c<=pp.getCantidad()){
                        Platos.add(plato);
                        c++;
                    }

                }else{
                    Platos.add(plato);
                }


            }
            for (int i=0;i<Platos.size();i++
            ) {
                Plato plato=Platos.get(i);
                PlatosTotales.add(plato);
            }
        }
    }

    public static CierreMesa newInstance() {
        CierreMesa fragment = new CierreMesa();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_cierre_mesa, container, false);
        // Inflate the layout for this fragment
        rvCierre=(RecyclerView) root.findViewById(R.id.rvCierreMesa);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCierre.setLayoutManager(linearLayoutManager);
        activity=getActivity();
        txttotal=(TextView) root.findViewById(R.id.txttotalpedido);
        CargarPedidosClientes(VarGlobales.getCliente1(),Pedido1);
        CargarPedidosClientes(VarGlobales.getCliente2(),Pedido2);
        CargarPedidosClientes(VarGlobales.getCliente3(),Pedido3);
        CargarPedidosClientes(VarGlobales.getCliente4(),Pedido4);

        inicializaAdaptador();
        txttotal.setText("TOTAL  $ "+preciotal);
        return root;

    }

    public PlatoAdaptador platoAdaptador;
    private void inicializaAdaptador(){
        platoAdaptador=new PlatoAdaptador(PlatosTotales,activity,1);
        rvCierre.setAdapter(platoAdaptador);
        platoAdaptador.notifyDataSetChanged();
    }
}