package testyourbrain;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.ArrayList;
import java.util.Set;

public class GameLogic {

    public static final boolean DEBUGMODE = false;

    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;
    private static ArrayList<Question> allQuestions = new ArrayList<Question>();
    private static ArrayList<Question> matchingQuestions = new ArrayList<Question>();
    private static Question currentQuestion;

    private static void updateMatchingQuestions() {
        if (getCategory() != null && getDifficulty() != null && !getAllQuestions().isEmpty()) {
            getMatchingQuestions().addAll(GameUtil.getMatchingQuestions());
            System.out.println("Es gibt nun : " + matchingQuestions.size() + " fragen in der Kategorie: " + category + " und in der Schwierigkeit: " + difficulty);
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

}
