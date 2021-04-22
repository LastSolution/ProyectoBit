package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_Mesa extends AppCompatActivity {

    ExpandableListView User1;
    ExpandableListView User2;
    ExpandableListView User3;
    ExpandableListView User4;
    List<String> listgroup;
    HashMap<String,List<String>> ListItems;

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
        listgroup=new ArrayList<>();
        ListItems=new HashMap<>();

        FabCarta=(FloatingActionButton) findViewById(R.id.FabCarta);
        CerrarMesa=(FloatingActionButton) findViewById(R.id.FabCerrarMesa);
        Bundle parametros =getIntent().getExtras();
        cantClientes=parametros.getInt("cantidad");

    }
    public void InicializarListGrupo(){
        listgroup.add(VarGlobales.cliente1.getNombre_cliente());

    }
}