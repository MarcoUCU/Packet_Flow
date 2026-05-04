package com.example;

import com.TDA.Implementaciones.ListaEnlazada;

public class Mensaje {
    private String identificador;
    private String contenido;
    private ListaEnlazada<Paquete> paquetes;
    private int prioridad;

    public Mensaje(String identificador, String contenido, int prioridad) {
        this.identificador = identificador;
        this.contenido = contenido;
        this.prioridad = prioridad;
        this.paquetes = new ListaEnlazada<>();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getContenido() {
        return contenido;
    }

    public ListaEnlazada<Paquete> getPaquetes() {
        return paquetes;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "identificador='" + identificador + '\'' +
                ", contenido='" + contenido + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }

    public int cantPendientes() {
        int count = 0;
        for (Paquete paquete : paquetes) {
            if (paquete.getEstadoPaquete() == EstadoPaquete.PENDIENTE) {
                count++;
            }
            
        }
    }

    public int cantEnTransito() {
        int count = 0;
        for (int i = 0; i < paquetes.tamanio(); i++) {
            if (paquetes.obtener(i).getEstadoPaquete() == EstadoPaquete.EN_TRANSITO) {
                count++;
            }
        }
        return count;
    }

    public int cantRecibidos() {
        int count = 0;
        for (int i = 0; i < paquetes.tamanio(); i++) {
            if (paquetes.obtener(i).getEstadoPaquete() == EstadoPaquete.RECIBIDO) {
                count++;
            }
        }
        return count;
    }

    public int cantPerdidos() {
        int count = 0;
        for (int i = 0; i < paquetes.tamanio(); i++) {
            if (paquetes.obtener(i).getEstadoPaquete() == EstadoPaquete.PERDIDO) {
                count++;
            }
        }
        return count;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < paquetes.tamanio(); i++) {
            if (paquetes.obtener(i).getEstadoPaquete() != EstadoPaquete.RECIBIDO) {
                return false;
            }
        }
        return true;
    }

    public int contarPorEstado(EstadoPaquete estado) {
        int count = 0;
        for (int i = 0; i < paquetes.tamanio(); i++) {
            if (paquetes.obtener(i).getEstadoPaquete() == estado) {
                count++;
            }
        }
        return count;
    }

    public void fragmentar(int tamMaxPaquete) {
        int totalPaquetes = (int) Math.ceil((double) contenido.length() / tamMaxPaquete);
        for (int i = 0; i < totalPaquetes; i++) {
            int start = i * tamMaxPaquete;
            int end = Math.min(start + tamMaxPaquete, contenido.length());
            String fragmento = contenido.substring(start, end);
            Paquete paquete = new Paquete(identificador, i + 1, totalPaquetes, fragmento);
            paquetes.agregar(paquete);
        }
    }
}
