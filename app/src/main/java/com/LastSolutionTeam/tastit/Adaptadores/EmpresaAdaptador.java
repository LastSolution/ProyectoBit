package com.LastSolutionTeam.tastit.Adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.R;

import java.util.ArrayList;

public class EmpresaAdaptador extends RecyclerView.Adapter<EmpresaAdaptador.EmpresaViewHolder>{

public  EmpresaAdaptador(ArrayList<Empresa>empresas){

    this.empresas=empresas;
}

ArrayList<Empresa> empresas;
    @NonNull

    //infla el layout y lo pasara al viewholder para obtener los views
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_empresa,parent,false);

        return new EmpresaViewHolder(v);
    }
    //Asocia cada elemento de la lista
    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder empresaViewHolder, int position) {
        Empresa empresa= empresas.get(position);
        empresaViewHolder.txtNombreEmpresa.setText(empresa.getNombre());
        empresaViewHolder.txtRutEmpresa.setText(empresa.getRut());
        empresaViewHolder.txtTelEmpresa.setText(empresa.getTelefono());
        empresaViewHolder.txtNombreEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
//cant de elementos de mi lista de empresas
    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public static  class EmpresaViewHolder extends RecyclerView.ViewHolder {


        private TextView txtNombreEmpresa;
        private TextView txtRutEmpresa;
        private TextView txtTelEmpresa;


         public EmpresaViewHolder(@NonNull View itemView) {
             super(itemView);
             txtNombreEmpresa =(TextView) itemView.findViewById(R.id.txtNomEmpresa);
             txtRutEmpresa=(TextView) itemView.findViewById(R.id.txtRutEmpresa);
             txtTelEmpresa=(TextView) itemView.findViewById(R.id.txtTelefonoEmpresa);




         }
     }
}
