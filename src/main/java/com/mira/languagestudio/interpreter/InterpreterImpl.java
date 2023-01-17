package com.mira.languagestudio.interpreter;

import com.mira.languagestudio.core.base.Interpreter;
import com.mira.languagestudio.core.factory.internal.Instruction;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.core.util.SerializablePredicate;
import com.mira.languagestudio.interpreter.parser.Lexer;

import java.util.List;

public class InterpreterImpl implements Interpreter {
    private LanguageInfo info;

    private final Lexer lexer = new Lexer(" ");

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

        for(SerializablePredicate<List<String>> predicate : info.registry().getInstructions().keySet()) {
            if(predicate.test(tokens)) {
                Instruction instruction = info.registry().getInstructions().get(predicate);

                instruction.execute(tokens);
            }
        }
    }

    @Override
    public void load(LanguageInfo languageInfo) {
        this.info = languageInfo;
    }
}
