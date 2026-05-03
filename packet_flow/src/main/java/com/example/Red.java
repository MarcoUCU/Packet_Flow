package com.example;


public class Red {

    private int capacidadMax;
    private int tamMaxPaquete;
    private Cola<Paquete> colaTransmision;
    private Lista<Paquete> listaTransito;
    private Lista<Paquete> bufferRecepcion;
    private Lista<Mensaje> mensajes;

    public Red(int capacidadMax, int tamMaxPaquete) {
        this.capacidadMax = capacidadMax;
        this.tamMaxPaquete = tamMaxPaquete;
        this.colaTransmision = new Cola<>();
        this.listaTransito = new Lista<>();
        this.bufferRecepcion = new Lista<>();
        this.mensajes = new Lista<>();
    }

    
    public boolean hayEspacioEnTransito() {
        return listaTransito.tamanio() < capacidadMax;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getTamMaxPaquete() {
        return tamMaxPaquete;
    }

    public Cola<Paquete> getColaTransmision() {
        return colaTransmision;
    }

    public Lista<Paquete> getListaTransito() {
        return listaTransito;
    }

    public Lista<Paquete> getBufferRecepcion() {
        return bufferRecepcion;
    }

    public Lista<Mensaje> getMensajes() {
        return mensajes;
    }
}