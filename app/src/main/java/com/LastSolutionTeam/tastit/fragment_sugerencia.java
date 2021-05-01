package com.LastSolutionTeam.tastit;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.POJO.Sugerencia;


public class fragment_sugerencia extends Fragment {

    EditText etSugerencia;
    Button btningresarsug;
    Button Volver;
    FrameLayout fragmentContainer;
    Context context;
    Fragment fragment=this;
    public fragment_sugerencia() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_sugerencia newInstance(String param1, String param2) {
        fragment_sugerencia fragment = new fragment_sugerencia();
        return fragment;
    }
    public void CerrarFragment(View v){

        fragmentContainer=(FrameLayout) v.findViewById(R.id.contenedorfragment);
        FragmentManager fragmentManager= ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sugerencia, container, false);
        context=getActivity();
        Volver=(Button) view.findViewById(R.id.volversugerencia);
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarFragment(v);
            }
        });
        btningresarsug=(Button) view.findViewById(R.id.btnSugerencia);
        etSugerencia=(EditText) view.findViewById(R.id.etsygerencia);
        btningresarsug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sugerencia sugerencia=new Sugerencia(etSugerencia.getText().toString());
                if (sugerencia.getSugerencia().equals("")){

                    Toast.makeText(context,"Ingrese una sugerencia",Toast.LENGTH_SHORT).show();
                }else{
                    if(Sugerencia.IngresarSugerencia(sugerencia)==1){
                        Toast.makeText(context,"Gracias por aportar una sugerencia!!",Toast.LENGTH_SHORT).show();
                        CerrarFragment(v);
                    }

                }

            }
        });

        return  view;
    }
}