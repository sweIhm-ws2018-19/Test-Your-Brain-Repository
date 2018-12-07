package testyourbrain;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameUtil {

    public static boolean saveData(HandlerInput input, String key, String value) {
        try {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.put(key, value);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getData(HandlerInput input, String key) {
        try {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            return persistentAttributes.get(key).toString();
        } catch (Exception e) {
            return "fehlermeldung: " + e.getMessage();
        }
    }

    /*
    Den Rückgabe Typ wollte ich dann zu einer List<Question> machen, da man die dann ganz einfach mit einem Stream filtern kann, beispielsweise
    auf Schwierigkeit oder Kategorie.
    Question ersteFrage = getQuestions(inputHandler).stream().filter(question -> question.getDifficulty() == 1).findfirst();
     */
    public static String getQuestions(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        String outcome = "  ";
        try {

            System.out.println(attributesManager.getPersistentAttributes().get("fragen").toString());
            ArrayList<HashMap<Integer, String>> liste = (ArrayList<HashMap<Integer, String>>) attributesManager.getPersistentAttributes().get("fragen");

            System.out.println("listenGroesse: " + liste.size());
            System.out.println("erstes element in der Liste: " + liste.get(0).toString());
            //HIER GIBTS NEN ERROR
            System.out.println("erstes element als map: " + liste.get(0).get("Schwierigkeit"));

            ArrayList<Question> questions = (ArrayList<Question>) liste.stream().map(map-> new Question(map.get("Kategorie"),map.get("Loesung"),Integer.valueOf(map.get("Schwierigkeit")),map.get("Hinweis"),map.get("Frage"),map.get("Themengebiet"),map.get("Auswahlmoeglichkeit"))).collect(Collectors.toList());

            //überprüfung der Liste mit Question Objekten
            System.out.println(" Question-listen Groesse: " + questions.size());
            System.out.println(" Question erstes element in der Liste: " + questions.get(0));

        } catch (Exception e) {
            outcome += e.getMessage();
        }

        return outcome;
    }
}
