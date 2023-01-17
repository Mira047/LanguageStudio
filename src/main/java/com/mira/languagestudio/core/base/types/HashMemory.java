package com.mira.languagestudio.core.base.types;

import com.mira.languagestudio.core.base.LanguageMemory;

import java.util.HashMap;

/**
 * HashMemory is an implementation of the Language interface that stores
 * variables and their values in a HashMap.
 *
 * <p>The class provides a thread-safe way of storing variables and their values
 * during the runtime of the language by using a HashMap.
 *
 * @since 1.0
 */
public class HashMemory implements LanguageMemory<String> {
    private final HashMap<String, Object> memory = new HashMap<>();

    /**
     * Creates a new HashMemory instance
     * @return the new HashMemory instance
     */
    public static HashMemory create() {
        return new HashMemory();
    }

    @Override
    public Object get(String key) {
        return memory.get(key);
    }

    @Override
    public void set(String key, Object value) {
        memory.put(key, value);
    }

    @Override
    public void remove(String key) {
        memory.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return memory.containsKey(key);
    }
}
