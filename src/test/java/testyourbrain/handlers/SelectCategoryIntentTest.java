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
public class SelectCategoryIntentTest {
    
    public SelectCategoryIntentTest() {
    }
    
    //Test Politik, Geografie, Geschichte, Sonstiges
    @Test
    public void change_to_politik(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "politik";
        String outputMessage = ic.generateReply(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_Geografie(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "geographie";
        String outputMessage = ic.generateReply(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_geschichte(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "geschichte";
        String outputMessage = ic.generateReply(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    @Test
    public void change_to_sonstiges(){
        SelectCategoryIntent ic = new SelectCategoryIntent();
        String inputString = "sonstiges";
        String outputMessage = ic.generateReply(inputString);
        assertEquals(outputMessage,"Du hast die Kategorie auf " + inputString + " gewechselt.");
    }
    
}
