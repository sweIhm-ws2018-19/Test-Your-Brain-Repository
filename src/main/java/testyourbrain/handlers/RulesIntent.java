package main.java.testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.testyourbrain.Game;
import main.java.testyourbrain.GameLogic;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import main.java.testyourbrain.GameState;
import main.java.testyourbrain.StringContainer;

public class RulesIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        // invoke with "JA" , "Nein"
        return input.matches(intentName("RulesIntent")) && GameLogic.GAMESTATE == GameState.RULES;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("ShowRules").getValue().toLowerCase();
        String debugInformation = "";
        //ask for Difficulty
        String reply = StringContainer.REQUEST_DIFFICULTY;
        if(answer.equalsIgnoreCase("ja")){
            //explain the Rules before
            reply = StringContainer.RULES + reply;
        }
        //set Gamestate to config to enable seting Difficulty and Category
        GameLogic.setGAMESTATE(GameState.CONFIG);
        if(GameLogic.DEBUGMODE){
            debugInformation = "Der Status der SpielLogic hat sich auf Config geaendert";
        }
        reply = debugInformation + reply;

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
