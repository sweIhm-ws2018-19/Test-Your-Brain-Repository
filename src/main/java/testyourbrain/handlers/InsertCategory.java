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

import main.java.testyourbrain.GameState;


import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameCategory;

public class InsertCategory implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertCategory")) && GameLogic.GAMESTATE == GameState.CONFIG;

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
        

        String optionalMessage ="";
        if(noMatchingCategory){
            optionalMessage = "Die eingegebene entspricht keiner gültigen Kategorie.";
        }else{
            optionalMessage= "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".";
        }

        return handlerInput.getResponseBuilder()
                .withSpeech("Du hast " + answer + " gewählt. " + optionalMessage)
                .withShouldEndSession(false)
                .build();
    }
}
