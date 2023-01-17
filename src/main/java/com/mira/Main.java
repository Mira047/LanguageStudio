package com.mira;

import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;

public class Main {
    public static void main(String[] argsc) {
        LanguageInfo info = LanguageBuilder.create()
                .withName("Test Language")
                .withIdentifier("test")
                .withVersion("0.0.1")
                .withAuthor("Mira")
                .withDescription("Test Programming Language for Language Studio")
                .register("test", args -> System.out.println("Test"))
                .register("test2", args -> System.out.println("Test2"))
                .register(args -> args.get(0).equals("var") && args.get(2).equals("="), args -> System.out.println("Variable"))
                .build();

        System.out.println(info.name());
        System.out.println(info.identifier());
        System.out.println(info.version());
        System.out.println(info.author());
        System.out.println(info.description());
    }
}