package com.example.jose.ejerciciolistview;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_SOLICITUD = 0;
    int posSelec=0;
    static String KEY_POSICION;
    ArrayAdapter<Queso> adaptador;
    ListView listViewQuesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_adapter);

         KEY_POSICION= getClass().getName() + "key_posicion";

        // Instancia objetos view
        listViewQuesos = findViewById(R.id.listaView);
        Button btnBorrar = findViewById(R.id.btnBorrar);
        Button btnInsertar = findViewById(R.id.btnInsertar);
        Button btnModificar = findViewById(R.id.btnModificar);

        // Obtengo instancia de lista de quesos

        final List<Queso> listaQuesos =  ListaQuesos.getInstance().getQuesos();

        // Creo array adapter
        // También puede crearme una clase adaptodor extendiendo de ArrayAdapter y reescribirle
        // el getView

         adaptador = new ArrayAdapter<Queso>(this,0,listaQuesos){
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if(convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.layout_item_queso, null, false);
                }

                // Poblamos los views con sus valores
                TextView queso = convertView.findViewById(R.id.nombre_queso_texview);
                queso.setText(listaQuesos.get(position).getNombre());

                TextView origen = convertView.findViewById(R.id.origen);
                origen.setText(listaQuesos.get(position).getOrigen());

                ImageView imgFavorito = convertView.findViewById(R.id.imgFavorito);

                imgFavorito.setVisibility(listaQuesos.get(position).isFavorito() ? View.VISIBLE : View.GONE);
                
                return convertView;
            }
        };



        // Vicula adapter a la ListView
        listViewQuesos.setAdapter(adaptador);

        // Color del item seleccionado en la listView
        // Esto también se puede establecer en el xml con la propiedad android:listSelector

        listViewQuesos.setSelector(R.color.ColorGris);



        // Listener para click en item de la lista
       listViewQuesos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posSelec = position;

                // Llamada a segunda actividad pasando la posición
                Intent intent = new Intent(MainActivity.this, DetalleQueso.class);
                intent.putExtra(KEY_POSICION,position);
                startActivityForResult(intent, CODIGO_SOLICITUD);

          }


        });



        // Cramos Listeners de los botones

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borra el view seleccionado

                listaQuesos.remove(posSelec);
                adaptador.notifyDataSetChanged();

                // Si borramos el último elemento de la lista actualizamos la ultima posición

                posSelec = posSelec == listaQuesos.size() ? posSelec-1 : posSelec;

            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listaQuesos.add(posSelec,new Queso("Nuevo Queso_"+ posSelec, "Villa Quesos" ));
                //adaptador.notifyDataSetChanged();

                // Esto es equivalente a las dos lineas anteriores
                adaptador.insert(new Queso("Nuevo Queso_"+ posSelec, "Villa Quesos" ),posSelec);
                listViewQuesos.smoothScrollToPosition(posSelec);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Queso q = adaptador.getItem(posSelec);
                q.setNombre(q.getNombre() + "(modificado)");
                adaptador.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_SOLICITUD && resultCode == RESULT_OK){
            Log.d("Queso",((Boolean) data.getBooleanExtra(DetalleQueso.QUESO_CAMBIADO_KEY,false)).toString());

            if(data.getBooleanExtra(DetalleQueso.QUESO_CAMBIADO_KEY,false)){

                adaptador.notifyDataSetChanged();
                //listViewQuesos.setSelection(posSelec);


            }
        }
    }
}
