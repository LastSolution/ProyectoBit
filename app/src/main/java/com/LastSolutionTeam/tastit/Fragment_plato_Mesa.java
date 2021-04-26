package com.LastSolutionTeam.tastit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LastSolutionTeam.tastit.POJO.Cliente;
import com.LastSolutionTeam.tastit.POJO.Pedido;
import com.LastSolutionTeam.tastit.POJO.PedidoPlato;
import com.LastSolutionTeam.tastit.POJO.Plato;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_plato_Mesa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_plato_Mesa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idplato";


    // TODO: Rename and change types of parameters
    private int idplato;
    Context context;
    Button btnpedir;
    Button Volver;
    TextView txtdescplato;
    TextView txtNombreplato;
    ImageView imgplato;
    Plato plato;
    FrameLayout fragmentContainer;
    Fragment fragment=this;
    private Bitmap convertirlogoBitMap(byte [] imgplato){
        Bitmap bmp = BitmapFactory.decodeByteArray(imgplato, 0, imgplato.length);
        return bmp;

    }
    private  void AgregarPlatoaPedido(Cliente cliente){

    }
    public Fragment_plato_Mesa () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idplato Parameter 1.
     * @return A new instance of fragment Fragment_plato_Mesa.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_plato_Mesa newInstance(int idplato) {
        Fragment_plato_Mesa fragment = new Fragment_plato_Mesa();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM1, idplato);
        fragment.setArguments(args);
        return fragment;

    }
    public static Fragment_plato_Mesa newInstance() {
        Fragment_plato_Mesa fragment = new Fragment_plato_Mesa();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idplato = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_plato__mesa, container, false);
        context=getActivity();
        txtdescplato=(TextView) view.findViewById(R.id.txtdescripcionplatomesa);
        txtNombreplato=(TextView) view.findViewById(R.id.txtnomplatomesa);
        imgplato=(ImageView) view.findViewById(R.id.imgplatomesa);
        Volver=(Button) view.findViewById(R.id.btncerrarfragmentplato);
        btnpedir=(Button) view.findViewById(R.id.btnagregarplatopedido);
        plato=Plato.BuscarPlato(idplato);
        txtNombreplato.setText(plato.getNombre_plato());
        txtdescplato.setText(plato.getDescripcion());
        imgplato.setImageBitmap(convertirlogoBitMap(plato.getImagen()));
        btnpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idclientepedido=VarGlobales.getClientepedidoActual().getId_cliente();
                Pedido pedido=Pedido.BuscarPedidoporcliente(idclientepedido);
                PedidoPlato pedidoPlato=new PedidoPlato(pedido.getId_pedido(),plato.getId_plato(),1);
                if(PedidoPlato.IngresarPedido(pedidoPlato)!=0) {

                    Toast.makeText(context, "Ingresado con exito", Toast.LENGTH_SHORT).show();
                    CerrarFragment(v);

                }else{
                    Toast.makeText(getActivity(), "Error al ingresar pedido", Toast.LENGTH_SHORT);
                }

            }
        });
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CerrarFragment(v);
            }
        });


        return view;
    }
    public void CerrarFragment(View v){
        
        fragmentContainer=(FrameLayout) v.findViewById(R.id.ContenederoFragmentPlato);
        FragmentManager fragmentManager= ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }
}