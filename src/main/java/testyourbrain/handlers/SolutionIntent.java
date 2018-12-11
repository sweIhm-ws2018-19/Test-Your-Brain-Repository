package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import testyourbrain.GameLogic;
import testyourbrain.GameState;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SolutionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        System.out.println("canHandle of Solution Intent, GameState: " + GameLogic.getGameState());
        return handlerInput.matches(intentName("SolutionIntent")) || GameLogic.getGameState() == GameState.GAME;

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        //get Key
        Optional<String > key = ((IntentRequest) request).getIntent().getSlots().keySet().stream().findFirst();
        //get Slot of Key
        Slot solutionSlot = ((IntentRequest) request).getIntent().getSlots().get(key.get());
        String answer = "";
        if (solutionSlot != null) {
            answer = solutionSlot.getValue().toLowerCase();
        }
        String reply = checkAnswerByCategory(answer);
        GameLogic.setGameState(GameState.CONFIG);
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
                if (answer.equals("gerhard schroeder")) {
                    reply = right;
                }
                break;
            case GEOGRAPHIE:
                //Welcher ist der hoechste Berg Deutschlands?
                if (answer.equals("zugspitze")) {
                    reply = right;
                }
                break;
            case GESCHICHTE:
                //Wie nennt man ein maennliches Schaf?
                if (answer.equals("otto von bismark") || answer.equals("otto von bismarck")) {
                    reply = right;
                }
                break;
            case SONSTIGES:
                //Welches Kleidungsstueck kaufen deutsche Frauen ihren Ehemaennern am Liebsten?
                if (answer.equals("hemden")) {
                    reply = right;
                }
                break;
            default:
                System.out.println("Game Category is: " + GameLogic.getCategory());
                reply = "Falsch";
                break;

        }
        return "Deine Antwort: " + answer + " ist " + reply + ".";
    }
}
