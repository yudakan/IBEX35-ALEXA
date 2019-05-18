package com.yudakan.ibex35.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

    // retorna cert si la sol·licitud entrant és de tipus LaunchRequest
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    // genera i retorna una resposta
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Cuán interesante, ignoraba que supieras de economía.";

        return input.getResponseBuilder()
            // text de resposta en veu
            .withSpeech(speechText)
            // text de resposta escrit
            .withSimpleCard("IBEX-35 SKILL", speechText)
            // text de resposta tardana
            .withReprompt("¿Pretendes que adivine tu pregunta?")
            .build();
    }
}