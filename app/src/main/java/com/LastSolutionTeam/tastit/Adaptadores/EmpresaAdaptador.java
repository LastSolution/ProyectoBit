package com.LastSolutionTeam.tastit.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.LastSolutionTeam.tastit.EmpresaActivity;
import com.LastSolutionTeam.tastit.POJO.Empresa;
import com.LastSolutionTeam.tastit.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class EmpresaAdaptador extends RecyclerView.Adapter<EmpresaAdaptador.EmpresaViewHolder>{

    ArrayList<Empresa> empresas;
    Activity activity;

public  EmpresaAdaptador(ArrayList<Empresa>empresas, Activity activity){

    this.empresas=empresas;
    this.activity=activity;

}
    private Bitmap convertirlogoBitMap(byte [] logo){
        Bitmap bmp = BitmapFactory.decodeByteArray(logo, 0, logo.length);
        return bmp;

    }
    private Bitmap LogoaBitmap(byte [] logo){
        InputStream inputStream  = new ByteArrayInputStream(logo);
        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
    return bitmap;
    }

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
        Empresa empresa = empresas.get(position);
        empresaViewHolder.txtNombreEmpresa.setText(empresa.getNombre());
        empresaViewHolder.txtRutEmpresa.setText(empresa.getRut());
        empresaViewHolder.txtTelEmpresa.setText(empresa.getTelefono());
        if(empresa.getLogo()!=null){

            empresaViewHolder.imglogo.setImageBitmap(LogoaBitmap(empresa.getLogo()));
        }
        empresaViewHolder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), EmpresaActivity.class);
                myIntent.putExtra("nombre",empresa.getNombre());
                myIntent.putExtra("rut",empresa.getRut());
                myIntent.putExtra("telefono",empresa.getTelefono());
                myIntent.putExtra("correo",empresa.getCorreo());
                activity.startActivity(myIntent);

            }
        });
        empresaViewHolder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa.EliminarEmpresa(empresa.getRut());
                empresas.remove(position);
                notifyItemRemoved(position);


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
        private Button btnEditar;
        private Button btnEliminar;
        private ImageView imglogo;
         public EmpresaViewHolder(@NonNull View itemView) {
             super(itemView);
             txtNombreEmpresa =(TextView) itemView.findViewById(R.id.txtNomEmpresa);
             txtRutEmpresa=(TextView) itemView.findViewById(R.id.txtRutEmpresa);
             txtTelEmpresa=(TextView) itemView.findViewById(R.id.txtTelefonoEmpresa);
             btnEditar=(Button) itemView.findViewById(R.id.btnEditarEmpresa);
             btnEliminar=(Button) itemView.findViewById(R.id.btneliminarEmpresa);
             imglogo=(ImageView) itemView.findViewById(R.id.logoEmpresa);



         }
     }
}
