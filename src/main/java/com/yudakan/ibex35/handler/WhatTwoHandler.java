package com.yudakan.ibex35.handler;

import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;
import com.yudakan.ibex35.IbexConsulting;

public class WhatTwoHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("whatTwo"));
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