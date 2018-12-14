package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

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
        String reply = "Wähle erst eine Kategorie und Schwierigkeitsstufe aus.";

        if (GameLogic.getGameState() == GameState.GAME) {
            GameLogic.setGameState(GameState.ANSWER);
            reply = "";
            try {
              
                GameLogic.setCurrentQuestion(GameUtil.getNextQuestion());
                System.out.println("Current Quetsion:" + GameLogic.getCurrentQuestion());
            } catch (Exception e) {
                System.out.println("Exception while setting new question");
                reply += e.getLocalizedMessage() + e.getMessage() + e.getCause() + e.getStackTrace();
            }
            Question currentQuestion = GameLogic.getCurrentQuestion();
            if (currentQuestion != null) {
                reply += currentQuestion.getQuestion();
            } else {
                GameLogic.setGameState(GameState.GAME);
                reply += "Leider sind für die aktuellen Einstellungen keine weiteren Fragen verfügbar. Um die Einstellungen zu wechseln sage zum Beispiel \" Wechsele Schwierigkeit zu schwer\" . ";
            }
        }
        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

}
