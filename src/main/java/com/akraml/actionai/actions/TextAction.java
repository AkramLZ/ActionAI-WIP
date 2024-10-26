package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

public class TextAction implements LLMAction {
    @Override
    public String id() {
        return "TEXT";
    }

    @Override
    public String description() {
        return "Returns a regular text response.";
    }

    @Override
    public Runnable process(String value) {
        return () -> System.out.println("[Assistant] " + value);
    }
}
