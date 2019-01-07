package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import testyourbrain.GameUtil;
import testyourbrain.Question;

public class NextQuestionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.

        return handlerInput.matches(intentName("NextQuestionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        System.out.println("NQ START: " + GameLogic.getMatchingQuestions());
        String reply = "Wähle erst eine Kategorie und Schwierigkeitsstufe aus.";
        if (GameLogic.getGameState() == GameState.ANSWER){
        reply = "Beantworte erst die gestellte Frage.";
        }
        if (GameLogic.getGameState() == GameState.GAME) {
            GameLogic.setGameState(GameState.ANSWER);
            reply = "";
            try {
              
                GameLogic.setCurrentQuestion(GameUtil.getNextQuestion());
            } catch (Exception e) {
                reply += e.getLocalizedMessage() + e.getMessage() + e.getCause() + e.getStackTrace();
            }
            Question currentQuestion = GameLogic.getCurrentQuestion();
            if (currentQuestion != null) {
                
                reply = generateReply();
                // reply += currentQuestion.getQuestion();
            } else {
                GameLogic.setGameState(GameState.GAME);
                reply += "Leider sind für die aktuellen Einstellungen keine weiteren Fragen verfügbar. Um die Einstellungen zu wechseln sage zum Beispiel \" Wechsele Schwierigkeit zu schwer\" . ";
            }
            System.out.println("NQ END: " + GameLogic.getMatchingQuestions());
        }
        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    private String generateReply() {
        List<String> solutions = Arrays.asList(GameLogic.getCurrentQuestion().getSuggestions().split(","));
        String reply = GameLogic.getCurrentQuestion().getQuestion();
        if(solutions.size() == 3){
        reply += "? Antwort A: " +  solutions.get(0);
        reply += " Antwort B: " + solutions.get(1);
        reply += " Antwort C: " +  solutions.get(2);
        }
        else{
            reply = "Liste der Antwortmöglichkeiten ungleich 3.";
            System.out.println(solutions);
        }
        
        return reply;
    }

}
