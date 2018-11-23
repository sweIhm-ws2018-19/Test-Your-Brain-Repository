package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.HashMap;
import java.util.Map;
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
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().toString();

        String question = "";
        
        switch (GameLogic.CATEGORY) {
            case "politik":
                question = "Wer war vor Angela Merkel Bundeskanzler?";
                break;
            case "natur":
                question = "Wie nennt man ein männliches Schaf?";
                break;
            case "gebirge":
                question = "Welcher ist der höchste Berg Deutschlands?";
                break;
            case "gewässer":
                question = "An welchem Fluss liegt die Stadt Prag?";
                break;
            case "unnützes wissen":
                question = "Welches Kleidungsstück kaufen deutsche Frauen ihren Ehemännern am Liebsten?";
                break;
            default:
                question = "Ungültige Kategorie";
                break;
    
    }

        return handlerInput.getResponseBuilder()
                .withSpeech(question)
                .withShouldEndSession(false)
                .build();
    }
}
