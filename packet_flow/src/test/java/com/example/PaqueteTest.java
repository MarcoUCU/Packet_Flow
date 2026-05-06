package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class PaqueteTest {
    @Test
    public void constructorYGetters() {
        Paquete paquete = new Paquete("mensaje1", 1, 3, "ja");

        assertEquals("mensaje1", paquete.getIdMensaje());
        assertEquals(1, paquete.getNumSecuencia());
        assertEquals(3, paquete.getTotalPaquetes());
        assertEquals("ja", paquete.getFragmento());
    }

    @Test
    public void estadoInicialPendiente() {
        Paquete paquete = new Paquete("mensaje1", 1, 3, "ja");

        assertEquals(EstadoPaquete.PENDIENTE, paquete.getEstadoPaquete());
    }

    @Test
    public void setEstadoEnTransito() {
        Paquete paquete = new Paquete("mensaje", 1, 3, "ja");

        paquete.setEstado(EstadoPaquete.EN_TRANSITO);

        assertEquals(EstadoPaquete.EN_TRANSITO, paquete.getEstadoPaquete());
    }

    @Test
    public void setEstadoRecibido() {
        Paquete paquete = new Paquete("mensaje1", 1, 3, "ja");

        paquete.setEstado(EstadoPaquete.RECIBIDO);

        assertEquals(EstadoPaquete.RECIBIDO, paquete.getEstadoPaquete());
    }

    @Test
    public void toStringContieneDatos() {
        Paquete paquete = new Paquete("mensaje1", 1, 3, "ja");

        String texto = paquete.toString();

        assertTrue(texto.contains("mensaje1"));
        assertTrue(texto.contains("ja"));
        assertTrue(texto.contains("PENDIENTE"));
    }
}