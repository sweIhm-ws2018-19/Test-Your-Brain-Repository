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
        boolean noMatchingCategory = false;
        
        switch(answer){
            case "politik":
                GameLogic.CATEGORY = GameCategory.POLITIK;
                break;
                
            case "geographie":
                GameLogic.CATEGORY = GameCategory.GEOGRAPHIE;
                break;
               
            case "geschichte":
                GameLogic.CATEGORY = GameCategory.GESCHICHTE;
                break;
                
            case "kultur":
                GameLogic.CATEGORY = GameCategory.KULTUR;
                break;
                
            case "zitate":
                GameLogic.CATEGORY = GameCategory.ZITATE;
                break;
                
            default:
                noMatchingCategory = true;
                break;
        
        }
//        GameLogic.CATEGORY = answer;
        //maybe MAP answer <Synonym,Slot>
        String reply = null;
        if(!noMatchingCategory)
            reply = "Du hast die Kategorie auf " + answer + " gewechselt.";
        
        else reply = "Keine passende Kategorie verfügbar.";

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
