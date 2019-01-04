package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SolutionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("SolutionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String response = "Wenn du bereits eine Kategorie und eine Schwierigkeitsstufe ausgewählt hast, sage  \"nächste Frage\" um dir eine neue Frage stellen zu lassen.";
        if (GameLogic.getGameState() == GameState.ANSWER) {
            try {
                Request request = handlerInput.getRequestEnvelope().getRequest();
                String answer = ((IntentRequest) request).getIntent().getSlots().get("ABC").getValue().toLowerCase();
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
        int index = 0;
        if (answer.equals("A") || answer.equals("a")) {
            index = 0;
        }
        if (answer.equals("B") || answer.equals("b")) {
            index = 1;
        }
        if (answer.equals("C") || answer.equals("c")) {
            index = 2;
        }
        String userAnswer = GameLogic.getCurrentQuestion().getSuggestions().split(",")[index];
        for (String sutableOption : solution.split(",")) {
            if (sutableOption.toLowerCase().equals(userAnswer.toLowerCase())) {
                result = true;
            }
        }

        String returningString = "Die gewaehlte Antwort " + answer + " ist " + (result ? "richtig." : "falsch.");
        if (!result) {
            returningString += " Die Richtige Antwort waere " + solution.split(",")[0] + " gewesen.";
        }
        return returningString;
    }
}
