package com.mira.languagestudio.core.base;


import com.mira.languagestudio.core.factory.settings.LanguageInfo;

import java.util.List;

/**
 * The {@code Interpreter} interface defines the basic methods for interpreting code in a programming language.
 *
 * <p>An interpreter is a program that reads and executes code written in a programming language. The interpreter reads the code and executes it line by line.
 *
 * @since 1.0
 */
public interface Interpreter {
    /**
     * Runs the specified code using the interpreter.
     *
     * @param code the code to be executed by the interpreter.
     */
    void run(List<String> code);

    /**
     * Runs a single line of code using the interpreter.
     *
     * @param line the line of code to be executed by the interpreter.
     */
    void run(String line);

    /**
     * Loads the specified {@code LanguageInfo} object into the interpreter.
     *
     * <p>This method will configure the interpreter to use the instructions and settings defined in the language represented by the {@code LanguageInfo} object.
     *
     * @param languageInfo the {@code LanguageInfo} object containing the instructions and settings of the language to load.
     */
    void load(LanguageInfo languageInfo);

    /**
     * Returns the {@link LanguageMemory} currently in use by the interpreter.
     *
     * <p>This method can be used to access and manipulate the memory of the interpreter.
     *
     * @return the {@link LanguageMemory} currently in use by the interpreter
     */
    LanguageMemory<?> getMemory();
}
