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

import org.junit.Test;
import testyourbrain.GameLogic;

import static org.junit.Assert.*;

/**
 *
 * @author wiesbob
 */
public class SelectDifficultyIntentTest {
    
    public SelectDifficultyIntentTest() {
    }
    @Test
    public void insert_leicht() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "leicht";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Schwierigkeit auf " + "EASY" + " gewechselt.");
    }

    @Test
    public void insert_mittel() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "mittel";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Schwierigkeit auf " + "MEDIUM" + " gewechselt.");
    }

    @Test
    public void insert_schwer() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "schwer";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Schwierigkeit auf " + "HARD" + " gewechselt.");
    }

    @Test
    public void insert_wrong() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "passt schon";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Deine Antwort: " + inputDifficulty + " entspricht keinem verfuegbaren Schwierigkeitsgrad.");
    }
    
}
