package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

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
    FrameLayout fragmentContainer;
    Fragment fragmentcarta;
    FragmentTransaction transaction;
    FloatingActionButton FabCarta;
    FloatingActionButton CerrarMesa;
    Context context;
    int cantClientes=0;
    MAinAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__mesa);
        getSupportActionBar().hide();
        context=this;
        User1=(ExpandableListView) findViewById(R.id.user1);
        User2=(ExpandableListView) findViewById(R.id.user2);
        User3=(ExpandableListView) findViewById(R.id.user3);
        User4=(ExpandableListView) findViewById(R.id.user4);
        fragmentContainer=(FrameLayout) findViewById(R.id.contenedorfragment);




        FabCarta=(FloatingActionButton) findViewById(R.id.FabCarta);
        FabCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentcarta=new FragmentCartaMesa();
                transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorfragment,fragmentcarta);
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorfragment,fragmentcarta);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        CerrarMesa=(FloatingActionButton) findViewById(R.id.FabCerrarMesa);
        CerrarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Bundle parametros =getIntent().getExtras();
        cantClientes=parametros.getInt("cantidad");
        InicializarListGrupo(cantClientes);


    }
    public void InicializarListGrupo(int Cantcli){
        switch (Cantcli){
            case 4:
                CargarlistaCliente(VarGlobales.getCliente1(),listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.getCliente2(),listgroup2,ListChild2,User2);
                CargarlistaCliente(VarGlobales.getCliente3(),listgroup3,ListChild3,User3);
                CargarlistaCliente(VarGlobales.getCliente4(),listgroup4,ListChild4,User4);
                break;
            case 3:
                CargarlistaCliente(VarGlobales.getCliente1(),listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.getCliente2(),listgroup2,ListChild2,User2);
                CargarlistaCliente(VarGlobales.getCliente3(),listgroup3,ListChild3,User3);
                break;
            case 2:
                CargarlistaCliente(VarGlobales.getCliente1(),listgroup1,ListChild1,User1);
                CargarlistaCliente(VarGlobales.getCliente2(),listgroup2,ListChild2,User2);
                break;
            case 1:
                CargarlistaCliente(VarGlobales.getCliente1(),listgroup1,ListChild1,User1);
                break;
        }

    }

    public void CargarlistaCliente(Cliente cliente,ArrayList<String> listgroup,HashMap<String,ArrayList<String>> listchild,ExpandableListView expandableListView){
        listgroup.clear();
        listchild.clear();
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
        if(Nombreyprecio.size()==0){
            Nombreyprecio.add("No pediste ningun plato");
            listchild.put(cliente.getNombre_cliente(),Nombreyprecio);
        }
        else{
            listchild.put(cliente.getNombre_cliente(),Nombreyprecio);
        }

        adapter=new MAinAdapter(listgroup,listchild);
        expandableListView.setAdapter(adapter);

    }
    public void VerCarta(){
    FragmentCartaMesa fragmentcarta=FragmentCartaMesa.newInstance();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_open_exit,R.anim.fragment_open_enter,R.anim.fragment_open_exit);
        transaction.add(R.id.contenedorfragment,fragmentcarta,"BLANK_FRAGMENT");

        transaction.commit();

    }
}