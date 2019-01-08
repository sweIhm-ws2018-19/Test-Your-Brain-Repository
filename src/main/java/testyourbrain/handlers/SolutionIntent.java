package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SolutionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("SolutionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String response = "Wenn du bereits eine Kategorie und eine Schwierigkeitsstufe ausgewählt hast, sage  \"nächste Frage\" um dir eine neue Frage stellen zu lassen.";
        System.out.println("GS: " + GameLogic.getGameState());
        if (GameLogic.getGameState() == GameState.ANSWER) {

            try {
                Request request = handlerInput.getRequestEnvelope().getRequest();
                String answer = "default";
                Map<String, Slot> slotMap = ((IntentRequest) request).getIntent().getSlots();
                Optional<Slot> slot = slotMap.values().stream().findFirst();
                if (slot.isPresent()) {
                    answer = slot.get().getValue().toLowerCase();
                    answer = "" + answer.charAt(0);
                }
                System.out.println(answer);
                response = checkAnswer(answer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (GameLogic.getNumberAskedQuestions() >= 10) {
                response += " Du hast nun 10 Fragen beantwortet, davon waren " + GameLogic.getNumberCorrectQuestions() + " richtig. Sage \"nächste Frage\""
                        + "um erneut 10 Fragen gestellt zu bekommen oder beende den Skill.";
                String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
                GameLogic.saveScoreToDB(date, handlerInput);
                GameLogic.setNumberAskedQuestions(0);
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
        System.out.println(userAnswer + " " + answer);
        System.out.println("antwortmöglichkeiten: " + solution);
        for (String suitableAnswer : solution.split(",")) {
            System.out.println("prüfe" + suitableAnswer.toLowerCase() + " mit " + userAnswer.toLowerCase());

            if (suitableAnswer.toLowerCase().equals(userAnswer.toLowerCase())) {
                result = true;
            }
        }

        String returningString = "Die gewaehlte Antwort " + answer + userAnswer + " ist " + (result ? "richtig." : "falsch.");
        if (!result) {
            returningString += " Die Richtige Antwort waere " + solution.split(",")[0] + " gewesen.";
        }

        if (result) {
            returningString = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_neutral_response_01'/> " + returningString;
        } else {
            returningString = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_negative_response_01'/> " + returningString;

        }

        GameLogic.countQuestions(result);
        return returningString;
    }
}
