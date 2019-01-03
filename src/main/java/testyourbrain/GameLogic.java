package testyourbrain;


import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public static final boolean DEBUGMODE = false;

    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;
    private static List<Question> allQuestions = new ArrayList<>();
    private static ArrayList<Question> matchingQuestions = new ArrayList<>();
    private static Question currentQuestion;

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
    
    public static String getDebugInfo(){
    return "Difficulty: " + getDifficulty() 
            + " Category: " + getCategory() 
            + " GameState: " + getGameState() + "\n"
            + "AllQuestionAmount: " + getAllQuestions().size()
            + " MatchingQuestionAmount: " + getMatchingQuestions().size()
            + " CurrentQuestion: " + getCurrentQuestion();
    }

}
