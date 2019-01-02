package testyourbrain;


import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameLogic {

    public static final boolean DEBUGMODE = false;

    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;
    private static ArrayList<Question> allQuestions = new ArrayList<>();
    private static ArrayList<Question> matchingQuestions = new ArrayList<>();
    private static Question currentQuestion;
    private static int askedQuestions;
    private static int correctAnsweredQuestions;

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

    private GameLogic() {
    }

    public static void setDifficulty(GameDifficulty difficulty) {
        GameLogic.difficulty = difficulty;
        updateMatchingQuestions();

    }

    public static ArrayList<Question> getAllQuestions() {
        return allQuestions;
    }

    public static ArrayList<Question> getMatchingQuestions() {
        return matchingQuestions;
    }

    public static int getAskedQuestions() {
        return askedQuestions;
    }

    public static int getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
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
        if (gameState.equals(GameState.RULES)) {
            askedQuestions = 0;
            correctAnsweredQuestions = 0;
        }
    }

    public static void questionAnswered(boolean isAnswerCorrect) {
        askedQuestions++;
        if (isAnswerCorrect)
            correctAnsweredQuestions++;
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

    public static void saveScoreToDB(String keyString, HandlerInput input) {
        //store persistent
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        System.out.println("DB before: " + persistentAttributes);
        if (!persistentAttributes.keySet().contains("Scores")) {
            persistentAttributes.put("Scores", new HashMap<String, Integer>());
        }

        Map<String, Integer> scores = (Map<String, Integer>) persistentAttributes.get("Scores");
        System.out.println("DB Scores: " + scores);
        scores.put(keyString, correctAnsweredQuestions);
        System.out.println("DB Scores added currentscore: " + scores);

        attributesManager.setPersistentAttributes(persistentAttributes);
        attributesManager.savePersistentAttributes();
    }

}
