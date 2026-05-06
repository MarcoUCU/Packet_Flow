package com.TDA.Implementaciones;

import com.TDA.TDACola;

public class Cola<T> extends ListaEnlazada<T> implements TDACola<T> {
    Nodo<T> cola;   //ultimo
    int tamanio;
    //cabeza es el primer nodo, heredado de ListaEnlazada
    
    public void encolar(T elem){
        Nodo<T> nuevo = new Nodo<>(elem);
        
        if(cabeza == null){
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
         tamanio++;
    }

    public T frente(){
        if(cabeza == null){
            throw new RuntimeException("Cola vacia");
        }
        return cabeza.getDato();
    }

    public T desencolar() {
        if (cabeza == null) {
            return null; 
        }

        T valor = cabeza.getDato();
        cabeza = cabeza.getSiguiente();

        if(cabeza == null){
            cola = null;
        }
         tamanio--;
        return valor;
    }
}