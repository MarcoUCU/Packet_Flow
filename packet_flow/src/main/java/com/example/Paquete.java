package com.example;

public class Paquete {
    private String idMensaje;
    private int numSecuencia;
    private int totalPaquetes;
    private String fragmento;
    private EstadoPaquete estado;

    public Paquete(String idMensaje, int numSecuencia, int totalPaquetes, String fragmento) {
        this.estado = estado == null ? EstadoPaquete.PENDIENTE : estado;
        this.fragmento = fragmento;
        this.numSecuencia = numSecuencia;
        this.idMensaje = idMensaje;
        this.totalPaquetes = totalPaquetes;
    }

    public int getTotalPaquetes(){
        return totalPaquetes;
    }

    public int getNumSecuencia(){
        return numSecuencia;
    }

    public String getIdMensaje(){
        return idMensaje;
    }

    public String getFragmento(){
        return fragmento;
    }

    public EstadoPaquete getEstadoPaquete() {
        return estado;
    }

    public void setEstado(EstadoPaquete estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "idMensaje='" + idMensaje + '\'' +
                ", numSecuencia=" + numSecuencia +
                ", totalPaquetes=" + totalPaquetes +
                ", fragmento='" + fragmento + '\'' +
                ", estado=" + estado +
                '}';
    }
}
