package com.mira.languagestudio.core.base.tasks;

import com.mira.languagestudio.core.base.LanguageMemory;
import com.mira.languagestudio.core.base.Statement;

import java.util.HashMap;
import java.util.List;

public class IfStatement implements Statement {
    private final int start;
    private final int end;

    // Conditions for the if, respectively else if statements.
    private final HashMap<Integer, String> conditions = new HashMap<>();

    public IfStatement(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public void onStarted(List<String> args, LanguageMemory<?> memory) {

    }
}
