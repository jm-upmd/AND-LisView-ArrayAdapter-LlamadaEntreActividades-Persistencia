package com.example.jose.ejerciciolistview;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class ListaQuesos {

    // Obtenemos el contexto de la aplicación
    // para poder usarlo en el los metodos de lectura/escritura en fichero

    Context context = MainActivity.getContext(); //App.getAppContext();


    private static final ListaQuesos ourInstance = new ListaQuesos();

    private static final String FICHERO="quesos.dat";

    private   ArrayList<Queso> quesos = new ArrayList<>();

    static ListaQuesos getInstance() {
        return ourInstance;
    }

    public List<Queso> getQuesos(){
        return quesos;
    }


    private ListaQuesos() {

        // Si aun no se ha generado el fichero de quesos en disco porque es la priemra vez
        // que ejecutamos el programa, entonces generamos estos, que serán guardados
        // cuando la app pase a estado Pause.
        // La proxima vez que se ejecute la app  ya los leera del fichero

        int estadoLectura = leer();

        if (estadoLectura == 0) {
            Log.d("quesos","Generando fichero de quesos inicial");
            inicializaQuesos();
        } else if(estadoLectura == 1){
            Log.d("quesos","Quesos leidos del fichero de disco");
        } else {
            Log.e("quesos","Error leyendo fichero de quesos");
        }
    }

    void escribir() {


        // Objeto java para escribir en fichero de texto.
        OutputStreamWriter out;

        // Abre fichero y graba los quesos. Campos separados por punto y coma.

        try {
            out = new OutputStreamWriter(context.openFileOutput(FICHERO, 0));
            for (Queso q : quesos) {
                out.write(q.toString());
            }
            out.flush();
            out.close();

        } catch (Exception t) {
            Log.e("quesos", "Error al escribir quesos");
        }
    }

     int leer() {

        InputStreamReader in=null;
        try {
            in = new InputStreamReader(context.openFileInput(FICHERO));
            BufferedReader buff = new BufferedReader(in);
            String strTmp;
            while ((strTmp = buff.readLine())!=null){
                quesos.add(nuevoQueso(strTmp));

            }
        } catch (FileNotFoundException  | ExceptionInInitializerError e ) {
            Log.e("quesos", "No hay níngun dato guardado aún en fichero: " + FICHERO);
            return 0;

        } catch (IOException e){
            Log.e("quesos", "Error leyendo el fichero: " + FICHERO);
            return -1;

        } finally{
            try {
                if(in!=null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 1;
     }

    // Construye objeto Queso a partir de string con los valores de las variables de clase
    // separados por punto y coma
     private Queso nuevoQueso(String strTmp) {
        String[] a = strTmp.split(";");
        return new Queso(a[0],a[1], a[2].equals("1"));
    }

    // Sólo se ejecuta este método en el caso de que no exista un fichero con los quesos
    void inicializaQuesos(){
        quesos.add(new Queso("Abbaye du Mont des Cats", "Luxemburgo"));
        quesos.add(new Queso("Abertam", "Malta"));
        quesos.add(new Queso("Ackawi", "Austria"));
        quesos.add(new Queso("Acorn", "Georgia"));
        quesos.add(new Queso("Allgauer Emmentaler", "Grecia"));
        quesos.add(new Queso("Anejo Enchilado", "Montenegro"));
        quesos.add(new Queso("Anthoriro", "Serbia"));
        quesos.add(new Queso("Ardi Gasna", "Estonia"));
        quesos.add(new Queso("Asiago", "Bélgica"));
        quesos.add(new Queso("Balaton", "Grecia"));
        quesos.add(new Queso("Barry's Bay Cheddar", "Montenegro"));
        quesos.add(new Queso("Basing", "Polonia"));
        quesos.add(new Queso("Bavarian Bergkase", "Federación Rusa"));
        quesos.add(new Queso("Beauvoorde", "Países Bajos"));
        quesos.add(new Queso("Berkswell", "Andorra"));
        quesos.add(new Queso("Blue", "Albania"));
        quesos.add(new Queso("Boeren Leidenkaas", "Italia"));
        quesos.add(new Queso("Bra", "Polonia"));
        quesos.add(new Queso("Buffalo", "Malta"));
        quesos.add(new Queso("Cabrales", "Mónaco"));
        quesos.add(new Queso("Caerphilly", "España"));
        quesos.add(new Queso("Cairnsmore", "Noruega"));
        quesos.add(new Queso("Canestrato", "Noruega"));
        quesos.add(new Queso("Castellano", "Hungría"));
        quesos.add(new Queso("Castelleno", "Andorra"));
        quesos.add(new Queso("Castelmagno", "Croacia"));
        quesos.add(new Queso("Castigliano", "Italia"));
        quesos.add(new Queso("Comte", "Eslovaquia"));
        quesos.add(new Queso("Coolea", "Andorra"));
        quesos.add(new Queso("Coquetdale", "Armenia"));
        quesos.add(new Queso("Corleggy", "Albania"));
        quesos.add(new Queso("Cotherstone", "Croacia"));
        quesos.add(new Queso("Cotija", "Hungría"));
        quesos.add(new Queso("Coverdale", "Liechtenstein"));
        quesos.add(new Queso("Crayeux de Roncq", "España"));
        quesos.add(new Queso("Crottin de Chavignol", "Eslovenia"));
        quesos.add(new Queso("Curworthy", "Letonia"));
        quesos.add(new Queso("Cwmtawe Pecorino", "Dinamarca"));
        quesos.add(new Queso("Denhany Dorset Drum", "Croacia"));
        quesos.add(new Queso("Derby", "Eslovenia"));
        quesos.add(new Queso("Doolin", "Georgia"));
        quesos.add(new Queso("Dorset Blue Vinney", "Suiza"));
        quesos.add(new Queso("Double Worcester", "República Checa"));
        quesos.add(new Queso("Dry Jack", "Suiza"));
        quesos.add(new Queso("Duddleswell", "Albania"));
        quesos.add(new Queso("Dunlop", "Francia"));
        quesos.add(new Queso("Duroblando", "Georgia"));
        quesos.add(new Queso("Dutch Mimolette (Commissiekaas)", "Irlanda"));
        quesos.add(new Queso("Emmental", "Italia"));
        quesos.add(new Queso("Etorki", "Alemania"));
        quesos.add(new Queso("Evora De L'Alentejo", "San Marino"));
        quesos.add(new Queso("Finlandia Swiss", "Andorra"));
        quesos.add(new Queso("Fiore Sardo", "Macedonia"));
        quesos.add(new Queso("Folded cheese with mint", "Chipre"));
        quesos.add(new Queso("Four Herb Gouda", "Letonia"));
        quesos.add(new Queso("Fourme de Montbrison", "Serbia"));
        quesos.add(new Queso("Fribourgeois", "Georgia"));
        quesos.add(new Queso("Friesekaas", "Reino Unido"));
        quesos.add(new Queso("Friesian", "Hungría"));
        quesos.add(new Queso("Fromage a Raclette", "Mónaco"));
        quesos.add(new Queso("Frying Cheese", "Albania"));
        quesos.add(new Queso("Gabriel", "Malta"));
        quesos.add(new Queso("Gammelost", "Bulgaria"));
        quesos.add(new Queso("Gaperon a l'Ail", "Austria"));
        quesos.add(new Queso("Garrotxa", "Eslovenia"));
        quesos.add(new Queso("Gornyaltajski", "Macedonia"));
        quesos.add(new Queso("Gospel Green", "Reino Unido"));
        quesos.add(new Queso("Gowrie", "Polonia"));
        quesos.add(new Queso("Grafton Village Cheddar", "Ciudad del Vaticano"));
        quesos.add(new Queso("Grana", "Estonia"));
        quesos.add(new Queso("Grana Padano", "Finlandia"));
        quesos.add(new Queso("Graviera", "Bosnia-Herzegovina"));
        quesos.add(new Queso("Gruyere", "Austria"));
        quesos.add(new Queso("Halloumi", "Alemania"));
        quesos.add(new Queso("Halloumy (Australian)", "Italia"));
        quesos.add(new Queso("Haloumi-Style Cheese", "Bélgica"));
        quesos.add(new Queso("Heidi Gruyere", "Estonia"));
        quesos.add(new Queso("Herriot Farmhouse", "Rumania"));
        quesos.add(new Queso("Iberico", "Eslovaquia"));
        quesos.add(new Queso("Idaho Goatster", "Suiza"));
        quesos.add(new Queso("Idiazabal", "Rumania"));
        quesos.add(new Queso("Isle of Mull", "Bielorrusia"));
        quesos.add(new Queso("Jarlsberg", "Eslovaquia"));
        quesos.add(new Queso("Jindi Brie", "Suiza"));
        quesos.add(new Queso("Kadchgall", "Alemania"));
        quesos.add(new Queso("Kefalotyri", "Bosnia-Herzegovina"));
        quesos.add(new Queso("Laguiole", "Letonia"));
        quesos.add(new Queso("Lairobell", "Federación Rusa"));
        quesos.add(new Queso("Lancashire", "Letonia"));
        quesos.add(new Queso("Laruns", "Francia"));
        quesos.add(new Queso("Lavistown", "Lituania"));
        quesos.add(new Queso("Leafield", "Polonia"));
        quesos.add(new Queso("Leicester", "Mónaco"));
        quesos.add(new Queso("Leyden", "Mónaco"));
        quesos.add(new Queso("Lincolnshire Poacher", "Rumania"));
        quesos.add(new Queso("Llanboidy", "Hungría"));
        quesos.add(new Queso("Llanglofan Farmhouse", "España"));
        quesos.add(new Queso("Loch Arthur Farmhouse", "Letonia"));
        quesos.add(new Queso("Longhorn", "Mónaco"));
        quesos.add(new Queso("Lou Palou", "Bosnia-Herzegovina"));
        quesos.add(new Queso("Mahon", "Armenia"));
        quesos.add(new Queso("Malvern", "Bielorrusia"));
        quesos.add(new Queso("Manchego", "Armenia"));
        quesos.add(new Queso("Manur", "Azerbaiyán"));
        quesos.add(new Queso("Marble Cheddar", "Federación Rusa"));
        quesos.add(new Queso("Menallack Farmhouse", "Ciudad del Vaticano"));
        quesos.add(new Queso("Mihalic Peynir", "Malta"));
        quesos.add(new Queso("Montasio", "Eslovaquia"));
        quesos.add(new Queso("Monterey Jack Dry", "Croacia"));
        quesos.add(new Queso("Northumberland", "Ciudad del Vaticano"));
        quesos.add(new Queso("Orkney Extra Mature Cheddar", "Croacia"));
        quesos.add(new Queso("Oschtjepka", "Federación Rusa"));
        quesos.add(new Queso("Parmesan (Parmigiano)", "Bulgaria"));
        quesos.add(new Queso("Parmigiano Reggiano", "Reino Unido"));
        quesos.add(new Queso("Pecorino", "Italia"));
        quesos.add(new Queso("Pecorino Romano", "Países Bajos"));
        quesos.add(new Queso("Penbryn", "Chipre"));
        quesos.add(new Queso("Piora", "Italia"));
        quesos.add(new Queso("Plymouth Cheese", "Bielorrusia"));
        quesos.add(new Queso("Pressato", "Portugal"));
        quesos.add(new Queso("Pyengana Cheddar", "Bielorrusia"));
        quesos.add(new Queso("Queso del Tietar", "Noruega"));
        quesos.add(new Queso("Queso Iberico", "Islandia"));
        quesos.add(new Queso("Queso Majorero", "Moldova"));
        quesos.add(new Queso("Queso Para Frier", "Noruega"));
        quesos.add(new Queso("Raclette", "Polonia"));
        quesos.add(new Queso("Ragusano", "Albania"));
        quesos.add(new Queso("Reggianito", "Grecia"));
        quesos.add(new Queso("Remedou", "Armenia"));
        quesos.add(new Queso("Ricotta Salata", "Dinamarca"));
        quesos.add(new Queso("Romano", "Federación Rusa"));
        quesos.add(new Queso("Roncal", "Bielorrusia"));
        quesos.add(new Queso("Saanenkaese", "Irlanda"));
        quesos.add(new Queso("Sainte Maure", "Bulgaria"));
        quesos.add(new Queso("Salers", "Bielorrusia"));
        quesos.add(new Queso("Sancerre", "Países Bajos"));
        quesos.add(new Queso("Sap Sago", "Portugal"));
        quesos.add(new Queso("Sardo", "Bielorrusia"));
        quesos.add(new Queso("Sardo Egyptian", "Serbia"));
        quesos.add(new Queso("Sbrinz", "Polonia"));
        quesos.add(new Queso("Schabzieger", "Macedonia"));
        quesos.add(new Queso("Serat", "San Marino"));
        quesos.add(new Queso("Seriously Strong Cheddar", "Andorra"));
        quesos.add(new Queso("Shelburne Cheddar", "Bielorrusia"));
        quesos.add(new Queso("Shropshire Blue", "Croacia"));
        quesos.add(new Queso("Smoked Gouda", "Bulgaria"));
        quesos.add(new Queso("Spenwood", "Albania"));
        quesos.add(new Queso("Sraffordshire Organic", "Andorra"));
        quesos.add(new Queso("Stinking Bishop", "Rumania"));
        quesos.add(new Queso("Swaledale", "Rumania"));
        quesos.add(new Queso("Swiss", "Mónaco"));
        quesos.add(new Queso("Syrian (Armenian)", "Hungría"));
        quesos.add(new Queso("Tala", "Georgia"));
        quesos.add(new Queso("Teifi", "Islandia"));
        quesos.add(new Queso("Tillamook Cheddar", "Letonia"));
        quesos.add(new Queso("Tomme d'Abondance", "Bulgaria"));
        quesos.add(new Queso("Tyn Grug", "Polonia"));
        quesos.add(new Queso("Tyning", "Eslovaquia"));
        quesos.add(new Queso("Ubriaco", "Hungría"));
        quesos.add(new Queso("Wellington", "Andorra"));
        quesos.add(new Queso("Wensleydale", "España"));
        quesos.add(new Queso("White Stilton", "Serbia"));
        quesos.add(new Queso("Xynotyro", "Serbia"));
        quesos.add(new Queso("Yarg Cornish", "Bélgica"));
        quesos.add(new Queso("Zamorano", "Azerbaiyán"));
        quesos.add(new Queso("Zanetti Grana Padano", "España"));
        quesos.add(new Queso("Zanetti Parmigiano Reggiano", "Polonia"));
    }

}
