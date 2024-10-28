package com.akraml.actionai.actions;

import com.akraml.actionai.LLMAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteToFileAction implements LLMAction {

    @Override
    public String id() {
        return "WRITE_TO_FILE";
    }

    @Override
    public String description() {
        return "Gives a response like this 'File name;File content', other semicolons won't affect action process.";
    }

    @Override
    public void process(String value) {
        String[] values = value.split(";", 2);
        String fileName = values[0], fileContent = values[1];
        File file = new File(fileName);
        try {
            if (file.exists()) file.delete();
            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(fileContent);
            }
        } catch (Exception exception) {
            System.out.println("[Assistant] An error occurred when processing " + fileName + ": " + exception.getMessage());
        }
    }
}
