package com.yudakan.ibex35.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.yudakan.ibex35.IbexConsulting;

import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class ShowHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("show"));
    }

    public Optional<Response> handle(HandlerInput input) {

        String speechText = IbexConsulting.getList("all");

        return input.getResponseBuilder()
                // text de resposta en veu
                .withSpeech(speechText)
                // text de resposta escrit
                .withSimpleCard("Todas las empresas", speechText)
                // text de resposta tardana
                .withReprompt("¿Me puedo ir ya?")
                .build();
    }
}
