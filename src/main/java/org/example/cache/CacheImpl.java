package org.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Cache de almacenamiento gen�rico
 * @param <T>
 * @param <K>
 * @since 1.0
 * @author Yahya El Hadri, Ra�l Fern�ndez, Samuel Cort�s, Javier Hern�ndez, Javier Ru�z, Alvaro Herrero.
 */

public class CacheImpl<K, T> implements Cache<K, T> {
    private static final Logger logger = LoggerFactory.getLogger(CacheImpl.class);
    private final LinkedHashMap<K, T> cache;
    private final int maxCapacity;

    /**
     * Constructor de la clase.
     * @autor Ra�l Fern�ndez, Javier Hern�ndez, Javier Ru�z, Samuel Cort�s, Yahya El Hadri, Alvaro Herrero.
     * @since 1.0
     * @param maxCapacity
     * @param logger
     * @return Tama�o de la c�che.
     */

    public CacheImpl(int maxCapacity, Logger logger) {
        this.maxCapacity = maxCapacity;
        this.cache = new LinkedHashMap<K, T>(maxCapacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, T> eldest) {
                return size() > maxCapacity;
            }
        };
    }

    /**
     * Obtiene el valor asociado a la clave especificada.
     * @author Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a buscar
     * @return Valor asociado a la clave
     */

    @Override
    public T get(K key) {
        logger.debug("Obteniendo el valor de la clave: {}", key);
        return cache.get(key);
    }

    /**
     * A�ade un valor a la c�che con la clave especificada.
     * @author Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a asociar
     * @param value Valor a almacenar
     */

    @Override
    public void put(K key, T value) {
        logger.debug("A�adiendo a cache el valor de la clave: {}", key);
        cache.put(key, value);
    }

    /**
     * Elimina el valor asociado a la clave especificada de la cache.
     * @author Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     * @param key Clave a eliminar
     */

    @Override
    public void remove(K key) {
        logger.debug("Eliminando de cache el valor de la clave: {}", key);
        cache.remove(key);
    }

    /**
     * Limpia la cache.
     * @author Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Samuel Cort�s.
     * @since 1.0
     */

    @Override
    public void clear() {
        logger.debug("Limpiando la cache");
        cache.clear();
    }

    /**
     * Obtiene el n�mero de valores almacenados en la c�che.
     * @author Ra�l Fern�ndez, Samuel Cort�s, Javier Hern�ndez, Alvaro Herrero, Javier Ru�z, Yahya El Hadri.
     * @since 1.0
     * @return N�mero de valores almacenados
     */

    @Override
    public int size() {
        logger.debug("Obteniendo el tama�o de la cache");
        return cache.size();
    }

    /**
     * Obtiene un conjunto de claves almacenadas en la c�che.
     * @author Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return Conjunto de claves almacenadas.
     */

    @Override
    public Set<K> keys() {
        logger.debug("Obteniendo las claves de la cache");
        return cache.keySet();
    }

    /**
     * Obtiene una colecci�n de valores almacenados en la c�che.
     * @author Ra�l Fern�ndez, Samuel Cort�s, Javier Hern�ndez, Alvaro Herrero, Javier Ru�z, Yahya El Hadri.
     * @since 1.0
     * @return Colecci�n de valores almacenados.
     */

    @Override
    public Collection<T> values() {
        logger.debug("Obteniendo los valores de la cache");
        return cache.values();
    }

    /**
     * Comprueba si la c�che contiene un valor asociado a la clave especificada.
     * @author Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @param key Clave a buscar
     * @return True si la cache contiene el valor, false en caso contrario
     */

    @Override
    public boolean containsKey(K key) {
        logger.debug("Comprobando si existe la clave en la cache: {}", key);
        return cache.containsKey(key);
    }

    /**
     * Comprueba si la cache contiene un valor espec�fico.
     * @author Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @param value Valor a buscar
     * @return True si la cache contiene el valor, false en caso contrario
     */

    @Override
    public boolean containsValue(T value) {
        logger.debug("Comprobando si existe el valor en la cache: {}", value);
        return cache.containsValue(value);
    }

    /**
     * Comprueba si la cache est� vac�a.
     * @author Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return True si la cache est� vac�a, false en caso contrario.
     */

    @Override
    public boolean isEmpty() {
        logger.debug("Comprobando si la cache est� vac�a");
        return cache.isEmpty();
    }

    /**
     * Comprueba si la cache no est� vac�a.
     * @author Alvaro Herrero, Samuel Cort�s, Javier Hern�ndez, Ra�l Fern�ndez, Yahya El Hadri, Javier Ru�z.
     * @since 1.0
     * @return True si la cache no est� vac�a, false en caso contrario.
     */

    @Override
    public boolean isNotEmpty() {
        logger.debug("Comprobando si la cache no est� vac�a");
        return !isEmpty();
    }
}
