package com.LastSolutionTeam.tastit.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.POJO.Plato;
import com.LastSolutionTeam.tastit.POJO.Usuario;
import com.LastSolutionTeam.tastit.PlatosActivity;
import com.LastSolutionTeam.tastit.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class PlatoAdaptador extends RecyclerView.Adapter<PlatoAdaptador.PlatoViewHolder> {
    ArrayList<Plato> platos;
    Activity activity;
    private Bitmap convertirlogoBitMap(byte [] imgplato){
        Bitmap bmp = BitmapFactory.decodeByteArray(imgplato, 0, imgplato.length);
        return bmp;

    }
    private Bitmap LogoaBitmap(byte [] imgplato){
        InputStream inputStream  = new ByteArrayInputStream(imgplato);
        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
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
        if(plato.getImagen()!=null){

            platoViewHolder.imgPlato.setImageBitmap(LogoaBitmap(plato.getImagen()));
        }
    platoViewHolder.btnmodifusuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(v.getContext(), PlatosActivity.class);
            myIntent.putExtra("nombre",plato.getNombre_plato());
            myIntent.putExtra("precio",plato.getPrecio());
            myIntent.putExtra("descripcion",plato.getDescripcion());
            myIntent.putExtra("categoria",plato.getCategoria());
            myIntent.putExtra("imagen",plato.getImagen());
            activity.startActivity(myIntent);

        }
    });
    platoViewHolder.btneliminarusuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Plato.EliminarPlato(plato);
            notifyItemRemoved(position);
            platos.remove(position);

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
        private TextView txtdescripcion;
        private ImageView imgPlato;
        private Button btnmodifusuario;
        private Button btneliminarusuario;


        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombreplato    = (TextView) itemView.findViewById(R.id.txtnomplato);
            txtprecioplato    = (TextView) itemView.findViewById(R.id.txtprecioplato);
            txtdescripcion = (TextView) itemView.findViewById(R.id.descripcionplato);
            btnmodifusuario    = (Button) itemView.findViewById(R.id.btnmodificarplato);
            btneliminarusuario = (Button) itemView.findViewById(R.id.btneliminarplato);
            imgPlato=(ImageView) itemView.findViewById(R.id.cardimgplato);
        }
    }
}