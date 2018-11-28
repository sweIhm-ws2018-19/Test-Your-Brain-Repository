package main.tests;

import main.java.testyourbrain.GameDifficulty;
import main.java.testyourbrain.GameLogic;
import main.java.testyourbrain.handlers.InsertCategory;
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
        assertEquals(outputMessage, "Du hast " + inputDifficulty + " gewählt. Das entspricht " + GameDifficulty.EASY + ". Wähle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_mittel() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "mittel";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast " + inputDifficulty + " gewählt. Das entspricht " + GameDifficulty.MEDIUM + ". Wähle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_schwer() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "schwer";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        assertEquals(outputMessage, "Du hast " + inputDifficulty + " gewählt. Das entspricht " + GameDifficulty.HARD + ". Wähle jetzt noch eine Kategorie.");
    }

    @Test
    public void insert_wrong() {
        InsertDifficulty id = new InsertDifficulty();
        String inputDifficulty = "passt schon";
        String outputMessage = id.createReplyMessage(inputDifficulty);
        assertEquals(outputMessage, "Deine Antwort: " + inputDifficulty + " entspricht keinem verfügbaren Schwierigkeitsgrad.");
    }



}