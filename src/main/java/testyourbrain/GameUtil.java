package testyourbrain;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
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
    public static List<Question> getAllQuestions(HandlerInput input) throws IOException {
        return Arrays.asList(get(input, "fragen", Question[].class).get());
    }

    public static List<Question> getMatchingQuestions() {
        List<Question> allQuestions = GameLogic.getAllQuestions();
       // Objects.requireNonNull(allQuestions);
        
        List<Question> matching = null;
        if(!allQuestions.isEmpty()){
        matching = allQuestions.stream().filter(x -> GameCategory.getByString(x.getCategory())  == GameLogic.getCategory()).
                filter(x -> GameDifficulty.getByInteger(x.getDifficulty()) == GameLogic.getDifficulty()).
                collect(Collectors.toList());
        } else {
            System.out.println("All Questions is empty");
        }
        System.out.println("Found Matching Questions: " + matching);
        return matching;
    }
    
    public static Question getNextQuestion(){
        ArrayList<Question> matchingQuestions = GameLogic.getMatchingQuestions();
        System.out.println("Before: "+ matchingQuestions);
        Question nextQuestion = null;
        
        if(!matchingQuestions.isEmpty()){
            Random randomGen = new Random();
            int randomIndex = randomGen.nextInt(matchingQuestions.size());
            nextQuestion = matchingQuestions.get(randomIndex);
            matchingQuestions.remove(randomIndex);
        } else{
            System.out.println("Matchting Questions is empty");
        }
         System.out.println("After: "+ matchingQuestions);
        return nextQuestion;
    }
//
//    public static List<Question>  getMatchingQuestions(HandlerInput input) {
//        AttributesManager attributesManager = input.getAttributesManager();
//        ArrayList<HashMap<Integer, String>> liste = (ArrayList<HashMap<Integer, String>>) attributesManager.getPersistentAttributes().get("fragen");
//
//        ArrayList<HashMap<Integer, String>> matching;
////        HashMap<Integer, Question> allQuestions = new HashMap<>();
//
//        Optional<Question[]> allQuestions = get(input, "fragen", Question[].class);
//
//        return Arrays.asList(allQuestions.get());
//    }

//    private static Question fromDBString(String input) {
//        System.out.println("Das ist er Input: " + input);
//        input = input.replace("{", "");
//        input = input.replace("}", "");
//
//        System.out.println("Trimmed input: " + input);
//
//        String[] fields = input.split(",");
//        System.out.println("Schwierigkeit: " + fields[3].replace("Schwierigkeit=", ""));
//
//    }

    public static <T> Optional<T> get(HandlerInput input, String key, Class<T> clazz) throws IOException {
        Objects.requireNonNull(clazz, "Class that should be read from source map must not be null!");

        // Convert object to desired type
        final Object retVal = input.getAttributesManager().getPersistentAttributes().get(key);
        if (Objects.nonNull(retVal)) {

                // Object is String that should be parsed to desired type
                if (retVal instanceof String && !clazz.equals(String.class)) {
                    final String jsonVal = (String) retVal;
                    return Optional.of(new ObjectMapper().readValue(jsonVal, clazz));
                } // Object is already an object and should be converted to desired type
                else {
                    return Optional.of(new ObjectMapper().convertValue(retVal, clazz));
                }
        } else {
            return Optional.empty();
        }
    }

}
