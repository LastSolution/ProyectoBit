package com.LastSolutionTeam.tastit.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.POJO.Plato;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.R;

import java.util.ArrayList;

public class PlatoAdaptador extends RecyclerView.Adapter<PlatoAdaptador.PlatoViewHolder> {
    ArrayList<Plato> platos;
    Activity activity;

    public PlatoAdaptador(ArrayList<Plato> platos, Activity activity) {
        this.platos = platos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_platos, parent, false);

        return new PlatoViewHolder (v);

    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder platoViewHolder, int position) {
    Plato plato=platos.get(position);

    platoViewHolder.txtnombreplato.setText(plato.getNombre_plato());
    platoViewHolder.txtprecioplato.setText( Double.toString(plato.getPrecio()));
    platoViewHolder.btnmodifusuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    });
    platoViewHolder.btneliminarusuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    }



    @Override
    public int getItemCount() {
        return platos.size();
    }


    public static class PlatoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtnombreplato;
        private TextView txtprecioplato;
        private Button btnmodifusuario;
        private Button btneliminarusuario;


        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombreplato    = (TextView) itemView.findViewById(R.id.txtnomplato);
            txtprecioplato    = (TextView) itemView.findViewById(R.id.txtprecioplato);
            btnmodifusuario    = (Button) itemView.findViewById(R.id.btnmodificarplato);
            btneliminarusuario = (Button) itemView.findViewById(R.id.btneliminarplato);
        }
    }
}