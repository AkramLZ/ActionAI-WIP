package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

public class RunOsCommandAction implements LLMAction {
    @Override
    public String id() {
        return "RUN_OS_COMMAND";
    }

    @Override
    public String description() {
        return "Returns a Windows OS Runtime command.";
    }

    @Override
    public Runnable process(String value) {
        return () -> {
            try {
                Runtime.getRuntime().exec(new String[] { value });
            } catch (Exception exception) {
                System.out.println("Error while executing os command: " + exception.getMessage());
            }
        };
    }
}
