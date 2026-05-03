package com.TDA.Implementaciones;

public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T dato){
        this.dato = dato;
        this.siguiente = null;
    }

    public Nodo<T> getSiguiente(){
        return this.siguiente;
    }

    public void setSiguiente(Nodo<T> nodo){
        this.siguiente = nodo;
    }

    public T getDato(){
        return this.dato;
    }

    public void setDato(T elem){
        this.dato = elem;
    }
}
