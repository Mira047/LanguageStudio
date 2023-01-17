package com.mira.languagestudio;

import com.mira.languagestudio.core.base.memory.HashMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class TestLanguage {
    public static void main(String[] arg) {
        LanguageInfo info = LanguageBuilder.create()
                .withIdentifier("test")
                .register("test", args -> System.out.println("Test"))
                .register("test2", args -> System.out.println("Test2"))
                .register(args -> args.get(0).equals("var") && args.get(2).equals("="), args -> System.out.println("Variable"))
                .memoryTypeOf(HashMemory.create())
//                .loadDefaults()
                .build();

        try {
            File f = new File(TestLanguage.class.getClassLoader().getResource("data/" + info.identifier() + "/language-info.json").toURI());

            List<String> lines = Files.readAllLines(f.toPath());
            for(String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
