package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NextQuestionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("NextQuestionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        String reply = getQuestionBySelectedCategory();

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    private String getQuestionBySelectedCategory() {
        //later on we have to Change this Method to get Access on The DB or the questions which are in GameLogic saved
        String question;
        switch (GameLogic.CATEGORY) {
            case POLITIK:
                question = "Wer war vor Angela Merkel Bundeskanzler?";
                break;
            case GESCHICHTE:
                question = "Wie nennt man ein männliches Schaf?";
                break;
            case GEOGRAFIE:
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
