package com.example.jose.ejerciciolistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class DetalleQueso extends AppCompatActivity {
    int indiceQueso;
    boolean quesoFavorito;
    static String QUESO_CAMBIADO_KEY;
    Queso elQueso;
    CheckBox favorito;

    List<Queso> quesos = ListaQuesos.getInstance().getQuesos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_queso);

        QUESO_CAMBIADO_KEY = getClass() +"queso_cambiado_key";


        TextView NombreQueso = findViewById(R.id.txtNombre);
        TextView OrigenQueso = findViewById(R.id.txtOrigen);
        favorito = findViewById(R.id.checkFavorito);

        Intent intent = getIntent(); // Recojo intent

        // Extraigo el indice del queso seleccionado
        indiceQueso = intent.getIntExtra(MainActivity.KEY_POSICION,0);

        // Pueblo los views con los datos del queso correspondiente al indice recogido en el intent
        elQueso = quesos.get(indiceQueso);
        NombreQueso.setText(elQueso.getNombre());
        OrigenQueso.setText(elQueso.getOrigen());
        favorito.setChecked(elQueso.isFavorito());

        // Guardo el estado de favorito que he recibido para cotejar si sigue
        // siendo el mismo o a cambiado cuando vaya a abandonar la activity.
        // En función de ello comunicare a la actividad llamante si tiene que refrescar la lista
        // o no.
        quesoFavorito = elQueso.isFavorito();

        // Intent para comunicar la vuelta a la actividad llamante.
        final Intent intentVuelta = new Intent();

        // Doto de un listener al check para que cada vez que cambie su valor actualice
        // el dato en el objeto queso.
        // También actualiza en el itent el valor QUESO_CAMBIADO_KEY, que será true si
        // el valor del check es distinto al que tenía inicialmente el queso.

        /*favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                elQueso.setFavorito(isChecked);
                intentVuelta.putExtra(QUESO_CAMBIADO_KEY,quesoFavorito != elQueso.isFavorito());

            }
        });*/

        //setResult(RESULT_OK,intentVuelta);

        // Anque el metodo ya a preparado el itent que enviará al cerrarse la actividad, este
        // seguira actualizandose a través del evento de pulsación en el check
        // No obstante es mejor usar la reescritura del método finish como se explica más abajo
    }

    /*
    Para establecer el intent de vuelta con el método setResult...
    Si la actividad tuviera algún componente que  pulsar para cerrarla (botón, menu...), como
    respuesta a la acción de cerrar crearíamos el intent, haríamos el setResult y hariamos
    un finish() para cerrar la actividad.
    Al no ser así tenemos que sobrescribir alguno de los métodos que se ejecutan cuando la actividad se vaya a
    destruir. Estos pueden ser: OnBackPressed ( que se ejecuta cuando se pulsa el boton back del
    dispositivo) o finish (que se ejecuta cuando la actividad se va a destruir)
    Abajo están las dos opciones. La primera comentada. Preferible usar sobrescritura del finish
     ya que con onBackPressed puediera darse algún caso en que la actividad se destruyera por otra vía...
     */

    /*@Override
    public void onBackPressed() {
        Log.d("Queso","En el finish");

        elQueso.setFavorito(favorito.isChecked());
        Intent intentVuelta = new Intent();
        intentVuelta.putExtra(QUESO_CAMBIADO_KEY,quesoFavorito != elQueso.isFavorito());

        setResult(RESULT_OK,intentVuelta);
        super.onBackPressed();
    }*/

   // Sobrescribo el metodo finish() de la activity para que sea la que
    // cree el iten y haga el setResult.
    @Override
    public void finish() {
        Log.d("Queso","En el finish");

        elQueso.setFavorito(favorito.isChecked());
        Intent intentVuelta = new Intent();
        intentVuelta.putExtra(QUESO_CAMBIADO_KEY,quesoFavorito != elQueso.isFavorito());

        setResult(RESULT_OK,intentVuelta);

        // Una vez crado intent y ejecutado el setResult llamo a finish de la clase padre
        // para que cierre la actividad.
        super.finish();
    }

    /* Nota final:
        Si hacemos lo anterior en onDestroy no funcionará.
     */

}
