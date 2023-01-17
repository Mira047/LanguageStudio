package com.mira.languagestudio.core.base.memory;

import java.io.Serializable;

public interface LanguageMemory<T> extends Serializable {
    Object get(T key);
    void set(T key, Object value);
    void remove(T key);
    boolean contains(T key);
}