package com.mira.languagestudio;

import com.mira.languagestudio.core.base.memory.HashMemory;
import com.mira.languagestudio.core.factory.LanguageBuilder;
import com.mira.languagestudio.core.factory.settings.LanguageInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
            File f = new File("D:/Projects/LanguageStudio/src/test/resources/data/test/languageInfo.ser");
            System.out.println(Files.readAllLines(f.toPath()));
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(info);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(info.export());
    }
}
