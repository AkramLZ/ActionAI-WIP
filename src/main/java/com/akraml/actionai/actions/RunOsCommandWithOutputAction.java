package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;
import com.akraml.actionai.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunOsCommandWithOutputAction implements LLMAction {
    @Override
    public String id() {
        return "RUN_OS_COMMAND_WITH_OUTPUT";
    }

    @Override
    public String description() {
        return "Runs a Windows OS Command, but displays its runtime output.";
    }

    @Override
    public void process(String value) {
        try {
            Process process = Runtime.getRuntime().exec(StringUtils.splitCommand(value).toArray(new String[0]));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception exception) {
            System.out.println("Error while executing os command: " + exception.getMessage());
        }
    }
}
