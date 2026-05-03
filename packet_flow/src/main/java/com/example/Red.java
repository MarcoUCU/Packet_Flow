package com.example;

import com.TDA.Implementaciones.Cola;
import com.TDA.Implementaciones.ListaEnlazada;

public class Red {

    private int capacidadMax;
    private int tamMaxPaquete;
    private Cola<Paquete> colaTransmision;
    private ListaEnlazada<Paquete> listaTransito;
    private ListaEnlazada<Paquete> bufferRecepcion;
    private ListaEnlazada<Mensaje> mensajes;

    public Red(int capacidadMax, int tamMaxPaquete) {
        this.capacidadMax = capacidadMax;
        this.tamMaxPaquete = tamMaxPaquete;
        this.colaTransmision = new Cola<>();
        this.listaTransito = new ListaEnlazada<>();
        this.bufferRecepcion = new ListaEnlazada<>();
        this.mensajes = new ListaEnlazada<>();
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

    public ListaEnlazada<Paquete> getListaTransito() {
        return listaTransito;
    }

    public ListaEnlazada<Paquete> getBufferRecepcion() {
        return bufferRecepcion;
    }

    public ListaEnlazada<Mensaje> getMensajes() {
        return mensajes;
    }
}