package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
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
        //catch Get Hints...
        //catch Cancel Or Stop...
        //catch Help...
        //... intentHandler
        return handlerInput.matches(intentName("SolutionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String response = "Wenn du bereits eine Kategorie und eine Schwierigkeitsstufe ausgewählt hast, sage  \"nächste Frage\" um dir eine neue Frage stellen zu lassen.";
        if (GameLogic.getGameState() == GameState.ANSWER) {
            try {
                Request request = handlerInput.getRequestEnvelope().getRequest();
                String answer = ((IntentRequest) request).getIntent().getSlots().get("Solution").getValue().toLowerCase();
                response = checkAnswer(answer);

            } catch (Exception e) {
                response += e.getLocalizedMessage() + "Exception" + e.getMessage() + e.getCause() + e.getStackTrace();
            }
        }
        GameLogic.setGameState(GameState.GAME);
        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }

    private String checkAnswer(String answer) {
        boolean result = false;
        String solution = GameLogic.getCurrentQuestion().getSolution();
        System.out.println("Compare " + answer + " with the right answers: " + solution);
        for (String sutableOption : solution.split(",")) {
            if (sutableOption.toLowerCase().equals(answer.toLowerCase())) {
                result = true;
                System.out.println("Solution: " + sutableOption.toLowerCase() + " Antwort: " + answer.toLowerCase() + "euqals: " + sutableOption.toLowerCase().equals(answer.toLowerCase()));
            }
            System.out.println("compare " + answer + " with " + sutableOption);
        }

        String returningString = "Die gewaehlte Antwort " + answer + " ist " + (result ? "richtig" : "falsch");
        if (!result) {
            returningString += " Die Richtige Antwort waere " + solution.replace(",", " oder ") + " gewesen.";
        }
        return returningString;
    }
}
