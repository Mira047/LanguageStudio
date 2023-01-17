package com.mira.languagestudio.core.factory.settings;

import com.mira.languagestudio.core.base.memory.LanguageMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.internal.Instruction;
import com.mira.languagestudio.core.util.SerializablePredicate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * The {@code LanguageInfo} class contains all the information and settings of a language.
 *
 * <p>It is typically created by the {@code LanguageBuilder} class, and can be used to configure and run an interpreter for the language.
 *
 * @since 1.0
 */
public record LanguageInfo(String name, String identifier, String version, String author, String description,
                           LanguageBuilder.Registry registry) implements Serializable {

    /**
     * Creates a new instance of {@code LanguageInfo}.
     *
     * @param name        the name of the language.
     * @param identifier  the identifier of the language.
     * @param version     the version of the language.
     * @param author      the author of the language.
     * @param description the description of the language.
     * @param registry    the registry of the language.
     */
    public LanguageInfo {
    }

    /**
     * Returns the name of the language.
     *
     * @return the name of the language.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Returns the identifier of the language.
     *
     * @return the identifier of the language.
     */
    @Override
    public String identifier() {
        return identifier;
    }

    /**
     * Returns the version of the language.
     *
     * @return the version of the language.
     */
    @Override
    public String version() {
        return version;
    }

    /**
     * Returns the author of the language.
     *
     * @return the author of the language.
     */
    @Override
    public String author() {
        return author;
    }

    /**
     * Returns the description of the language.
     *
     * @return the description of the language.
     */
    @Override
    public String description() {
        return description;
    }

    /**
     * Returns the registry of instructions of the language.
     *
     * @return the registry of instructions of the language.
     */
    @Override
    public LanguageBuilder.Registry registry() {
        return registry;
    }

    /**
     * Exports the language information to a string.
     */
    public String export() {
        return "LanguageInfo{" +
                "name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Returns the instructions registered to this language.
     * @return the instructions registered to this language.
     */
    public HashMap<SerializablePredicate<List<String>>, Instruction> getInstructions() {
        return registry.getInstructions();
    }
}