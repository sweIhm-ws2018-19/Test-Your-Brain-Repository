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
                //System.out.println(GameUtil.getNextQuestion());
                GameLogic.setCurrentQuestion(GameUtil.getNextQuestion());//getQuestionBySelectedCategory();
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

    private String getQuestionBySelectedCategory() {
        //later on we have to Change this Method to get Access on The DB or the questions which are in GameLogic saved
        String question;
        switch (GameLogic.getCategory()) {
            case POLITIK:
                question = "Wer war vor Angela Merkel Bundeskanzler?";
                break;
            case GESCHICHTE:
                question = "Von wem stammt das Zitat: \"Je weniger die Menschen davon wissen wie Würste oder Gesetze gemacht werden, desto besser schlafen sie.\" ?";
                break;
            case GEOGRAPHIE:
                question = "Welcher ist der höchste Berg Deutschlands?";
                break;
            case SONSTIGES:
                question = "Welches Kleidungsstück kaufen deutsche Frauen ihren Ehemännern am Liebsten?";
                break;
            default:
                question = "Ungültige Kategorie";
                break;
        }
        return question;
    }
}
