package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameDifficulty;

public class StartGameIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartGameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Ok lass uns Anfangen, wenn ich dir eine neue Frage stellen soll, sage einfach \"nächste Frage\".";
       
        if(GameLogic.CATEGORY == null){
            //start select Category
            speechText = "Zuerst musst du mir eine Kategorie nennen.";
        }
        if(GameLogic.DIFFICULTY == null){
            //start select Difficulty
            speechText = "Zuerst musst du mir eine Schwierigkeitsstufe nennen.";
        }

        return handlerInput.getResponseBuilder()
                .withSimpleCard("startGame", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
