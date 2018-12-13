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

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;
import java.util.Optional;
import testyourbrain.GameLogic;

/**
 *
 * @author wiesbob
 */
public class RepeatQuestionIntent implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RepeatQuestionIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String reply = GameLogic.getCurrentQuestion().getQuestion();
        if (GameLogic.getCurrentQuestion().getQuestion() == null) {
            reply = "Wenn du eine Kategorie und eine Schwierigkeitsstufe gewählt hast, dann sage zuerst \"nächste Frage\" um dir eine Frage stellen zu lassen.";
        }

        return input.getResponseBuilder()
                .withSpeech(reply)
                 .withShouldEndSession(false)
                //.withReprompt(reply)
                .build();
    }
}
