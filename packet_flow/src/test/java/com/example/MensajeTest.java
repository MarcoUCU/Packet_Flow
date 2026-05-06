package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class MensajeTest {
    @Test
    public void constructorYGetters() {
        Mensaje mensaje = new Mensaje("mensaje1", "hola", 2);

        assertEquals("mensaje1", mensaje.getIdentificador());
        assertEquals("hola", mensaje.getContenido());
        assertEquals(2, mensaje.getPrioridad());
        assertNotNull(mensaje.getPaquetes());
    }

    @Test
    public void fragmentarCantidadPaquetesExacta() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);

        assertEquals(3, mensaje.getPaquetes().tamanio());
    }

    @Test
    public void fragmentarCantidadPaquetesNoExacta() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdefg", 1);

        mensaje.fragmentar(3);

        assertEquals(3, mensaje.getPaquetes().tamanio());
    }

    @Test
    public void fragmentarContenidoPaquetes() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);

        assertEquals("ab", mensaje.getPaquetes().obtener(0).getFragmento());
        assertEquals("cd", mensaje.getPaquetes().obtener(1).getFragmento());
        assertEquals("ef", mensaje.getPaquetes().obtener(2).getFragmento());
    }

    @Test
    public void fragmentarDatosDePaquete() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);

        Paquete paquete = mensaje.getPaquetes().obtener(0);

        assertEquals("mensaje1", paquete.getIdMensaje());
        assertEquals(1, paquete.getNumSecuencia());
        assertEquals(3, paquete.getTotalPaquetes());
        assertEquals(EstadoPaquete.PENDIENTE, paquete.getEstadoPaquete());
    }

    @Test
    public void cantPendientesInicial() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);

        assertEquals(3, mensaje.cantPendientes());
    }

    @Test
    public void cantEnTransito() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);
        mensaje.getPaquetes().obtener(0).setEstado(EstadoPaquete.EN_TRANSITO);

        assertEquals(1, mensaje.cantEnTransito());
    }

    @Test
    public void cantRecibidos() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);
        mensaje.getPaquetes().obtener(0).setEstado(EstadoPaquete.RECIBIDO);
        mensaje.getPaquetes().obtener(1).setEstado(EstadoPaquete.RECIBIDO);

        assertEquals(2, mensaje.cantRecibidos());
    }

    @Test
    public void cantPerdidos() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);
        mensaje.getPaquetes().obtener(0).setEstado(EstadoPaquete.PERDIDO);

        assertEquals(1, mensaje.cantPerdidos());
    }

    @Test
    public void contarPorEstado() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);
        mensaje.getPaquetes().obtener(0).setEstado(EstadoPaquete.RECIBIDO);
        mensaje.getPaquetes().obtener(1).setEstado(EstadoPaquete.RECIBIDO);
        mensaje.getPaquetes().obtener(2).setEstado(EstadoPaquete.PERDIDO);

        assertEquals(2, mensaje.contarPorEstado(EstadoPaquete.RECIBIDO));
        assertEquals(1, mensaje.contarPorEstado(EstadoPaquete.PERDIDO));
    }

    @Test
    public void estaCompletoFalse() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);
        mensaje.getPaquetes().obtener(0).setEstado(EstadoPaquete.RECIBIDO);

        assertFalse(mensaje.estaCompleto());
    }

    @Test
    public void estaCompletoTrue() {
        Mensaje mensaje = new Mensaje("mensaje1", "abcdef", 1);

        mensaje.fragmentar(2);

        for (int i = 0; i < mensaje.getPaquetes().tamanio(); i++) {
            mensaje.getPaquetes().obtener(i).setEstado(EstadoPaquete.RECIBIDO);
        }

        assertTrue(mensaje.estaCompleto());
    }

    @Test
    public void toStringContieneDatos() {
        Mensaje mensaje = new Mensaje("mensaje1", "hola", 1);

        String texto = mensaje.toString();

        assertTrue(texto.contains("mensaje1"));
        assertTrue(texto.contains("hola"));
        assertTrue(texto.contains("1"));
    }
}