package com.example;

import static org.junit.Assert.*;
import org.junit.Test;
import com.TDA.Implementaciones.Cola;

public class ColaTest {

    @Test
    public void testEncolarFrente() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(10);
        cola.encolar(20);

        assertEquals(Integer.valueOf(10), cola.frente());
    }

    @Test
    public void testDesencolar() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(10);
        cola.encolar(20);

        Integer val = cola.desencolar();

        assertEquals(Integer.valueOf(10), val);
        assertEquals(Integer.valueOf(20), cola.frente());
    }

    @Test
    public void testFIFO() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        assertEquals(Integer.valueOf(1), cola.desencolar());
        assertEquals(Integer.valueOf(2), cola.desencolar());
        assertEquals(Integer.valueOf(3), cola.desencolar());
    }

    @Test
    public void testVacio() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(1);

        cola.desencolar();

        assertNull(cola.desencolar()); 
    }

    @Test(expected = RuntimeException.class)
    public void testFrenteVacia() {
        Cola<Integer> cola = new Cola<>();
        cola.frente(); 
    }

    @Test
    public void testColaSeReinicia() {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(1);
        cola.desencolar();

        cola.encolar(2);

        assertEquals(Integer.valueOf(2), cola.frente());
    }
}