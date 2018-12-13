package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameState;
import testyourbrain.Question;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class RepeatQuestionIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("RepeatQuestionIntent")) && GameLogic.getGameState() == GameState.GAME;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String reply = "Es gab ein fehler beim laden.";
        Question currentQuestion = GameLogic.getCurrentQuestion();
        if (currentQuestion != null)
            reply = currentQuestion.getQuestion();

        return handlerInput.getResponseBuilder()
                .withSpeech(reply)
                .withShouldEndSession(false)
                .build();
    }
}
