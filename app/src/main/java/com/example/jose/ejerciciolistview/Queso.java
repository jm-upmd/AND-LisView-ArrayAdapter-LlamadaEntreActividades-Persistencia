package com.example.jose.ejerciciolistview;

public class Queso {
    private String nombre;
    private String origen;
    private boolean favorito;

    public Queso(String nombre, String origen){
        this(nombre, origen,false);
         favorito=false;
    }

    public Queso(String nombre, String origen, boolean favorito){
        this.nombre = nombre;
        this.origen = origen;
        this.favorito = favorito;
    }

    // Sobrescribo metod de la clase Object para que me devuelva un estring con los
    // valores de las variable de clase separados por punto y coma
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(';').append(origen).append(';').append(favorito?'1':'0').append('\n');

        return sb.toString();

    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
