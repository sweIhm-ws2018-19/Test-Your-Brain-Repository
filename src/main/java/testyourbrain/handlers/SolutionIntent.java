package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import testyourbrain.GameLogic;
import testyourbrain.GameState;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SolutionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        //catch Get Hints...
        //catch Cancel Or Stop...
        //catch Help...
        //... intentHandler
        return handlerInput.matches(intentName("SolutionIntent"));

    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String response = "Solution Intent triggered. Eventual Exception: ";
        String triggeredIntentStr = "";
        try{
        Request request = handlerInput.getRequestEnvelope().getRequest();
        Intent triggeredIntent = ((IntentRequest) request).getIntent();
        triggeredIntentStr = triggeredIntent.getName();
        if (triggeredIntent.getName().equals("AMAZON.FallbackIntent")) {
            return handlerInput.getResponseBuilder()
                    .withSpeech(" FallBackIntent Triggered. Deine Antwort war leider falsch. Richtig gewesen wäre: " + GameLogic.getCurrentQuestion().getSolution().replace(",", " oder "))
                    .withShouldEndSession(false)
                    .build();
        }
                
        if (triggeredIntent.getName().equals("SolutionIntent")) {
            String answer = triggeredIntent.getSlots().get("Solution").getValue();
            if(answer != null){
                response = checkAnswer(answer);
            } else {
                response = "Deine Antwort ist leider falsch. Richtig gewesen wäre: " + GameLogic.getCurrentQuestion().getSolution().replace(",", " oder ");
            }
//            System.out.println("sout answer");
//            return handlerInput.getResponseBuilder()
//                    .withSpeech("SolutionIntent:" + response)
//                    .withShouldEndSession(false)
//                    .build();
        }
        
        
        } catch(Exception e){
            response += e.getLocalizedMessage() + "Exception" + e.getMessage() + e.getCause() + e.getStackTrace();
        }
        //response += "trigger: " + triggeredIntentStr + "response: " + response;
        GameLogic.setGameState(GameState.GAME);
        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }

    private String checkAnswer(String answer) {
        boolean result = false;
        String solution = GameLogic.getCurrentQuestion().getSolution();
        System.out.println("Compare " + answer + " with the right answers: " + solution);
        for (String sutableOption : solution.split(",")) {
            if (sutableOption.equalsIgnoreCase(answer)) {
                result = true;
            }
            System.out.println("compare " + answer + " with " + sutableOption);
        }

        String returningString = "Die gewaehlte Antwort " + answer + " ist " + (result ? "richtig" : "falsch");
        if (!result) {
            returningString += " Die Richtige Antwort waere " + solution.replace(",", " oder ") + " gewesen.";
        }
        return returningString;
    }
}
