package com.example;

import static org.junit.Assert.*;
import org.junit.Test;
import com.TDA.Implementaciones.ListaEnlazada;

public class ListaTest {

    @Test
    public void testAgregarYObtener() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(10);
        lista.agregar(20);

        assertEquals(Integer.valueOf(10), lista.obtener(0));
        assertEquals(Integer.valueOf(20), lista.obtener(1));
    }

    @Test
    public void testAgregarEnIndice() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(10);
        lista.agregar(30);

        lista.agregar(1, 20);

        assertEquals(Integer.valueOf(20), lista.obtener(1));
    }

    @Test
    public void testSacarIndice() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        Integer eliminado = lista.sacarIndice(1);

        assertEquals(Integer.valueOf(20), eliminado);
        assertEquals(Integer.valueOf(30), lista.obtener(1));
    }

    @Test
    public void testSacarElemento() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("A");
        lista.agregar("B");

        String eliminado = lista.sacarElemento("A");

        assertEquals("A", eliminado);
        assertEquals("B", lista.obtener(0));
    }

    @Test
    public void testContieneEIndice() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(5);
        lista.agregar(10);

        assertTrue(lista.contiene(10));
        assertEquals(1, lista.indiceDe(10));
        assertFalse(lista.contiene(99));
        assertEquals(-1, lista.indiceDe(99));
    }

    @Test
    public void testEliminarElementoBoolean() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(1);
        lista.agregar(2);

        assertTrue(lista.eliminarElemento(1));
        assertFalse(lista.contiene(1));
    }

    @Test
    public void testTamanoYVacio() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertTrue(lista.esVacio());

        lista.agregar(1);
        lista.agregar(2);

        assertEquals(2, lista.tamanio());
        assertFalse(lista.esVacio());
    }

    @Test
    public void testVaciar() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(1);
        lista.vaciar();

        assertTrue(lista.esVacio());
    }

    @Test
    public void testOrdenar() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);

        lista.ordenar(Integer::compareTo);

        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }
}