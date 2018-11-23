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

public class SolutionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("SolutionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Solution").getValue().toLowerCase();
        String reply = "Ich kann deine Antwort nicht verstehen";

        switch (GameLogic.CATEGORY) {
            case "politik":
                if (answer.equals("gerhard schröder")) {
                    reply = "Richtig";
                }
                break;
            case "natur":
                if (answer.equals("widder")) {
                    reply = "Richtig";
                }
                break;
            case "gebirge":
                if (answer.equals("zugspitze")) {
                    reply = "Richtig";
                }
                break;
            case "gewässer":
                if (answer.equals("moldau")) {
                    reply = "Richtig";
                }
                break;
            case "unnützes wissen":
                if (answer.equals("hemden")) {
                    reply = "Richtig";
                }
                break;
            default:
                reply = "Falsch";
                break;

        }

        return handlerInput.getResponseBuilder()
                .withSpeech("Deine Antwort: " + answer + " ist " + reply + ".")
                .withShouldEndSession(false)
                .build();
    }
}
