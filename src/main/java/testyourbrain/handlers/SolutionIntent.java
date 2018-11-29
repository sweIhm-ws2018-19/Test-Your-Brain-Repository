package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;

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
        String reply = checkAnswerByCategory(answer);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    private String checkAnswerByCategory(String answer) {
        String reply = "Falsch";
        final String right = "Richtig";
        switch (GameLogic.getCategory()) {
            case POLITIK:
                //Wer war vor Angela Merkel Bundeskanzler?
                if (answer.equals("gerhard schröder")) {
                    reply = right;
                }
                break;
            case GEOGRAPHIE:
                //Welcher ist der höchste Berg Deutschlands?
                if (answer.equals("zugspitze")) {
                    reply = right;
                }
                break;
            case GESCHICHTE:
                //Wie nennt man ein männliches Schaf?
                if (answer.equals("otto von bismark") || answer.equals("otto von bismarck")) {
                    reply = right;
                }
                break;
            case SONSTIGES:
                //Welches Kleidungsstück kaufen deutsche Frauen ihren Ehemännern am Liebsten?
                if (answer.equals("hemden")) {
                    reply = right;
                }
                break;
            default:
                reply = "Falsch";
                break;

        }
        return "Deine Antwort: " + answer + " ist " + reply + ".";
    }
}
