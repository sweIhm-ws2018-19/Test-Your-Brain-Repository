package main.tests;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import main.java.testyourbrain.GameLogic;
import main.java.testyourbrain.StringContainer;
import main.java.testyourbrain.handlers.InsertCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertCategoryTest {


    //Test Politik, Geografie, Geschichte, Sonstiges
    @Test
    public void insert_Politik_klein(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("politik");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Politik_groß(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("Politik");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Politik_uppercase(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("POLITIK");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geografie_klein(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("geografie");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geografie_groß(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("Geografie");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geografie_uppercase(){
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("GEOGRAFIE");
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geschichte_klein(){
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geschichte_groß(){
        InsertCategory ic = new InsertCategory();
        String inputString = "Geschichte";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Geschichte_uppercase(){
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte".toUpperCase();
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Sonstiges_klein(){
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Sonstiges_groß(){
        InsertCategory ic = new InsertCategory();
        String inputString = "Sonstiges";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }

    @Test
    public void insert_Sonstiges_uppercase(){
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges".toUpperCase();
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage,"Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben möchtest sage \"nächste Frage\".");
    }


}