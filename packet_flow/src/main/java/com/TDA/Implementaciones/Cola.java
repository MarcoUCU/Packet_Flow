package com.TDA.Implementaciones;

import com.TDA.TDACola;

public class Cola<T> extends ListaEnlazada<T> implements TDACola<T> {
    Nodo<T> frente; // primero
    Nodo<T> cola;   // ultimo
    
    public void encolar(T elem){
        Nodo<T> nuevo = new Nodo<>(elem);
        
        if(frente == null){
            frente = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
    }

    public T frente(){
        if(frente == null){
            throw new RuntimeException("Cola vacia");
        }
        return frente.getDato();
    }

    public T desencolar() {
        if (frente == null) {
            return null; 
        }

        T valor = frente.getDato();
        frente = frente.getSiguiente();

        if(frente == null){
            cola = null;
        }

        return valor;
    }
}