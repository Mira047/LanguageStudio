package com.mira.languagestudio.core.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Consumer;

public class SerializableConsumer<T> implements Consumer<T>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Consumer<T> consumer;

    public SerializableConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(T t) {
        consumer.accept(t);
    }

    @Serial
    private Object writeReplace() {
        return new SerializationProxy<>(this);
    }

    private static class SerializationProxy<T> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private final Consumer<T> consumer;

        SerializationProxy(SerializableConsumer<T> sc) {
            this.consumer = sc.consumer;
        }

        @Serial
        private Object readResolve() {
            return new SerializableConsumer<>(consumer);
        }
    }
}