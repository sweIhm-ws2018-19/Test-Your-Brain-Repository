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

import testyourbrain.GameLogic;
import testyourbrain.GameDifficulty;
import org.junit.Test;
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

    
}
