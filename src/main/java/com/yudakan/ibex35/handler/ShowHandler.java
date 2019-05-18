package com.yudakan.ibex35.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class ShowHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("show"));
    }

    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Crítica, me aburro.";

        return input.getResponseBuilder()
                // text de resposta en veu
                .withSpeech(speechText)
                // text de resposta escrit
                .withSimpleCard("Uf...", speechText)
                // text de resposta tardana
                .withReprompt("¿Me puedo ir ya?")
                .build();
    }
}
