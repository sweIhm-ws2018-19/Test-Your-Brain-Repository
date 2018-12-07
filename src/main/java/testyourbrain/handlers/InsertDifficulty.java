package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import testyourbrain.GameDifficulty;
import testyourbrain.GameState;

public class InsertDifficulty implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Schwierigkeit noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertDifficulty")) && GameLogic.getGameState() == GameState.CONFIG;

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Schwierigkeitsgrad").getValue().toLowerCase();

        String reply = generateReply(answer);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String generateReply(String answer) {
        boolean noMatchingDifficulty = false;
        try {
            GameLogic.setDifficulty(GameDifficulty.getBySynonym(answer));
        } catch (Exception e) {
            noMatchingDifficulty = true;
        }
        if (GameLogic.getDifficulty() == GameDifficulty.WRONG) {
            GameLogic.setDifficulty(GameDifficulty.EASY);
            noMatchingDifficulty = true;
        }
        String debugInformation = "";
        if (GameLogic.DEBUGMODE) {
            debugInformation = "Du hast " + answer + " gewaehlt. Das entspricht " + GameLogic.getDifficulty() + ". ";
        }
        String reply = "Waehle jetzt noch eine Kategorie.";

        if (noMatchingDifficulty) {
            reply = "Deine Antwort: " + answer + " entspricht keinem verfuegbaren Schwierigkeitsgrad.";
        }
        return debugInformation + reply;
    }
}
