package com.mira.languagestudio;

import com.mira.languagestudio.core.base.types.HashMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.core.factory.syntax.Syntax;
import com.mira.languagestudio.core.factory.syntax.SyntaxBuilder;
import com.mira.languagestudio.core.util.Pair;
import com.mira.languagestudio.core.util.RegexConst;
import com.mira.languagestudio.external.interpreter.InterpreterImpl;
import com.mira.languagestudio.external.interpreter.parser.Lexer;

import java.util.List;

public class TestLanguage {
    public static void main(String[] arg) {
        // Creating a new syntax which takes care of declaring variables (var x = 5)
        Syntax varSyntax = SyntaxBuilder.create()
                .addRegex(0, s -> s.matches("int"))
                .addRegex(1, s -> s.matches(RegexConst.IDENTIFIER))
                .addRegex(2, s -> s.matches("="))
                .build();

        // Now time to create a new programming language with the syntax we just created
        LanguageInfo languageInfo = LanguageBuilder.create()
                .withName("Test Language")
                .withVersion("1.0")
                .withAuthor("Mira")
                .withDescription("A test language for LanguageStudio")
                .register(varSyntax, (tokens, memory) -> {
                    if(memory instanceof HashMemory hashMemory) {
                        hashMemory.set(tokens.get(1), Integer.parseInt(tokens.get(3)));

                        System.out.println("Set variable " + tokens.get(1) + " to " + tokens.get(3));
                    }
                })
                .build();

        // Now we can create a new interpreter instance and run some code
        InterpreterImpl interpreter = new InterpreterImpl();
        interpreter.load(languageInfo);

        interpreter.run("int x = 5");
    }
}
