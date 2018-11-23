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

public class InsertCategory implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //true wenn Richtige Eingabe gemacht wurde UND die Kategorie noch nicht gesetzt wurde.
        return handlerInput.matches(intentName("InsertCategory")) /*&& (GameLogic.CATEGORY=="none")*/;

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("Category").getValue();

        GameLogic.CATEGORY = answer;
        //maybe MAP answer <Synonym,Slot>


        String optionalMessage ="";
        if(GameLogic.DIFFICULTY == 0){
            optionalMessage = "Auf welcher Schwierigkeitsstufe möchtest du spielen?";
        }else{
            optionalMessage= "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".";
        }

        return handlerInput.getResponseBuilder()
                .withSpeech("Du hast " + answer + " gewählt. " + optionalMessage)
                .withShouldEndSession(false)
                .build();
    }
}
