package com.mira.languagestudio;

import com.mira.languagestudio.core.factory.syntax.Syntax;
import com.mira.languagestudio.core.factory.syntax.SyntaxBuilder;
import com.mira.languagestudio.core.util.Pair;
import com.mira.languagestudio.core.util.RegexConst;
import com.mira.languagestudio.external.interpreter.parser.Lexer;

import java.util.List;

public class TestLanguage {
    public static void main(String[] arg) {
        SyntaxBuilder syntaxBuilder = SyntaxBuilder.create()
                .addRegex(0, s -> s.matches("var"))
                .addRegex(1, s -> s.matches(RegexConst.IDENTIFIER))
                .addRegex(2, s -> s.matches("="))
                .addRegexToLength(0, s -> s.matches(";"));


        Syntax syntax = syntaxBuilder.build();

        Lexer lexer = new Lexer("var a = sus sus sus;");

        List<String> tokens = lexer.tokenize();

        System.out.println("SYNTAX TEST: " + syntax.test(tokens));
    }
}
