package com.LastSolutionTeam.tastit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity_Mesa extends AppCompatActivity {

    ExpandableListView User1;
    ExpandableListView User2;
    ExpandableListView User3;
    ExpandableListView User4;
    FloatingActionButton FabCarta;
    FloatingActionButton CerrarMesa;

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


    }
}