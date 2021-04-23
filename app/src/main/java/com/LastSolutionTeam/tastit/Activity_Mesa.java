package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.LastSolutionTeam.tastit.Adaptadores.MAinAdapter;
import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Pedido;
import com.LastSolutionTeam.tastit.POJO.PedidoPlato;
import com.LastSolutionTeam.tastit.POJO.Plato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_Mesa extends AppCompatActivity {

    ExpandableListView User1;
    ExpandableListView User2;
    ExpandableListView User3;
    ExpandableListView User4;
    ArrayList<String> listgroup1= new ArrayList<String>();
    HashMap<String,ArrayList<String>> ListChild1=new HashMap<>();
    ArrayList<String> listgroup2= new ArrayList<String>();
    HashMap<String,ArrayList<String>> ListChild2=new HashMap<>();
    ArrayList<String> listgroup3= new ArrayList<String>();
    HashMap<String,ArrayList<String>> ListChild3=new HashMap<>();
    ArrayList<String> listgroup4= new ArrayList<String>();
    HashMap<String,ArrayList<String>> ListChild4=new HashMap<>();
    MAinAdapter adapter;

    FloatingActionButton FabCarta;
    FloatingActionButton CerrarMesa;
    int cantClientes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__mesa);
        User1=(ExpandableListView) findViewById(R.id.user1);
        User2=(ExpandableListView) findViewById(R.id.user2);
        User3=(ExpandableListView) findViewById(R.id.user3);
        User4=(ExpandableListView) findViewById(R.id.user4);



        FabCarta=(FloatingActionButton) findViewById(R.id.FabCarta);
        CerrarMesa=(FloatingActionButton) findViewById(R.id.FabCerrarMesa);
        Bundle parametros =getIntent().getExtras();
        cantClientes=parametros.getInt("cantidad");
        InicializarListGrupo(cantClientes);


    }
    public void InicializarListGrupo(int Cantcli){
        switch (Cantcli){
            case 4:
                CargarlistaCliente(VarGlobales.cliente1,listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.cliente2,listgroup2,ListChild2,User2);
                CargarlistaCliente(VarGlobales.cliente3,listgroup3,ListChild3,User3);
                CargarlistaCliente(VarGlobales.cliente4,listgroup4,ListChild4,User4);
            case 3:
                CargarlistaCliente(VarGlobales.cliente1,listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.cliente2,listgroup2,ListChild2,User2);
                CargarlistaCliente(VarGlobales.cliente3,listgroup3,ListChild3,User3);
            case 2:
                CargarlistaCliente(VarGlobales.cliente1,listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.cliente2,listgroup2,ListChild2,User2);
            case 1:
                CargarlistaCliente(VarGlobales.cliente1,listgroup1,ListChild1,User1);
        }

    }
    //VarGlobales.cliente1
    public void CargarlistaCliente(Cliente cliente,ArrayList<String> listgroup,HashMap<String,ArrayList<String>> listchild,ExpandableListView expandableListView){
        listgroup.add(cliente.getNombre_cliente());
        Pedido pedido=Pedido.BuscarPedidoporcliente(cliente.getId_cliente());

        ArrayList<PedidoPlato> platoscliente=PedidoPlato.ListarPedidos(pedido.getId_pedido());
        ArrayList<String>Nombreyprecio=new ArrayList<String>();

        for(int i=0; i<platoscliente.size();i++){
            PedidoPlato p=platoscliente.get(i);
            Plato plato=Plato.BuscarPlato(p.getIdplato());
            String nomprecio;
            nomprecio=plato.getNombre_plato()+"  "+plato.getPrecio();
            Nombreyprecio.add(nomprecio);
        }
        listchild.put(cliente.getNombre_cliente(),Nombreyprecio);
        adapter=new MAinAdapter(listgroup,listchild);
        expandableListView.setAdapter(adapter);



    }
}