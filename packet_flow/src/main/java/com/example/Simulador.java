package com.example;
import java.util.Random;

import com.TDA.Implementaciones.Cola;
import com.TDA.Implementaciones.ListaEnlazada;

public class Simulador {
 
    private Red red;
    private Random random;
    private double probabilidadPerdida;
 
    public Simulador(int capacidadMax, int tamMaxPaquete, double probabilidadPerdida) {
        this.red = new Red(capacidadMax, tamMaxPaquete);
        this.random = new Random();
        this.probabilidadPerdida = probabilidadPerdida;
    }
 
    public Simulador(int capacidadMax, int tamMaxPaquete) {
        this(capacidadMax, tamMaxPaquete, 0.15);
    }
 
    // Crear mensaje, fragmentar y encolar paquetes para transmitir
    public String crearMensaje(String identificador, String contenido, int prioridad) {
        Mensaje existente = buscarMensaje(identificador);
        if (existente != null) {
            return "Ya existe un mensaje con el identificador: " + identificador;
        }
        
        // Se crea mensaje con los parametros de la red
        Mensaje mensaje = new Mensaje(identificador, contenido, prioridad);
        mensaje.fragmentar(red.getTamMaxPaquete());
        red.getMensajes().agregar(mensaje);
 
        // Se encolan todos los paquetes del mensaje
        ListaEnlazada<Paquete> paquetes = mensaje.getPaquetes();
        for (int i = 0; i < paquetes.tamanio(); i++) {
            red.getColaTransmision().encolar(paquetes.obtener(i));
        }

        
 
        return "Mensaje: " + identificador + " creado y fragmentado en "
                + paquetes.tamanio() + " paquetes.";
    }
 
    // Avanzar un paso de la simluacion implica: 1- Mover un paquete desde ColaTransmision a Transito (si hay espacio)
    // 2: Mover un paquete aleatorio de transito a recepcion
    
    public String avanzarPaso() {
        StringBuilder resultado = new StringBuilder();
 
        // 1
        resultado.append(pasarATransito());
 
        // 2
        resultado.append(pasarARecepcion());
 
        if (resultado.length() == 0) {
            return "No hay paquetes para procesar.";
        }
 
        return resultado.toString();
    }
 
    // Mueve desde ColaTransmision a tránsito, si hay lugar. (1)

    private String pasarATransito() {
        if (red.getColaTransmision().esVacio()) {
            return "";
        }
        if (!red.hayEspacioEnTransito()) {
            return "Red saturada: no hay espacio en tránsito.\n";
        }
 
        Paquete paquete = red.getColaTransmision().desencolar();
        paquete.setEstado(EstadoPaquete.EN_TRANSITO);
        red.getListaTransito().agregar(paquete);
 
        return paquete + " pasó a TRÁNSITO.\n";
    }
 
    // Mueve un paquete random de tránsito y lo mueve a recepción (si no se pierde)

    private String pasarARecepcion() {
        if (red.getListaTransito().esVacio()) {
            return "";
        }
 
        // Elige un paquete random (esto es lo que simula la pérdida de paquetes)
        int indice = random.nextInt(red.getListaTransito().tamanio());
        Paquete paquete = red.getListaTransito().eliminar(indice);
 
        // Decidir si se pierde o llega
        // random.nextDouble() da un numero entre 0.0 y 1.0, si cae abajo de el parámetro
        // probabilidadPerdida, el paquete se marca como perdido y no entra en el buffer de recepción
        if (random.nextDouble() < probabilidadPerdida) {
            paquete.setEstado(EstadoPaquete.PERDIDO);
            return "<- " + paquete + " se PERDIÓ en tránsito.\n";
        } else {
            paquete.setEstado(EstadoPaquete.RECIBIDO);
            red.getBufferRecepcion().agregar(paquete);
            return "<- " + paquete + " llegó al BUFFER DE RECEPCIÓN.\n";
        }
    }
 
    // Reconstruye (o no) un mensaje a partir de los paquetes recibidos
    public String reconstruirMensaje(String identificador) {
        Mensaje mensaje = buscarMensaje(identificador);
        if (mensaje == null) {
            return "No se encontró el mensaje: " + identificador;
        }
 
        if (!mensaje.estaCompleto()) {
            return "No se puede reconstruir el mensaje " + identificador + ". Se recibieron: "
                    + mensaje.cantRecibidos() + "/" + mensaje.getPaquetes().tamanio()
                    + " paquetes." + mensaje.cantPerdidos();
        }
 
        // Reordenar paquetes por número de secuencia y reconstruir
        ListaEnlazada<Paquete> paquetes = mensaje.getPaquetes();
        String[] fragmentos = new String[paquetes.tamanio()];
        for (int i = 0; i < paquetes.tamanio(); i++) {
            Paquete p = paquetes.obtener(i);
            fragmentos[p.getNumSecuencia()] = p.getFragmento();
        }
 
        StringBuilder reconstruido = new StringBuilder();
        for (String fragmento : fragmentos) {
            reconstruido.append(fragmento);
        }
 
        return "Mensaje reconstruido: \"" + reconstruido.toString() + "\"";
    }
 
    // Busca y elimina un mensaje con todos sus paquetes de la Red
    public String eliminarMensaje(String identificador) {
        Mensaje mensaje = buscarMensaje(identificador);
        if (mensaje == null) {
            return "No se encontró el mensaje: " + identificador;
        }
 
        // Eliminar paquetes de todas las estructuras de la red
        ListaEnlazada<Paquete> paquetes = mensaje.getPaquetes();
        for (int i = 0; i < paquetes.tamanio(); i++) {
            Paquete p = paquetes.obtener(i);
            red.getListaTransito().eliminarElemento(p);
            red.getBufferRecepcion().eliminarElemento(p);
        }
 
        // Eliminar paquetes de la cola de transmisión desencola todos los mensajes
        // y los encola de vuelta, pero, si tienen ese identificador, NO vuelven a ingresar
        eliminarDeCola(identificador);
 
        // Eliminar el mensaje de la lista
        red.getMensajes().eliminarElemento(mensaje);
 
        return "Mensaje \"" + identificador + "\" eliminado junto con todos sus paquetes.";
    }
 
    // Elimina los mensajes de la cola de transmision que tengan el id.
    // Lo que hace es crear otra cola e ir desencolando y encolando todos
    // aquellos que NO tengan ese id, los que sí, NO vuelven a ingresar.
    private void eliminarDeCola(String idMensaje) {
        int tamanio = red.getColaTransmision().tamanio();
        for (int i = 0; i < tamanio; i++) {
            Paquete p = red.getColaTransmision().desencolar();
            if (!p.getIdMensaje().equals(idMensaje)) {
                red.getColaTransmision().encolar(p);
            }
        }
    }
 
    // Consulta estado de la red
    public String consultarEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Estado de la Red ===\n");
        sb.append("Capacidad de tránsito: ")
                .append(red.getListaTransito().tamanio())
                .append("/").append(red.getCapacidadMax()).append("\n");
        sb.append("Paquetes en cola de transmisión: ")
                .append(red.getColaTransmision().tamanio()).append("\n");
        sb.append("Paquetes en tránsito: ")
                .append(red.getListaTransito().tamanio()).append("\n");
        sb.append("Paquetes en buffer de recepción: ")
                .append(red.getBufferRecepcion().tamanio()).append("\n");
 
        sb.append("\nEstado de los Mensajes:\n");
        ListaEnlazada<Mensaje> mensajes = red.getMensajes();
        if (mensajes.esVacio()) {
            sb.append("No hay mensajes en el sistema.\n");
        } else {
            for (int i = 0; i < mensajes.tamanio(); i++) {
                sb.append(mensajes.obtener(i).toString()).append("\n");
            }
        }
 
        return sb.toString();
    }
 
    // Listar todos los mensajes con su estado actual
    public String listarMensajes() {
        ListaEnlazada<Mensaje> mensajes = red.getMensajes();
        if (mensajes.esVacio()) {
            return "No hay mensajes en el sistema.";
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("Mensajes del Sistema:\n");
        for (int i = 0; i < mensajes.tamanio(); i++) {
            Mensaje m = mensajes.obtener(i);
            sb.append((i + 1)).append(". ").append(m.toString()).append("\n");
        }
        return sb.toString();
    }
 
    // Buscar mensaje por id
    public Mensaje buscarMensaje(String identificador) {
        ListaEnlazada<Mensaje> mensajes = red.getMensajes();
        for (int i = 0; i < mensajes.tamanio(); i++) {
            Mensaje m = mensajes.obtener(i);
            if (m.getIdentificador().equals(identificador)) {
                return m;
            }
        }
        return null;
    }
 
    public Red getRed() {
        return red;
    }
}