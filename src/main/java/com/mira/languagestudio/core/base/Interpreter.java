package com.mira.languagestudio.core.base;


import com.mira.languagestudio.core.factory.settings.LanguageInfo;

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
    void run(String code);

    /**
     * Runs a single line of code using the interpreter.
     *
     * @param line the line of code to be executed by the interpreter.
     */
    void runLine(String line);

    /**
     * Runs a single line of code using the interpreter.
     *
     * @param line the line of code to be executed by the interpreter.
     * @param isDebug a flag indicating whether the interpreter should run in debug mode.
     */
    void runLine(String line, boolean isDebug);

    /**
     * Enables or disables the debug mode of the interpreter.
     *
     * @param debug {@code true} to enable debug mode, {@code false} to disable it.
     */
    void setDebug(boolean debug);

    /**
     * Returns whether the interpreter is currently in debug mode.
     *
     * @return {@code true} if the interpreter is in debug mode, {@code false} otherwise.
     */
    boolean isDebug();

    /**
     * Loads the specified {@code LanguageInfo} object into the interpreter.
     *
     * <p>This method will configure the interpreter to use the instructions and settings defined in the language represented by the {@code LanguageInfo} object.
     *
     * @param languageInfo the {@code LanguageInfo} object containing the instructions and settings of the language to load.
     */
    void load(LanguageInfo languageInfo);
}
