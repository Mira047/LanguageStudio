package com.mira.languagestudio.core.base;

import com.mira.languagestudio.core.base.LanguageMemory;

import java.util.List;

/**
 * The {@code Statement} interface represents an object that can keep track of
 * instructions which take multiple lines of code, such as loops and conditionals.
 *
 * <p>The {@code Statement} interface has 3 listener methods that can be used to
 * listen for the start, end and lifetime (every line) of the statement.</p>
 *
 * @since 1.0
 */
public interface Statement {
    /**
    Get the starting point of this statement.
    @return The starting point of this statement.
    */
    int getStart();

    /**
     Get the ending point of this statement.
     @return The ending point of this statement.
     */
    int getEnd();

    /**
     A method that gets called when this statement is started.
     @param memory The Language Memory used in this statement.
     */
    default void onStarted(List<String> args, LanguageMemory<?> memory) {}

    /**
     A method that gets called when this statement is finished.
     @param memory The Language Memory used in this statement.
     */
    default void onFinished(List<String> args, LanguageMemory<?> memory) {}

    /**

     A method that gets called when this statement is running. (Every line)
     @param memory The Language Memory used in this statement.
     */
    default void onRun(List<String> args, LanguageMemory<?> memory) {}
}
