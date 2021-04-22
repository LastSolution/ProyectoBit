package com.LastSolutionTeam.tastit.Adaptadores;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.R;

import java.util.ArrayList;

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.UsuarioViewHolder> {
    ArrayList<Usuario> usuarios;
    Activity activity;

    public UsuarioAdaptador(ArrayList<Usuario> Lista,Activity activity) {
        this.usuarios=Lista;
        this.activity=activity;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usuario,parent,false);

        return new UsuarioViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder , int position) {
          Usuario usuario=usuarios.get(position);
          usuarioViewHolder.TxtUsername.setText(usuario.getUsername());
          usuarioViewHolder.TxtTipo.setText(usuario.getTipo());
          usuarioViewHolder.TxtEmpresa.setText(usuario.getRut_empresa());
            usuarioViewHolder.btnEliminaruser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Usuario.EliminarUsuario(usuario.getId_usuario());
                usuarios.remove(position);
                notifyItemRemoved(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {

        private TextView TxtUsername;
        private TextView TxtTipo;
        private TextView TxtEmpresa;
        private Button btnEliminaruser;


        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtUsername=(TextView) itemView.findViewById(R.id.txtUserName);
            TxtTipo=(TextView) itemView.findViewById(R.id.txtTipoUsuario);
            TxtEmpresa=(TextView) itemView.findViewById(R.id.txtEmpresaUsuario);
            btnEliminaruser=(Button) itemView.findViewById(R.id.btnEliminarUser);


        }
    }
}
