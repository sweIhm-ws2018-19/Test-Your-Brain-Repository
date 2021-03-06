package testyourbrain;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GameUtil {

    private GameUtil() {
    }

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

    public static List<Question> getAllQuestions(HandlerInput input) throws IOException {

        Question[] all = get(input, "fragen", Question[].class).get();
        List<Question> allQuestions = Arrays.asList(all);
        System.out.println("getAQ-Start: " + allQuestions+ " size: " + allQuestions.size());

        if (allQuestions.size() == 0) {
            throw new IOException("DB Connection failed.");
        }
        if (allQuestions.size() > 59) {
            allQuestions = allQuestions.subList(0, 59);
        }
        System.out.println("getAQ-end: " + allQuestions + " size: " + allQuestions.size());

        return allQuestions;
    }

    public static List<Question> getMatchingQuestions() {
        List<Question> allQuestions = GameLogic.getAllQuestions();
        System.out.println("getMQ-Start ALL: " + allQuestions.size());

        List<Question> matching = null;
        if (!allQuestions.isEmpty()) {
            matching = allQuestions.stream().filter(x -> GameCategory.getByString(x.getCategory()) == GameLogic.getCategory()).
                    filter(x -> GameDifficulty.getByInteger(x.getDifficulty()) == GameLogic.getDifficulty()).
                    collect(Collectors.toList());
        } else {
            Logger.getLogger("log").log(Level.SEVERE, "All Questions is empty");
        }
        System.out.println("getMQ-End All: " + allQuestions.size());
        System.out.println("getMQ-Start MAT: " + matching);

        return matching;
    }

    public static Question getNextQuestion() {
        ArrayList<Question> matchingQuestions = GameLogic.getMatchingQuestions();
        Question nextQuestion = null;
        System.out.println("getNQ-Start: " + matchingQuestions);

        if (!matchingQuestions.isEmpty()) {
            Random randomGen = new Random();
            int randomIndex = randomGen.nextInt(matchingQuestions.size());
            nextQuestion = matchingQuestions.get(randomIndex);

            matchingQuestions.remove(randomIndex);
        } else {
            Logger.getLogger("log").log(Level.SEVERE, "Matchting Questions is empty");
        }
        System.out.println("getNQ-End: " + matchingQuestions);

        return nextQuestion;
    }

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
