package com.LastSolutionTeam.tastit.Adaptadores;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;

import java.util.ArrayList;

public class UsuarioAdaptador extends RecyclerView.Adapter {
    ArrayList<Usuario> usuarios;
    Activity activity;

    public UsuarioAdaptador(ArrayList<Usuario> Lista,Activity activity) {
        this.usuarios=usuarios;
        this.activity=activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
