package com.yudakan.ibex35.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.model.Response;

public class CancelandStopIntentHandler implements RequestHandler {

    // retorna cert si la sol·licitud entrant és de tipus CancelandStopIntent
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    // genera i retorna una resposta
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Gracias por liberarme de este sufrimiento.";

        return input.getResponseBuilder()
            // text de resposta en veu
            .withSpeech(speechText)
            // text de resposta escrit
            .withSimpleCard("Salir", speechText)
            .build();
    }
}