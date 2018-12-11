/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
 */
package testyourbrain.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.GameUtil;
import testyourbrain.StringContainer;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

import testyourbrain.GameState;
import testyourbrain.Question;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        GameLogic.setDifficulty(null);
        GameLogic.setCategory(null);

        //set initial Game state!
        GameLogic.setGameState(GameState.RULES);
        GameLogic.getAllQuestions().addAll(GameUtil.getAllQuestions(input));
        String reply = StringContainer.WELCOME_MESSAGE + StringContainer.SKILL_DESCRIPTION;

        
        if (GameLogic.DEBUGMODE) {
//            GameUtil.saveData(input,"test","Ich habe etwas in die DynamoDB geschrieben");
//            reply += GameUtil.getData(input,"test");
            GameUtil.getMatchingQuestions();
        }


        String repromptText = StringContainer.RULES_QUESTION;
        return input.getResponseBuilder()
                .withSimpleCard("ColorSession", reply)
                .withSpeech(reply)
                .withReprompt(repromptText)
                .build();
    }
}

