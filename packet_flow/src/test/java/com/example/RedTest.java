package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class RedTest {

    @Test
    public void testConstructor() {
        Red red = new Red(5, 100);

        assertEquals(5, red.getCapacidadMax());
        assertEquals(100, red.getTamMaxPaquete());

        assertNotNull(red.getColaTransmision());
        assertNotNull(red.getListaTransito());
        assertNotNull(red.getBufferRecepcion());
        assertNotNull(red.getMensajes());
    }

    @Test
    public void testHayEspacioEnTransitoVacio() {
        Red red = new Red(3, 100);

        assertTrue(red.hayEspacioEnTransito());
    }

    @Test
    public void testHayEspacioEnTransitoLleno() {
        Red red = new Red(2, 100);

        // Simulamos paquetes (pueden ser null si no tenés clase Paquete aún)
        red.getListaTransito().agregar(null);
        red.getListaTransito().agregar(null);

        assertFalse(red.hayEspacioEnTransito());
    }

    @Test
    public void testHayEspacioEnTransitoParcial() {
        Red red = new Red(3, 100);

        red.getListaTransito().agregar(null);

        assertTrue(red.hayEspacioEnTransito());
    }
}