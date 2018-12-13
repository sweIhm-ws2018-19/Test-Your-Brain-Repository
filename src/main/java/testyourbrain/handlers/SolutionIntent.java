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
    String[] repeatUtterances = new String[]{"was",
        "noch mal",
        "wiederholung bitte",
        "Wiederholung",
        "Wiederhole",
        "Wiederhole die Frage"};

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //String answer = getAnswer(handlerInput);
        //System.out.println("Der SolutionIntent wurde mit der Eingabe " + answer + " aufgerufen \n wir befinden uns im Gamestate: " + GameLogic.getGameState());



            //solutionSlot.getValue()

        //catch Get Hints...
        //catch Cancel Or Stop...
        //catch Help...
        //... intentHandler
        return handlerInput.matches(intentName("SolutionIntent")) || GameLogic.getGameState() == GameState.GAME;

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String answer = getAnswer(handlerInput);
        String reply = "";
        int isSolutionInput = 0;
        for(String repeatQuestionUtterance : repeatUtterances){
            if(answer.equalsIgnoreCase(repeatQuestionUtterance))
                isSolutionInput = 1;
        }
        if(answer.equalsIgnoreCase("hinweis")){
            isSolutionInput=2;
        }

        System.out.println("SolutionInput is: " + isSolutionInput);
        if(isSolutionInput==0){
            reply = checkAnswer(answer);
            GameLogic.setGameState(GameState.CONFIG);
        }else if(isSolutionInput==1){
            reply = GameLogic.getCurrentQuestion().getQuestion();
        }else{
            reply = GameLogic.getCurrentQuestion().getHint();
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    private String getAnswer(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        //get Key
        Optional<String> key = ((IntentRequest) request).getIntent().getSlots().keySet().stream().findFirst();
        //get Slot of Key
        Slot solutionSlot = ((IntentRequest) request).getIntent().getSlots().get(key.get());
        String answer = "";
        if (solutionSlot != null) {
            answer = solutionSlot.getValue().toLowerCase();
        }
        return answer;
    }

    private String checkAnswer(String answer) {
        boolean result = false;
        String solution = GameLogic.getCurrentQuestion().getSolution();
        System.out.println("Compare " + answer + " with the right answers: " + solution);
        for (String sutableOption : solution.split(",")) {
            if (sutableOption.equalsIgnoreCase(answer)) {
                result = true;
            }
            System.out.println("compare " + answer + " with " + sutableOption);
        }

        String returningString = "Die gewaehlte Antwort " + answer + " ist " + (result ? "richtig" : "falsch");
        if (!result)
            returningString += " Die Richtige Antwort waere " + solution.replace(","," oder ") + " gewesen.";
        return returningString;
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
