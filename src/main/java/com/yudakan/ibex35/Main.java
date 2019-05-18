package com.yudakan.ibex35;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.yudakan.ibex35.handler.*;

// La classe mare SkillStreamHandler s'encarrega de serialitzar
// i deserialitzar les sol•lictuds de Alexa.
public class Main extends SkillStreamHandler {

    // Aquest mètode crea una Skill estàndard amb els nostres
    // handlers de sol·licitud que hem creat anteriorment
    private static Skill getSkill() {
        return Skills.standard()
            .addRequestHandlers(
                new LaunchRequestHandler(),
                new CancelandStopIntentHandler(),
                new HelpIntentHandler(),
                new SessionEndedRequestHandler(),
                new ShowHandler(),
                new WhatOneHandler(),
                new WhatTwoHandler())
            .build();
    }

    public Main() {
        // Crida al constructor pare passant-li la Skill creada
        // en el mètode estàtic getSkill()
        super(getSkill());
    }
}