package testyourbrain.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import testyourbrain.GameState;
import testyourbrain.GameUtil;
import testyourbrain.StringContainer;

public class RulesIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        // invoke with "JA" , "Nein"
        return input.matches(intentName("RulesIntent")) && GameLogic.getGameState() == GameState.RULES;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        String answer = ((IntentRequest) request).getIntent().getSlots().get("ShowRules").getValue().toLowerCase();


        //ask for Difficulty
        String reply = StringContainer.REQUEST_DIFFICULTY;
        if (answer.equalsIgnoreCase("ja")) {
            //explain the Rules before
            reply = StringContainer.RULES + reply;
        }
        //set Gamestate to config to enable seting Difficulty and Category
        GameLogic.setGameState(GameState.CONFIG);

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
