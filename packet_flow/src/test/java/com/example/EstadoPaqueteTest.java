package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

public class EstadoPaqueteTest {
    @Test
    public void desdeNullDevuelvePendiente(){
        EstadoPaquete estado = EstadoPaquete.desde(null);

        assertEquals(EstadoPaquete.PENDIENTE, estado);
    }

    @Test
    public void desdeVacioDevuelvePendiente(){
        EstadoPaquete estado = EstadoPaquete.desde("");

        assertEquals(EstadoPaquete.PENDIENTE, estado);
    }

    @Test
    public void desdePendiente(){
        EstadoPaquete estado = EstadoPaquete.desde("PENDIENTE");

        assertEquals(EstadoPaquete.PENDIENTE, estado);
    }

    @Test
    public void desdeEnTransitoConEspacio(){
        EstadoPaquete estado = EstadoPaquete.desde("EN TRANSITO");

        assertEquals(EstadoPaquete.EN_TRANSITO, estado);
    }

    @Test
    public void desdeRecibidoMinuscula(){
        EstadoPaquete estado = EstadoPaquete.desde("recibido");

        assertEquals(EstadoPaquete.RECIBIDO, estado);
    }

    @Test
    public void desdePerdidoConEspacios() {
        EstadoPaquete estado = EstadoPaquete.desde(" perdido ");

        assertEquals(EstadoPaquete.PERDIDO, estado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDesdeEstadoInvalido() {
        EstadoPaquete.desde("otro estado");
    }
}
