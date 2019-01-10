/*
 * Copyright 2018 wiesbob.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package testyourbrain.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import testyourbrain.GameLogic;
import testyourbrain.GameUtil;

/**
 *
 * @author wiesbob
 */
public class LastScoresIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("LastScoresIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String response = "Deine letzten Punkte: ";
        try {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            ArrayList<HashMap<String, Integer>> score = (ArrayList<HashMap<String, Integer>>) persistentAttributes.get("Score");

            ListIterator li = score.listIterator(score.size());
            int elementCount = 0;
            while (li.hasPrevious() && elementCount < 3) {
                HashMap<String, Integer> element = (HashMap<String, Integer>) li.previous();
                response += "Am " + element.keySet().toArray()[0] + ": " + element.entrySet().toArray()[0].toString().split("=")[1] + " Punkte" + ((!li.hasPrevious() || elementCount == 2) ? ". " : ", ");
                elementCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }

}
