package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class StartGameIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartGameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Ok lass uns Anfangen";

        if(GameLogic.CATEGORY.equals("none")){
            //start select Category
            speechText = "zuerst musst du mir eine Kategorie nennen.";
        }
        if(GameLogic.DIFFICULTY == 0){
            //start select Difficulty
            speechText = "zuerst musst du mir eine Schwirigkeitsstufe nennen.";
        }

        return handlerInput.getResponseBuilder()
                .withSimpleCard("startGame", speechText)
                .withSpeech(speechText)
                .build();
    }
}
