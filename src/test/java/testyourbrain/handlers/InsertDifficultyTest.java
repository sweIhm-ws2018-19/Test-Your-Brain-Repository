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
import com.amazon.ask.model.Response;
import testyourbrain.GameCategory;
import testyourbrain.GameLogic;
import testyourbrain.GameDifficulty;
import org.junit.Test;
import testyourbrain.GameState;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 *
 * @author wiesbob
 */
public class InsertDifficultyTest {
    
    public InsertDifficultyTest() {
    }
    @Test
    public void insert_leicht() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "leicht";
        String outputMessage = id.generateReply(inputDifficulty);
        String debugMessage = "";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.EASY + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");

    }

    @Test
    public void insert_mittel() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "mittel";
        String outputMessage = id.generateReply(inputDifficulty);
        String debugMessage ="";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.MEDIUM + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_schwer() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "schwer";
        String outputMessage = id.generateReply(inputDifficulty);
        String debugMessage ="";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.HARD + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_wrong() {

        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "passt schon";

        String outputMessage = id.generateReply(inputDifficulty);
        assertEquals(outputMessage, "Deine Antwort: " + inputDifficulty + " entspricht keinem verfuegbaren Schwierigkeitsgrad.");

    }

    @Test
    public void test_InsertCategory_canHandle(){
        HashMap<String,String> slotMap = new HashMap<>();
        slotMap.put("Schwierigkeitsgrad","Leicht");
        HashMap<String,Object> objMap = new HashMap<>();
        HandlerInput handlerInput = TestUtil.mockHandlerInput(slotMap,objMap,objMap,objMap);

        GameLogic.setGameState(GameState.CONFIG);
        Optional<Response> response = new InsertDifficulty().handle(handlerInput);
        assertTrue(response.isPresent());
        String ende = response.get().getOutputSpeech().toString();
        assertTrue(GameLogic.getDifficulty().equals(GameDifficulty.EASY));
        assertTrue(ende.contains("Waehle jetzt noch eine Kategorie."));
    }

    
}
