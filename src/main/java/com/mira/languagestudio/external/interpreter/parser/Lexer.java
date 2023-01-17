package com.mira.languagestudio.external.interpreter.parser;

import com.mira.languagestudio.core.factory.syntax.SyntaxBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Lexer} class is used to split a line of code into tokens.
 *
 * <p>It also has additional uses. For example, putting the tokens from
 * a string (That begin with a certain character) into a string</p>
 */
public class Lexer {
    public static final char EOF = '\0';

    private String input;
    private int p = 0;
    private char c;

    private final List<String> tokens = new ArrayList<>();

    public Lexer(String input) {
        this.input = input;
        c = input.charAt(p);
    }

    public Lexer(SyntaxBuilder builder) {
        this(builder.toString());
    }

    /**
     * Resets the lexer to a new input.
     * @param input the new input
     */
    public void reset(String input) {
        this.input = input;

        p = 0;
        c = input.charAt(p);

        tokens.clear();
    }

    /**
     * Tokenizes the string and then returns the tokens.
     * @return the tokens
     */
    public List<String> tokenize() {
        while(c != '\0') {
            tokens.add(nextToken());
        }

        for(int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            switch (token) {
                case "+", "-" -> {
                    if (i + 1 < tokens.size()) {
                        String nextToken = tokens.get(i + 1);

                        if (nextToken.equals("=")) {
                            tokens.set(i, token + "=");
                            tokens.remove(i + 1);
                        } else if (nextToken.equals(token)) {
                            tokens.set(i, token + token);
                            tokens.remove(i + 1);
                        }
                    }
                }
                case "*", "/", "%", "!", "=", ">", "<" -> {
                    if (i + 1 < tokens.size()) {
                        String nextToken = tokens.get(i + 1);

                        if (nextToken.equals("=")) {
                            tokens.set(i, token + "=");
                            tokens.remove(i + 1);
                        }
                    }
                }
                case "|", "&" -> {
                    if (i + 1 < tokens.size()) {
                        String nextToken = tokens.get(i + 1);

                        if (nextToken.equals(token)) {
                            tokens.set(i, token + token);
                            tokens.remove(i + 1);
                        }
                    }
                }
            }
        }

        return tokens;
    }


    private void next() {
        p++;

        if (p >= input.length()) {
            c = EOF;
        } else {
            c = input.charAt(p);
        }
    }

    /**
     * Returns the next token. Keep in mind, this method should not be used, unless you know what you are doing.
     * @return the next token
     */
    public String nextToken() {
        if(c == ' ' || c == '\t' || c == '\r') {
            WS();
            return nextToken();
        } else if(Character.isDigit(c)) {
            return INT();
        } else if(Character.isLetter(c)) {
            return ID();
        } else if(c == '"') {
            return STRING();
        } else {
            next();
            return String.valueOf(input.charAt(p-1));
        }
    }

    void WS() {
        while (c == ' ' || c == '\t' || c == '\r') {
            next();
        }
    }

    String ID() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(c);
            next();
        } while (Character.isLetter(c)||Character.isDigit(c)||c=='_'||c=='$'||c=='#'||c=='-');

        return sb.toString();
    }

    String INT() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(c);
            next();
        } while (Character.isDigit(c));

        return sb.toString();
    }

    String STRING() {
        StringBuilder sb = new StringBuilder();

        try {
            do {
                sb.append(c);
                next();
            } while (c != '"' || input.charAt(p - 1) == '\\');
            sb.append(c);

            next();
        } catch (Exception ignored) {} // This is a really awful way to deal with this, but who cares?

        return sb.toString();
    }
}