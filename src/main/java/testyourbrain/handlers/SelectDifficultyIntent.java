package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;
import main.java.testyourbrain.GameDifficulty;
import main.java.testyourbrain.GameLogic;

public class SelectDifficultyIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("SelectDifficultyIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Schwierigkeitsgrad").getValue().toLowerCase();
        boolean noMatchingDifficulty = false;

        switch(answer){
            case "leicht":
                GameLogic.DIFFICULTY = GameDifficulty.EASY;
                break;
                
            case "mittel":
                GameLogic.DIFFICULTY = GameDifficulty.MEDIUM;
                break;
                
            case "schwer":
                GameLogic.DIFFICULTY = GameDifficulty.HARD;
                break;
                
            default:
                noMatchingDifficulty = true;
                break;
        }
        String reply = "Du hast die Kategorie auf " + GameLogic.DIFFICULTY + " gewechselt.";
        
        if(noMatchingDifficulty){
            reply = "Deine Antwort: " + answer + " entspricht keinem verfügbaren Schwierigkeitsgrad.";
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
