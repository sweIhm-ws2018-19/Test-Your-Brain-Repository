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
        String outputMessage = ic.generateReply("politik");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Politik_groß() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.generateReply("Politik");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Politik_uppercase() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.generateReply("POLITIK");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geografie_klein() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.generateReply("geographie");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geografie_groß() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.generateReply("Geographie");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geografie_uppercase() {
        InsertCategory ic = new InsertCategory();
        String outputMessage = ic.generateReply("GEOGRAPHIE");
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geschichte_klein() {
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte";
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geschichte_groß() {
        InsertCategory ic = new InsertCategory();
        String inputString = "Geschichte";
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Geschichte_uppercase() {
        InsertCategory ic = new InsertCategory();
        String inputString = "geschichte".toUpperCase();
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Sonstiges_klein() {
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges";
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Sonstiges_groß() {
        InsertCategory ic = new InsertCategory();
        String inputString = "Sonstiges";
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    @Test
    public void insert_Sonstiges_uppercase() {
        InsertCategory ic = new InsertCategory();
        String inputString = "sonstiges".toUpperCase();
        String outputMessage = ic.generateReply(inputString);
        assertEquals("Ok, es kann losgehen. Wenn du eine neue Frage gestellt haben moechtest sage \"naechste Frage\".", outputMessage);
    }

    
}
