package com.TDA.Implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import com.TDA.TDALista;


public class ListaEnlazada<T> implements TDALista<T> {
    Nodo<T> cabeza;

    @Override // El override se tiene que poner para suplantar el generico de java
    public void agregar(T elem){
        Nodo<T> nuevo = new Nodo<T>(elem);
        
        if(cabeza == null){
            cabeza = nuevo;
            return;
        }

        Nodo<T> actual = cabeza;
        while(actual.getSiguiente() != null){
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > tamanio()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
        
        Nodo<T> nuevo = new Nodo<T>(elem);
        if (index == 0){
            nuevo.setSiguiente(cabeza);
            this.cabeza = nuevo;
            return;
        }

        Nodo<T> actual = cabeza;
        int i = 0;
        while(i < index - 1){
            actual = actual.getSiguiente();
            i++;
        }
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    @Override
    public T obtener(int index){
        if (index < 0 || index >= tamanio()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }

        Nodo<T> actual = cabeza;
        int i = 0;
        while(i < index){
            actual = actual.getSiguiente();
            i++;
        }
        return actual.getDato();
    }

    @Override
    public T sacarIndice(int index){
        if (index < 0 || index >= tamanio()){
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
        
        if (index == 0){
            T dato = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
            return dato;
        }

        Nodo<T> actual = cabeza;
        int i = 0;

        while(i < index - 1){
            actual = actual.getSiguiente();
            i++;
        }
        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        return dato;
    }

    @Override
    public T sacarElemento(T dato) {
        if (cabeza == null){
            throw new IndexOutOfBoundsException("No hay elementos en la lista");
        }
        
        if (cabeza.getDato().equals(dato)) {
            T nodo = cabeza.getDato();
            cabeza = cabeza.getSiguiente();    
            return nodo;

        }
    
        Nodo<T> actual = this.cabeza;
        
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null){
            throw new RuntimeException("Elemento no encontrado");
        }

        T nodo = actual.getSiguiente().getDato();
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        return nodo;
    }

    public T eliminar(int index){
        if (index < 0 || index >= tamanio()){
            return null;
        }
        
        if (index == 0){
            T dato = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
            return dato;
        }

        Nodo<T> actual = cabeza;
        int i = 0;

        while(i < index - 1){
            actual = actual.getSiguiente();
            i++;
        }
        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        return dato;        
    }

    public boolean eliminarElemento(T dato) {
        if (cabeza == null){
            return false;
        }
        
        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();    
            return true;

        }
    
        Nodo<T> actual = this.cabeza;
        
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null){
            return false;
        }

        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        return true;
    }

public boolean contiene(T elem){
    Nodo<T> actual = cabeza;

    while (actual != null){
        if (actual.getDato().equals(elem)){
            return true;
        }
        actual = actual.getSiguiente();
    }

    return false;
}


    public int indiceDe(T elem) {
        if (cabeza == null) {
            return -1;
        }

        Nodo<T> actual = cabeza;
        int i = 0;

        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return i;
            }
            actual = actual.getSiguiente();
            i++;
        }

        return -1;

    }

    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void ordenar(Comparator<T> comp) {
    if (cabeza == null) return;
    
    Nodo<T> actual = cabeza;
    
    while (actual != null) {
        Nodo<T> menor = actual;
        Nodo<T> temp = actual.getSiguiente();
    
        while (temp != null) {
            if (comp.compare(temp.getDato(), menor.getDato()) < 0) {
                menor = temp;
            }
            temp = temp.getSiguiente();
        }
    
        T aux = actual.getDato();
        actual.setDato(menor.getDato());
        menor.setDato(aux);
    
        actual = actual.getSiguiente();
        }
    }

    public int tamanio(){
        if(cabeza == null){
            return 0;
        }

        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null){
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public boolean esVacio(){
        if (cabeza == null){
            return true;
        }
        else{
            return false;
        }
    }

    public void vaciar(){
        this.cabeza = null;
    }
}
    

