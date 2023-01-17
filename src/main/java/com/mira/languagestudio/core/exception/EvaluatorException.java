package com.mira.languagestudio.core.exception;

/**
 * EvaluatorException is thrown when an error occurs during the evaluation of a
 * program. This exception is thrown when a line is being evaluated and an error
 * occurs.
 *
 * @since 1.0
 */
public class EvaluatorException extends Exception {

    public EvaluatorException() {
        super();
    }

    public EvaluatorException(String message) {
        super(message);
    }
}

