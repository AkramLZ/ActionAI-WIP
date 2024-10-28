package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

public class CompileAndRunJavaAction implements LLMAction {
    @Override
    public String id() {
        return "COMPILE_AND_RUN_JAVA";
    }

    @Override
    public String description() {
        return "Compiles and runs java code, response will contain File name without '.java' or '.class' at the end.";
    }

    @Override
    public void process(String value) {
        new RunOsCommandWithOutputAction().process("javac " + value + ".java");
        new RunOsCommandWithOutputAction().process("java " + value);
    }
}
