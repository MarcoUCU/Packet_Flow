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
    public void testEnviarSiguienteMueveATransito() {
        Simulador sim = new Simulador(5, 3, 0.0);

        sim.crearMensaje("msg1", "hola", 1);
        sim.enviarSiguiente();

        assertTrue(sim.getRed().getListaTransito().tamanio() > 0);
    }

    @Test
    public void testReconstruirMensajeCompleto() {
        Simulador sim = new Simulador(10, 2, 0.0);

        sim.crearMensaje("msg1", "hola", 1);
        sim.enviarSiguiente();

        // Recibir todos los paquetes (prob. de pérdida = 0.0, llegan todos)
        while (sim.getRed().getListaTransito().tamanio() > 0) {
            sim.recibirPaquete();
        }

        String res = sim.reconstruirMensaje("msg1");

        assertTrue(res.contains("Mensaje reconstruido"));
    }

    @Test
    public void testReconstruirMensajeIncompleto() {
        Simulador sim = new Simulador(5, 2, 1.0);

        sim.crearMensaje("msg1", "hola", 1);
        sim.enviarSiguiente();

        // Recibir paquetes (prob. de pérdida = 1.0, se pierden todos)
        while (sim.getRed().getListaTransito().tamanio() > 0) {
            sim.recibirPaquete();
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