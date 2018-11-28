package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameCategory;

public class SelectCategoryIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("SelectCategoryIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Category").getValue();

        String reply = createChangeMessage(answer);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String createChangeMessage(String answer) {
        boolean noMatchingCategory = false;

        //TODO Test THIS Way
        try{
            GameLogic.CATEGORY = GameCategory.valueOf(answer.toUpperCase());
        }catch(Exception e){
            noMatchingCategory = true;
        }

        String optionalMessage ="";
        if(noMatchingCategory){
            optionalMessage = "Keine passende Kategorie verfügbar.";
        }else{
            optionalMessage= "Du hast die Kategorie auf " + answer + " gewechselt.";
        }
        return optionalMessage;
    }
}
