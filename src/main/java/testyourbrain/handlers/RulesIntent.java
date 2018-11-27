package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameDifficulty;
import main.java.testyourbrain.GameState;

public class RulesIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RulesIntent")) /*&& GameLogic.GAMESTATE == GameState.RULES*/;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().toString();
        
        String reply = "Ok. Dann nenne mir bitte eine Schwierigkeitsstufe.";
        if(answer.equals("ja")){
            reply = "Das sind die Rules!";
        }
        
        GameLogic.GAMESTATE = GameState.CONFIG;

        return handlerInput.getResponseBuilder()
                .withSpeech("startet")
                .withShouldEndSession(false)
                .build();
    }
}
