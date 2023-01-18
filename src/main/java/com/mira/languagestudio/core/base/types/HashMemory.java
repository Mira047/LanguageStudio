package com.mira.languagestudio.core.base.types;

import com.mira.languagestudio.core.base.LanguageMemory;
import com.mira.languagestudio.core.base.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private final List<Statement> statements = new ArrayList<>();

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

    @Override
    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void removeStatement(Statement statement) {
        statements.remove(statement);
    }

    @Override
    public List<Statement> getStatements(int index) {
        List<Statement> statements = new ArrayList<>();
        for (Statement statement : this.statements) {
            if (statement.getStart() == index) {
                statements.add(statement);
            }
        }
        return statements;
    }
}
