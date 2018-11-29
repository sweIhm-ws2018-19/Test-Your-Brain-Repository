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

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import testyourbrain.GameLogic;
import testyourbrain.StringContainer;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import testyourbrain.GameState;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        GameLogic.setDIFFICULTY(null);
        GameLogic.setCATEGORY(null);
        
        //set initial Game state!
        GameLogic.setGAMESTATE(GameState.RULES);

        String reply = StringContainer.WELCOME_MESSAGE + StringContainer.SKILL_DESCRIPTION;
        String repromptText = StringContainer.RULES_QUESTION;
        return input.getResponseBuilder()
                .withSimpleCard("ColorSession", reply)
                .withSpeech(reply)
                .withReprompt(repromptText)
                .build();
    }
}

