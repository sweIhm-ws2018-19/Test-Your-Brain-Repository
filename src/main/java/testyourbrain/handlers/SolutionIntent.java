package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Date;
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
                String answer = ((IntentRequest) request).getIntent().getSlots().get("Solution").getValue().toLowerCase();
                response = checkAnswer(answer);
                if(GameLogic.getAskedQuestions()>=10){
                    //End Of The Game
                    response += String.format(" Das wars, du hast %d Fragen beantwortet und davon waren %d richtig. Ich speichere dein Ergebnis ab.", GameLogic.getAskedQuestions(),GameLogic.getCorrectAnsweredQuestions());
                    GameLogic.saveScoreToDB(new Date().toString(),handlerInput);
                }
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
        for (String sutableOption : solution.split(",")) {
            if (sutableOption.toLowerCase().equals(answer.toLowerCase())) {
                result = true;
            }
        }

        String returningString = "Die gewaehlte Antwort " + answer + " ist " + (result ? "richtig" : "falsch");
        GameLogic.questionAnswered(result);
        if (!result) {
            returningString = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_01'/> " + returningString;
            returningString += " Die Richtige Antwort waere " + solution.replace(",", " oder ") + " gewesen.";
        }else{
            returningString = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_neutral_response_01'/> " + returningString;
        }
        return returningString;
    }
}
