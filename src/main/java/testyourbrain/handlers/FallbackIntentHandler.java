package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;
import testyourbrain.StringContainer;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

// 2018-July-09: AMAZON.FallackIntent is only currently available in en-US locale.
//              This handler will not be triggered except in that locale, so it can be
//
//   safely deployed for any locale.
public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        if (GameLogic.getGameState() == GameState.ANSWER) {
            return new SolutionIntent().handle(input);
        }
        Request request = input.getRequestEnvelope().getRequest();
        Intent intent = ((IntentRequest) request).getIntent();
        
        String reply;
        reply = StringContainer.UNKNOWN_MESSAGE;
        
        reply = "Bei der Verarbeitung der Anfrage ist leider ein Problem aufgetreten";

        return input.getResponseBuilder()
                .withSpeech(reply)
                .withSimpleCard("ColorSession", reply)
                //.withReprompt(reply)
                .build();
    }

}
