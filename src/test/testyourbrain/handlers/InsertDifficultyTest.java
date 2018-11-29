package test.testyourbrain.handlers;

import main.java.testyourbrain.GameDifficulty;
import main.java.testyourbrain.GameLogic;
import main.java.testyourbrain.handlers.InsertDifficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertDifficultyTest {


    //Leicht, Mittel, Schwer
    @Test
    public void insert_leicht() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "leicht";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        String debugMessage = "";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.EASY + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");

    }

    @Test
    public void insert_mittel() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "mittel";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        String debugMessage ="";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.MEDIUM + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_schwer() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "schwer";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        String debugMessage ="";
        if (GameLogic.DEBUGMODE)
            debugMessage = "Du hast " + inputDifficulty + " gewaehlt. Das entspricht " + GameDifficulty.HARD + ". ";
        assertEquals(outputMessage, debugMessage + "Waehle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_wrong() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "passt schon";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        assertEquals(outputMessage, "Deine Antwort: " + inputDifficulty + " entspricht keinem verfuegbaren Schwierigkeitsgrad.");
    }


}