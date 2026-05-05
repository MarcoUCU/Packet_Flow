package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.TDA.Implementaciones.Nodo;

public class NodoTest {

    @Test 
    public void constructorYgetDato(){
        Nodo<Integer> nodo = new Nodo<>(5);
        assertEquals(Integer.valueOf(5), nodo.getDato());
        assertNull(nodo.getSiguiente());
    }

    @Test
    public void testSetDato() {
        Nodo<String> nodo = new Nodo<>("prueba1");
        nodo.setDato("prueba2");
        assertEquals("prueba2", nodo.getDato());
    }

    @Test
    public void testSetSiguiente() {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);

        nodo1.setSiguiente(nodo2);

        assertEquals(nodo2, nodo1.getSiguiente());
        assertEquals(Integer.valueOf(2), nodo1.getSiguiente().getDato());
    }
}