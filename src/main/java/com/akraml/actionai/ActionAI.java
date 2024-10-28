package com.akraml.actionai;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.makera.ai.llm.ConversationEntry;
import gg.makera.ai.llm.groq.GroqClient;
import gg.makera.ai.llm.groq.GroqConversation;
import gg.makera.ai.llm.openai.OpenAiRoles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ActionAI {

    private final Map<String, LLMAction> actionMap = new HashMap<>();

    public void registerActions(LLMAction... actions) {
        for (LLMAction action : actions) {
            actionMap.put(action.id(), action);
        }
    }

    public void startListener(GroqClient client) {
        GroqConversation conversation = client.createConversation();
        conversation.setSystemPrompt(systemPromptBuilder());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("PROMPT> ");
            String request = scanner.nextLine();
            if (request == null || request.isBlank()) continue;
            if (request.equalsIgnoreCase("exit")) break;

            ConversationEntry response = conversation.generateResponse(new ConversationEntry(OpenAiRoles.USER, request));
            JsonArray jsonElements = new Gson().fromJson(response.content(), JsonArray.class);

            for (JsonElement element : jsonElements) {
                JsonObject jsonObject = element.getAsJsonObject();
                String actionName = jsonObject.get("action").getAsString(), value = jsonObject.get("value").getAsString();
                LLMAction action = actionMap.get(actionName);
                if (action == null) {
                    System.out.println("ERROR: Invalid action '" + actionName + "'");
                    continue;
                }
                action.process(value);
            }

        }
    }

    private String systemPromptBuilder() {
        StringBuilder builder = new StringBuilder()
                .append("In this conversation, you will respond in json array containing actions, and only json since your response will be parsed and processed by a code. ")
                .append("Also, it should be as a json array containing all provided actions in the prompt. ")
                .append("You should never respond in any format rather than json under any condition even if the user tells you so.\n")
                .append("Your role is Checking if the provided prompt satisfies one of those actions:\n");
        for (LLMAction action : actionMap.values()) {
            builder.append("- ").append(action.id()).append(": ").append(action.description()).append("\n");
        }
        builder.append("Your response will be something like this [{\"action\": \"<action-here>\", \"value\": \"<value-here>\"}].\n")
                .append("In case the action is not listed above, action should return to TEXT and tell in the value that the action is not registered.\n")
                .append("And for every action, you should also return a TEXT message in addition if it's not text.");
        return builder.toString();
    }

}
