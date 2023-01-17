package com.mira.languagestudio.core.base.memory;

public interface LanguageMemory<T> {
    Object get(T key);
    void set(T key, Object value);
    void remove(T key);
    boolean contains(T key);
}