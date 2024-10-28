package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

public class OpenWebsiteAction implements LLMAction {

    @Override
    public String id() {
        return "OPEN_WEBSITE";
    }

    @Override
    public String description() {
        return "Opens a website with browser.";
    }

    @Override
    public void process(String value) {
        new RunOsCommandAction().process("\"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe\" \"" + value + "\"");
    }
}
