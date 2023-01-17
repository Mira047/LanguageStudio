package com.mira.languagestudio.core.factory.syntax;

import com.mira.languagestudio.core.util.RegexConst;
import com.mira.languagestudio.external.interpreter.parser.Lexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The {@code Syntax} class represents an object that can be used to store the regex and
 * test the syntax of a line of code.
 *
 * <p>The {@code Lexer} class is recommended for viewing how the tokens are created,
 * to be able to properly use custom syntaxes.</p>
 *
 * @since 1.0
 * @see Lexer
 * @see RegexConst
 */
public class Syntax {

    private final HashMap<Integer, Predicate<String>> regexMap = new HashMap<>();

    protected Syntax() {
    }

    protected void putMap(HashMap<Integer, Predicate<String>> map) {
        regexMap.putAll(map);
    }

    public boolean test(List<String> tokens) {
        for(int i = 0; i < tokens.size(); i++){
            if(regexMap.containsKey(i)){
                if(!regexMap.get(i).test(tokens.get(i))){
                    return false;
                }
            }
        }

        for(Map.Entry<Integer, Predicate<String>> entry : regexMap.entrySet()){
            if(entry.getKey() < 0){
                int index = tokens.size() + entry.getKey();

                if(index >= 0){
                    if(!entry.getValue().test(tokens.get(index))){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
