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

        String reply = createchangeMessage(answer);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String createchangeMessage(String answer) {
        boolean noMatchingDifficulty = false;
        try{
            GameLogic.setDIFFICULTY(GameDifficulty.getBySynonym(answer));
        }catch(Exception e){
            noMatchingDifficulty = true;
        }
        if(GameLogic.DIFFICULTY == GameDifficulty.WRONG){
            GameLogic.setDIFFICULTY(GameDifficulty.EASY);
            noMatchingDifficulty = true;
        }
        String reply = "Du hast die Kategorie auf " + GameLogic.DIFFICULTY + " gewechselt.";

        if(noMatchingDifficulty){
            reply = "Deine Antwort: " + answer + " entspricht keinem verfuegbaren Schwierigkeitsgrad.";
        }
        return reply;
    }
}
