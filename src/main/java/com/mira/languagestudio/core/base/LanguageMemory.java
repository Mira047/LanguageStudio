package com.mira.languagestudio.core.base;

import java.io.Serializable;

/**
 * The {@code LanguageMemory} interface defines a memory storage for a programming language.
 *
 * <p>It provides a way to store, retrieve, and remove data using a key of type {@code T}.
 *
 * @param <T> the type of the key used to access the memory
 * @since 1.0
 */
public interface LanguageMemory<T> extends Serializable {
    /**
     * Retrieves the value stored in the memory associated with the specified key.
     *
     * @param key the key associated with the value to retrieve
     * @return the value stored in the memory, or {@code null} if no value is associated with the key
     */
    Object get(T key);
    /**
     * Associates the specified value with the specified key in the memory.
     *
     * @param key the key to store the value with
     * @param value the value to store
     */
    void set(T key, Object value);

    /**
     * Removes the value associated with the specified key from the memory.
     *
     * @param key the key associated with the value to remove
     */
    void remove(T key);

    /**
    * Returns {@code true} if the memory contains a value associated with the specified key.
    * @param key the key to check for
    */
    boolean contains(T key);
}