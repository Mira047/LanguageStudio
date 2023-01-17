package com.mira.languagestudio;

import com.mira.languagestudio.core.base.memory.HashMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.core.factory.syntax.SyntaxBuilder;
import com.mira.languagestudio.core.factory.syntax.SyntaxObject;
import com.mira.languagestudio.core.util.SerializableConsumer;
import com.mira.languagestudio.interpreter.InterpreterImpl;
import com.mira.languagestudio.interpreter.parser.Lexer;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestLanguage {
    public static void main(String[] arg) {
        SyntaxObject var_declaration = SyntaxBuilder.create("${0} ${1} ${2} ${3} ;")
                .regexOf("${0}", "int|float|double|char|String")
                .regexOf("${1}", "[a-zA-Z_][a-zA-Z0-9_]*")
                .regexOf("${2}", "=|+=|-=|\\*=|/=")
                .regexOf("${3}", "[a-zA-Z_][a-zA-Z0-9_]*")
                .build();

        Lexer lexer = new Lexer("int a = b;");

        System.out.println(var_declaration.test(lexer.tokenize()));
    }
}
