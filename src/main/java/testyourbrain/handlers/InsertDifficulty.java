package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import main.java.testyourbrain.GameDifficulty;
import main.java.testyourbrain.GameState;

public class InsertDifficulty implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Schwierigkeit noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertDifficulty")) && GameLogic.GAMESTATE == GameState.CONFIG;

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Schwierigkeitsgrad").getValue().toLowerCase();

        String reply = createReplyMessage(answer);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String createReplyMessage(String answer) {
        boolean noMatchingDifficulty = false;
        try {
            GameLogic.DIFFICULTY = GameDifficulty.getBySynonym(answer);
        } catch (Exception e) {
            noMatchingDifficulty = true;
        }
        if (GameLogic.DIFFICULTY == GameDifficulty.WRONG) {
            GameLogic.DIFFICULTY = GameDifficulty.EASY;
            noMatchingDifficulty = true;
        }
        String debugInformation = "";
        if (GameLogic.DEBUGMODE) {
            debugInformation = "Du hast " + answer + " gewählt. Das entspricht " + GameLogic.DIFFICULTY + ". ";
        }
        String reply = "Wähle jetzt noch eine Kategorie.";

        if (noMatchingDifficulty) {
            reply = "Deine Antwort: " + answer + " entspricht keinem verfügbaren Schwierigkeitsgrad.";
        }
        return debugInformation + reply;
    }
}
