package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;
import com.akraml.actionai.StringUtils;

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
    public void process(String value) {
        try {
            Runtime.getRuntime().exec(StringUtils.splitCommand(value).toArray(new String[0]));
        } catch (Exception exception) {
            System.out.println("Error while executing os command: " + exception.getMessage());
        }
    }
}
