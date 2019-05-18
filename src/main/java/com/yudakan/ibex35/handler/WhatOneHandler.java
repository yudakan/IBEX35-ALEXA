package com.yudakan.ibex35.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;

public class WhatOneHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("whatOne"));
    }

    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Pasa que eres pobre.";

        return input.getResponseBuilder()
            // text de resposta en veu
            .withSpeech(speechText)
            // text de resposta escrit
            .withSimpleCard("Lo siento", speechText)
            // text de resposta tardana
            .withReprompt("¿Me puedo ir ya?")
            .build();
    }
}