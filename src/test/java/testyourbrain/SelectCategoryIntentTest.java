package java.testyourbrain;

import main.java.testyourbrain.handlers.SelectCategoryIntent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectCategoryIntentTest {

    //Test Politik, Geografie, Geschichte, Sonstiges
    @Test
    public void change_to_politik(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "politik";
        String outputMessage = ic.createChangeMessage(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_Geografie(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "geografie";
        String outputMessage = ic.createChangeMessage(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_geschichte(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "geschichte";
        String outputMessage = ic.createChangeMessage(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_sonstiges(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "sonstiges";
        String outputMessage = ic.createChangeMessage(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }

}