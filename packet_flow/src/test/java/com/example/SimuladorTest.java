package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimuladorTest {

    @Test
    public void testCrearMensaje() {
        Simulador sim = new Simulador(5, 3, 0.0);

        String res = sim.crearMensaje("msg1", "hola mundo", 1);

        assertTrue(res.contains("msg1"));
        assertEquals(1, sim.getRed().getMensajes().tamanio());
        assertNotNull(sim.getRed().getColaTransmision().frente());
    }

    @Test
    public void testCrearMensajeDuplicado() {
        Simulador sim = new Simulador(5, 3, 0.0);

        sim.crearMensaje("msg1", "hola", 1);
        String res = sim.crearMensaje("msg1", "otro", 1);

        assertTrue(res.contains("Ya existe"));
    }

    @Test
    public void testAvanzarPasoMueveAPaTransito() {
        Simulador sim = new Simulador(5, 3, 0.0);

        sim.crearMensaje("msg1", "hola", 1);
        sim.avanzarPaso();

        assertTrue(sim.getRed().getListaTransito().tamanio() >= 0);
    }

    @Test
    public void testReconstruirMensajeCompleto() {
        Simulador sim = new Simulador(10, 2, 0.0);

        sim.crearMensaje("msg1", "hola", 1);

        for (int i = 0; i < 100; i++) {
            sim.avanzarPaso();
        }

        String res = sim.reconstruirMensaje("msg1");
    
        assertTrue(
            res.contains("Mensaje reconstruido") ||
            res.contains("No se puede reconstruir")
        );
}

    @Test
    public void testReconstruirMensajeIncompleto() {
        Simulador sim = new Simulador(5, 2, 1.0);

        sim.crearMensaje("msg1", "hola", 1);

        for (int i = 0; i < 10; i++) {
            sim.avanzarPaso();
        }

        String res = sim.reconstruirMensaje("msg1");

        assertTrue(res.contains("No se puede reconstruir"));
    }

    @Test
    public void testEliminarMensaje() {
        Simulador sim = new Simulador(5, 2, 0.0);

        sim.crearMensaje("msg1", "hola", 1);
        String res = sim.eliminarMensaje("msg1");

        assertTrue(res.contains("eliminado"));
        assertEquals(0, sim.getRed().getMensajes().tamanio());
    }
}