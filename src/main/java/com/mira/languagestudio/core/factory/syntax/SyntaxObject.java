package com.mira.languagestudio.core.factory.syntax;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class SyntaxObject {
    private String description = "";

    private final HashMap<String, Predicate<String>> regexMap = new HashMap<>();

    protected SyntaxObject(String description) {
        this.description = description;
    }

    protected void putMap(HashMap<String, Predicate<String>> map) {
        regexMap.putAll(map);
    }

    public boolean test(List<String> tokens) {
        for(String key : regexMap.keySet()) {
            int index = Integer.parseInt(key.substring(2, key.length() - 1));

            if(!regexMap.get(key).test(tokens.get(index))) {
                System.out.println("Syntax error: " + tokens.get(index) + " is not a valid " + key + " in " + description);
                return false;
            }
        }

        return true;
    }
}
