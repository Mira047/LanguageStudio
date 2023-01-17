package com.mira.languagestudio.core.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Predicate;

import java.io.Serializable;
import java.util.function.Predicate;

public class SerializablePredicate<T> implements Predicate<T>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Predicate<T> predicate;

    public SerializablePredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    @Serial
    private Object writeReplace() {
        return new SerializationProxy<>(this);
    }

    private static class SerializationProxy<T> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private final Predicate<T> predicate;

        SerializationProxy(SerializablePredicate<T> sp) {
            this.predicate = sp.predicate;
        }

        @Serial
        private Object readResolve() {
            return new SerializablePredicate<>(predicate);
        }
    }
}