package com.TDA;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Define un Tipo de Dato Abstracto (TDA) Lista genérica.
 *
 * <p>Una lista permite almacenar elementos en una secuencia ordenada por posición,
 * admitiendo inserciones, accesos, eliminaciones, búsquedas y operaciones de ordenamiento.</p>
 *
 * <p>Las posiciones de los elementos se interpretan mediante índices enteros.
 * Salvo que la implementación indique lo contrario, se asume indexación basada en 0.</p>
 *
 * @param <T> el tipo de los elementos almacenados en la lista
 */
public interface TDALista<T> {


    void agregar(T elem); // Agrega un elemento al final
    void agregar(int index, T elem); // Agrega un elemento a la posicion indicada.

    T obtener(int index); // Busca un elemento segun el indice y lo de vuelve

    T sacarElemento(T elem); // Remueve un elemento comparando y lo devuelvo.
    T sacarIndice(int index); // Remuevo un elemento por indice y lo devuelvo

    T eliminar(int indice); // Elimino un elemento segun su indice
    boolean eliminarElemento(T elem); // Compara los elementos y si lo encuentra lo elimina y devuelve true, si no devuelve false

    boolean contiene(T elem); // Devuelve true si la lista contiene el elemento buscado, false si no
    int indiceDe(T elem); //Da el indice del elemento indicado, -1 si no se encuentra
    
    T buscar(Predicate<T> criterio); // Devuelve el primer elemento que coincide con el criterio dado por el parametro, null si no existe ninguno
    void ordenar(Comparator<T> comparator); // Ordena los elementos segun el comparador dado, el criterio esta dado por el objeto comparator y devuelve la lista ordenada
    // comparator.compare(a,b) si a es menor que b da menos de 0; si a=b da 0; si a > b da algo mayor a 0
    
    int tamanio(); // La cantidad de elementos que tiene la lista
    boolean esVacio(); // True si es vacia, falso si no
    void vaciar(); // Vacia la lista
}