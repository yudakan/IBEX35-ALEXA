package com.yudakan.ibex35.handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {

    // retorna cert si la sol·licitud entrant és de tipus HelpIntent
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    // genera i retorna una resposta
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "No.";

        return input.getResponseBuilder()
                // text de resposta en veu
                .withSpeech(speechText)
                // text de resposta escrit
                .withSimpleCard("Ayuda", speechText)
                // text de resposta tardana
                .withReprompt("Bueeeeeeeeeeeeeno, puedes preguntarme que te muestre las empresas del ibex35, que te las ordene, la situacion...")
                .build();
    }
}