package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.StringContainer;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class StartGameIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("StartGameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String reply = StringContainer.START_MESSAGE;
       
        if(GameLogic.CATEGORY == null){
            //start select Category
            reply = StringContainer.REQUEST_CATEGORY;
        }
        if(GameLogic.DIFFICULTY == null){
            //start select Difficulty
            reply = StringContainer.REQUEST_DIFFICULTY;
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
