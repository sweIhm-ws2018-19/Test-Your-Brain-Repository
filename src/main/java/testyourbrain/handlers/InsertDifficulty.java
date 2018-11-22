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

public class InsertDifficulty implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Schwierigkeit noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertDifficulty")) && (GameLogic.DIFFICULTY==0);

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Schwierigkeitsgrad").getValue().toLowerCase();

        Map<String,Integer> diffMap = new HashMap<>();
        diffMap.put("schwer",3);
        diffMap.put("mittel",2);
        diffMap.put("leicht",1);

        GameLogic.DIFFICULTY = diffMap.getOrDefault(answer,99);

        return handlerInput.getResponseBuilder()
                .withSpeech("Du hast " + answer + " gewählt. Das entspricht der Zahl " + GameLogic.DIFFICULTY + ". wähle jetzt noch eine Kategorie.")
                .withShouldEndSession(false)
                .build();
    }
}
