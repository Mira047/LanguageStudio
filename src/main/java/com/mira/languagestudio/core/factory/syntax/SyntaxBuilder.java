package com.mira.languagestudio.core.factory.syntax;

import com.mira.languagestudio.external.interpreter.parser.Lexer;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A class for building a syntax for a language.
 *
 * <p>The syntax is represented as a string and can contain special regex expressions
 * that are used to test the syntax. </p>
 *
 * <p>It is used to define the syntax of a language, which is later used by
 * the interpreter to parse the input and determine the instructions to execute.</p>
 *
 * @since 1.0
 */
public class SyntaxBuilder {

    private final HashMap<Integer, Predicate<String>> regexMap = new HashMap<>();

    /**
     * Creates a new SyntaxBuilder with the provided description
     * @return the created SyntaxBuilder
     */
    public static SyntaxBuilder create() {
        return new SyntaxBuilder();
    }

    public SyntaxBuilder() {
    }

    /**
     * Add a regex to the SyntaxBuilder to match the key
     * @param key the index of the matching string
     * @param regex the regex to match the string
     */
    public SyntaxBuilder addRegex(int key, Predicate<String> regex) {
        regexMap.put(key, regex);

        return this;
    }

    /**
     * Add a regex to the SyntaxBuilder to match the keys between min and max
     * @param min the minimum index of the matching string
     * @param max the maximum index of the matching string
     * @param regex the regex to match the string
     */
    public SyntaxBuilder addRegex(int min, int max, Predicate<String> regex) {
        for(int i = min; i <= max; i++) {
            regexMap.put(i, regex);
        }

        return this;
    }

    /**
     * Add a regex to the SyntaxBuilder to match the keys with a specific length
     * @param offset an offset to add to the length
     * @param regex the regex to match the string
     */
    public SyntaxBuilder addRegexToLength(int offset, Predicate<String> regex) {
        regexMap.put(-1 - offset, regex);

        return this;
    }

    /**
     * Builds the {@code Syntax} object.
     * @return the created Syntax.
     */
    public Syntax build() {
        Lexer lexer = new Lexer(this);
        Syntax syntax = new Syntax();

        syntax.putMap(regexMap);


        return syntax;
    }
}
