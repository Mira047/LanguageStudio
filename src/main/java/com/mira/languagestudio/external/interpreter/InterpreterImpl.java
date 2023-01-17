package com.mira.languagestudio.external.interpreter;

import com.mira.languagestudio.core.base.Interpreter;
import com.mira.languagestudio.core.base.LanguageMemory;
import com.mira.languagestudio.core.base.tasks.Instruction;
import com.mira.languagestudio.core.base.types.HashMemory;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.external.interpreter.parser.Lexer;

import java.util.List;
import java.util.function.Predicate;

public class InterpreterImpl implements Interpreter {
    private LanguageInfo info;

    private final Lexer lexer = new Lexer(" ");

    private final LanguageMemory<String> memory = new HashMemory();

    @Override
    public void run(List<String> code) {
        for(int i = 1; i <= code.size(); i++) {
            run(code.get(i));
        }
    }

    @Override
    public void run(String line) {
        lexer.reset(line);

        List<String> tokens = lexer.tokenize();

        for(Predicate<List<String>> predicate : info.registry().getInstructions().keySet()) {
            if(predicate.test(tokens)) {
                Instruction instruction = info.registry().getInstructions().get(predicate);

                instruction.execute(memory, tokens);
            }
        }
    }

    @Override
    public void load(LanguageInfo languageInfo) {
        this.info = languageInfo;
    }

    @Override
    public LanguageMemory<?> getMemory() {
        return memory;
    }
}
