package com.LastSolutionTeam.tastit.ui.abmcarta;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.MesaBienvenida;
import com.LastSolutionTeam.tastit.R;

public class CartaFragment extends Fragment {
    Button btnmodomesa;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_carta, container, false);
btnmodomesa=(Button) root.findViewById(R.id.btnmodomesa);
btnmodomesa.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent myIntent = new Intent(v.getContext(), MesaBienvenida.class);
        startActivity(myIntent);
    }
});


        return root;
    }



}