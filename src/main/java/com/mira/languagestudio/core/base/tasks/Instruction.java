package com.mira.languagestudio.core.base.tasks;

import com.mira.languagestudio.core.base.LanguageMemory;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * The {@code Instruction} record represents a single instruction of the language that can be executed.
 *
 * <p>This record contains a {@code Consumer} that represents the implementation of the instruction, which takes a {@code List} of {@code String} arguments and performs an action based on the instruction.
 *
 * <p>Instruction records can be appended to form a sequence of instructions to be executed.
 *
 * @since 1.0
 */
public record Instruction(BiConsumer<List<String>, LanguageMemory<?>> implementation) implements Serializable {

    /**
     * Appends another instruction to this one, creating a new instruction that will execute both instructions in sequence.
     *
     * @param instruction the instruction to be appended
     * @return a new instruction that will execute both instructions in sequence
     */
    public Instruction append(Instruction instruction) {
        return new Instruction((implementation.andThen(instruction.implementation)));
    }

    /**
     * Executes the instruction with the provided arguments.
     *
     * @param args the arguments to be passed to the instruction's implementation.
     */
    public void execute(LanguageMemory<?> memory, List<String> args) {
        implementation.accept(args, memory);
    }
}