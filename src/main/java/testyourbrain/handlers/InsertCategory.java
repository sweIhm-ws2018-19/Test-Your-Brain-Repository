package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;

import java.util.Optional;

import testyourbrain.GameState;


import static com.amazon.ask.request.Predicates.intentName;

import testyourbrain.GameCategory;

public class InsertCategory implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertCategory"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Category").getValue();
        String optionalMessage = generateReply(answer);
        String debugInformation = "";
        
        if (GameLogic.DEBUGMODE) {
            debugInformation = "Du hast " + answer + " gewaehlt. ";
        }
        
        if(GameLogic.getDifficulty() != null){
            GameLogic.setGameState(GameState.GAME);
        }
        
        
        String reply = debugInformation + optionalMessage;
        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }

    public String generateReply(String answer) {
        boolean noMatchingCategory = false;
        String error = null;
        try {
            GameLogic.setCategory(GameCategory.valueOf(answer.toUpperCase()));
        } catch (Exception e) {
            noMatchingCategory = true;

        }
        System.out.println(GameLogic.getMatchingQuestions());
        String optionalMessage = "";
        if (noMatchingCategory) {
            optionalMessage = "Die eingegebene Kategorie entspricht keiner gueltigen Kategorie." + error;
            
        } else {
            optionalMessage = "Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".";
        }
        return optionalMessage;
    }
}
