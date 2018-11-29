package test.java;

import main.java.testyourbrain.handlers.SelectDifficultyIntent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectDifficultyIntentTest {
    @Test
    public void insert_leicht() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "leicht";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Kategorie auf " + "EASY" + " gewechselt.");
    }

    @Test
    public void insert_mittel() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "mittel";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Kategorie auf " + "MEDIUM" + " gewechselt.");
    }

    @Test
    public void insert_schwer() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "schwer";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast die Kategorie auf " + "HARD" + " gewechselt.");
    }

    @Test
    public void insert_wrong() {
        SelectDifficultyIntent id = new SelectDifficultyIntent();
        String inputDifficulty = "passt schon";
        String outputMessage = id.createchangeMessage(inputDifficulty);
        assertEquals(outputMessage, "Deine Antwort: " + inputDifficulty + " entspricht keinem verfuegbaren Schwierigkeitsgrad.");
    }
}