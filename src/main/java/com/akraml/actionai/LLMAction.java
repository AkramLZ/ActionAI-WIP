package com.akraml.actionai;

public interface LLMAction {

    String id();
    String description();
    Runnable process(String value);

}
