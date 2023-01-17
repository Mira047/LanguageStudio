package com.mira.languagestudio.core.factory.syntax;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SyntaxBuilder {
    private String description = "";

    private final HashMap<String, Predicate<String>> regexMap = new HashMap<>();

    public static SyntaxBuilder create(String description) {
        return new SyntaxBuilder(description);
    }

    public SyntaxBuilder(String description) {
        this.description = description;
    }

    public SyntaxBuilder regexOf(String key, String regex) {
        regexMap.put(key, value -> value.matches(regex));

        return this;
    }

    public SyntaxBuilder functionOf(String key, Predicate<String> function) {
        regexMap.put(key, function);

        return this;
    }

    public SyntaxObject build() {
        SyntaxObject object = new SyntaxObject(description);

        object.putMap(regexMap);

        return object;
    }
}
