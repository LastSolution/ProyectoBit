package com.LastSolutionTeam.tastit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LastSolutionTeam.tastit.POJO.Pedido;

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

    public CierreMesa() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cierre_mesa, container, false);
    }
}