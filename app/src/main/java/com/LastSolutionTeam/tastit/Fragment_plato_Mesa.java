package com.LastSolutionTeam.tastit;


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


public class Fragment_plato_Mesa extends Fragment {


    private static final String ARG_PARAM1 = "idplato";



    private int idplato;
    int Cant=1;
    Button btnsumar;
    Button btnRestar;
    TextView txtCantidad;
    Context context;
    Button btnpedir;
    Button Volver;
    TextView txtdescplato;
    TextView txtNombreplato;
    ImageView imgplato;
    Plato plato;
    FrameLayout fragmentContainer;
    Fragment fragment=this;
    Boolean platoYapedido=false;
    Pedido pedido;
    int idclientepedido;
    private Bitmap convertirlogoBitMap(byte [] imgplato){
        Bitmap bmp = BitmapFactory.decodeByteArray(imgplato, 0, imgplato.length);
        return bmp;

    }
    private  void AgregarPlatoaPedido(Cliente cliente){

    }
    public Fragment_plato_Mesa () {
        // Required empty public constructor
    }

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
    public void SumarCantidad(){
        Cant++;
        txtCantidad.setText(String.valueOf(Cant));
    }
    public void RestarCantidad(){
        Cant--;
        txtCantidad.setText(String.valueOf(Cant));
    }
    public Boolean VerificarPlatoEnPedido(int idpedido, int idplato){

        if(PedidoPlato.VerificarPlatoenPedido(idpedido,idplato)==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_plato__mesa, container, false);
        context=getActivity();
        btnsumar=(Button) view.findViewById(R.id.btnSumarCant);
        btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumarCantidad();
            }
        });
        btnRestar=(Button) view.findViewById(R.id.btnrestarcantidad);
        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestarCantidad();
            }
        });
        txtCantidad=(TextView)view.findViewById(R.id.txtcantplato);
        txtdescplato=(TextView) view.findViewById(R.id.txtdescripcionplatomesa);
        txtNombreplato=(TextView) view.findViewById(R.id.txtnomplatomesa);
        imgplato=(ImageView) view.findViewById(R.id.imgplatomesa);
        Volver=(Button) view.findViewById(R.id.btncerrarfragmentplato);
        btnpedir=(Button) view.findViewById(R.id.btnagregarplatopedido);
        plato=Plato.BuscarPlato(idplato);
        txtNombreplato.setText(plato.getNombre_plato());
        txtdescplato.setText(plato.getDescripcion());
        imgplato.setImageBitmap(convertirlogoBitMap(plato.getImagen()));
        idclientepedido=VarGlobales.getClientepedidoActual().getId_cliente();
        pedido=Pedido.BuscarPedidoporcliente(idclientepedido);
        if (VerificarPlatoEnPedido(pedido.getId_pedido(),plato.getId_plato())) {
        btnpedir.setText("Volver a Pedir");
        platoYapedido=true;
        }

        btnpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cant=Integer.parseInt(txtCantidad.getText().toString());
               if(platoYapedido==false){

                   PedidoPlato pedidoPlato=new PedidoPlato(pedido.getId_pedido(),plato.getId_plato(),Cant);
                   if(PedidoPlato.IngresarPedido(pedidoPlato)!=0) {
                       pedido.setPrecio_total(pedido.getPrecio_total()+(plato.getPrecio()*Cant));
                       Pedido.ModificarPedido(pedido);
                       ((Activity_Mesa) getActivity()).InicializarListGrupo(((Activity_Mesa) getActivity()).cantClientes);
                       Toast.makeText(context, "Ingresado con exito", Toast.LENGTH_SHORT).show();
                       CerrarFragment(v);

                   }else{
                       Toast.makeText(context, "Error al ingresar pedido", Toast.LENGTH_SHORT).show();
                   }
               }else{
                    PedidoPlato pedidoPlato=PedidoPlato.Buscar(pedido.getId_pedido(),plato.getId_plato());
                    int cantdelplato=pedidoPlato.getCantidad();
                    PedidoPlato.ModificarCantidad(Cant+cantdelplato,pedido.getId_pedido());
                    pedido.setPrecio_total(pedido.getPrecio_total()+(plato.getPrecio()*Cant));
                    Pedido.ModificarPedido(pedido);

                   ((Activity_Mesa) getActivity()).InicializarListGrupo(((Activity_Mesa) getActivity()).cantClientes);
                   CerrarFragment(v);
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