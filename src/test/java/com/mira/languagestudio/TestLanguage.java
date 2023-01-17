package com.mira.languagestudio;

import com.mira.languagestudio.core.base.memory.HashMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;
import com.mira.languagestudio.core.util.SerializableConsumer;
import com.mira.languagestudio.interpreter.InterpreterImpl;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestLanguage {
    public static void main(String[] arg) {
        LanguageBuilder builder = LanguageBuilder.create()
                .withName("Orbit")
                .withIdentifier("orbit")
                .withVersion("0.0.1")
                .withAuthor("Mira")
                .withDescription("A language for orbiting planets");

        builder.register("print", (List<String> args) -> System.out.println(args.get(1)))
                .register((List<String> args) -> (args.get(0).equals("var") && args.get(2).equals("=")),
                        (List<String> args) -> {
                            if(builder.getMemoryReference() instanceof HashMemory memory) {
                                if(!memory.contains(args.get(1))) {
                                    memory.set(args.get(1), args.get(3));

                                    System.out.println("Set " + args.get(1) + " to " + args.get(3));
                                } else {
                                    throw new RuntimeException("Variable already exists");
                                }
                            }
                        });

        LanguageInfo info = builder.build();

        InterpreterImpl interpreter = new InterpreterImpl();

        interpreter.load(info);

        // Now some test code
        interpreter.run("print \"hello world\"");
        interpreter.run("var test = \"hello world\"");
    }
}
