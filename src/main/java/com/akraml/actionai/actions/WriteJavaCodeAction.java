package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

public class WriteJavaCodeAction implements LLMAction {
    @Override
    public String id() {
        return "WRITE_JAVA_CODE";
    }

    @Override
    public String description() {
        return "Writes a Java code in this format: 'Class name.java;Code', other semicolons won't affect action process.";
    }

    @Override
    public void process(String value) {
        new WriteToFileAction().process(value);
    }
}
