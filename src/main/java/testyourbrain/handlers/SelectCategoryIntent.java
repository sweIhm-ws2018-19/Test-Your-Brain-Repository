package testyourbrain.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import testyourbrain.GameCategory;
import testyourbrain.GameState;

public class SelectCategoryIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("SelectCategoryIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String reply = "Bitte beantworte zunächst die Frage, dann kannst du die Kategorie ändern.";
        if (GameLogic.getGameState() != GameState.ANSWER) {

            Request request = handlerInput.getRequestEnvelope().getRequest();
            String answer = ((IntentRequest) request).getIntent().getSlots().get("Category").getValue();

            reply = generateReply(answer);

        }
        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String generateReply(String answer) {
        boolean noMatchingCategory = false;

        try {
            GameLogic.setCategory(GameCategory.valueOf(answer.toUpperCase()));
        } catch (Exception e) {
            noMatchingCategory = true;
        }

        String optionalMessage = "";
        if (noMatchingCategory) {
            optionalMessage = "Keine passende Kategorie verfügbar.";
        } else {
            optionalMessage = "Du hast die Kategorie auf " + answer + " gewechselt.";
        }
        return optionalMessage;
    }
}
