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
package test.java.testyourbrain.handlers;

import testyourbrain.handlers.InsertCategory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wiesbob
 */
public class InsertCategoryTest {
    
    public InsertCategoryTest() {
    }
    
    //Test Politik, Geografie, Geschichte, Sonstiges
    @Test
    public void insert_Politik_klein() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("politik");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Politik_groß() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("Politik");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Politik_uppercase() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("POLITIK");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geografie_klein() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("geografie");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geografie_groß() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("Geografie");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geografie_uppercase() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.createInsertMessage("GEOGRAFIE");
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geschichte_klein() {
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geschichte_groß() {
        InsertCategory ic = new InsertCategory();
        String inputString = "Geschichte";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Geschichte_uppercase() {
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte".toUpperCase();
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Sonstiges_klein() {
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Sonstiges_groß() {
        InsertCategory ic = new InsertCategory();
        String inputString = "Sonstiges";
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    @Test
    public void insert_Sonstiges_uppercase() {
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges".toUpperCase();
        String outputMessage = ic.createInsertMessage(inputString);
        assertEquals(outputMessage, "Alles klar, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".");
    }

    
}
