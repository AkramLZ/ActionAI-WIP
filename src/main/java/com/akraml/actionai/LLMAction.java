package com.akraml.actionai;

public interface LLMAction {

    String id();
    String description();
    void process(String value);

}
