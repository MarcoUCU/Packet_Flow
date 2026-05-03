package com.TDA;

/**
 * Define un Tipo de Dato Abstracto (TDA) Cola genérica.
 *
 * <p>Una cola es una estructura de datos lineal que sigue la política
 * FIFO (First In, First Out)
 *
 * <p>Esta interfaz extiende  TDALista, por lo que además hereda las
 * operaciones generales de una lista. Sin embargo, las operaciones propias
 * de la cola modelan el acceso restringido a sus extremos: inserción al final
 * y extracción desde el frente.</p>
 * */
public interface TDACola<T> extends TDALista<T> {
    T frente(); // Retorna el elemento ubicado al frente de la cola, sin removerlo.
    void encolar(T dato); // Inserta un elemetno al final, retorna true si se agrego y false si no
    T desencolar(); // Saca y retorna el elemento que esta al frente de la cola, el siguiente elemento(si existe) va a ocupar el frente
}