package testyourbrain;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic {

    public static final boolean DEBUGMODE = false;

    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;
    private static List<Question> allQuestions = new ArrayList<>();
    private static ArrayList<Question> matchingQuestions = new ArrayList<>();
    private static Question currentQuestion;
    private static int numberAskedQuestions;
    private static int numberCorrectQuestions;

    private static void updateMatchingQuestions() {
        if (getCategory() != null && getDifficulty() != null && !getAllQuestions().isEmpty()) {
            setMatchingQuestions(new ArrayList<>(GameUtil.getMatchingQuestions()));
        }

    }

    public static Question getCurrentQuestion() {
        return currentQuestion;
    }

    public static void setCurrentQuestion(Question currentQuestion) {
        GameLogic.currentQuestion = currentQuestion;
    }

    public static void countQuestions(boolean result) {
        setNumberAskedQuestions(getNumberAskedQuestions() + 1);
        if (result) {
            setNumberCorrectQuestions(getNumberCorrectQuestions() + 1);
        }
    }

    private GameLogic() {
    }

    public static void setDifficulty(GameDifficulty difficulty) {
        GameLogic.difficulty = difficulty;
        updateMatchingQuestions();

    }

    public static List<Question> getAllQuestions() {
        return allQuestions;
    }

    public static void setAllQuestions(List<Question> all) {
        allQuestions = all;
    }

    public static ArrayList<Question> getMatchingQuestions() {
        return matchingQuestions;
    }

    public static void setMatchingQuestions(ArrayList<Question> matching) {
        matchingQuestions = matching;
    }

    public static void setCategory(GameCategory category) {
        GameLogic.category = category;
        updateMatchingQuestions();
    }

    public static void setGameState(GameState gameState) {
        GameLogic.gameState = gameState;
    }

    public static GameDifficulty getDifficulty() {
        return difficulty;
    }

    public static GameCategory getCategory() {
        return category;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static String getDebugInfo() {
        return "Difficulty: " + getDifficulty()
                + " Category: " + getCategory()
                + " GameState: " + getGameState() + "\n"
                + "AllQuestionAmount: " + getAllQuestions().size()
                + " MatchingQuestionAmount: " + getMatchingQuestions().size()
                + " CurrentQuestion: " + getCurrentQuestion();
    }

    /**
     * @return the numberAskedQuestions
     */
    public static int getNumberAskedQuestions() {
        return numberAskedQuestions;
    }

    /**
     * @param aNumberAskedQuestions the numberAskedQuestions to set
     */
    public static void setNumberAskedQuestions(int aNumberAskedQuestions) {
        numberAskedQuestions = aNumberAskedQuestions;
    }

    /**
     * @return the numberCorrectQuestions
     */
    public static int getNumberCorrectQuestions() {
        return numberCorrectQuestions;
    }

    /**
     * @param aNumberCorrectQuestions the numberCorrectQuestions to set
     */
    public static void setNumberCorrectQuestions(int aNumberCorrectQuestions) {
        numberCorrectQuestions = aNumberCorrectQuestions;
    }
        public static void saveScoreToDB(String keyString, HandlerInput input) {
        //store persistent
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        System.out.println("DB before: " + persistentAttributes);
        if (!persistentAttributes.keySet().contains("Score")) {
            persistentAttributes.put("Score", new ArrayList<HashMap<String, Integer>>());
        }

        ArrayList<Map<String, Integer>> scores = (ArrayList<Map<String, Integer>>) persistentAttributes.get("Score");
        System.out.println("DB Scores: " + scores);
        HashMap<String, Integer> element = new HashMap<String, Integer>();
        element.put(keyString, numberCorrectQuestions);
        scores.add(element);
        System.out.println("DB Scores added currentscore: " + scores);

        attributesManager.setPersistentAttributes(persistentAttributes);
        attributesManager.savePersistentAttributes();
}

}
