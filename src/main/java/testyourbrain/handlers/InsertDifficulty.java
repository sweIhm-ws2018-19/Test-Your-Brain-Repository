package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameDifficulty;

public class InsertDifficulty implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Schwierigkeit noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertDifficulty")) && (GameLogic.DIFFICULTY== null);

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

        String reply = "Du hast " + answer + " gewählt. Das entspricht " + GameLogic.DIFFICULTY + ". Wähle jetzt noch eine Kategorie.";
        
        if(noMatchingDifficulty){
            reply = "Deine Antwort: " + answer + " entspricht keinem verfügbaren Schwierigkeitsgrad.";
        }
        
        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
