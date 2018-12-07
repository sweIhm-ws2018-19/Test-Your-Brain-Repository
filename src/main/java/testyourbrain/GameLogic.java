package testyourbrain;

public class GameLogic {

    public static final boolean DEBUGMODE = false;

    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;

    private GameLogic() {
    }

    public static void setDifficulty(GameDifficulty difficulty) {
        GameLogic.difficulty = difficulty;
    }

    public static void setCategory(GameCategory category) {
        GameLogic.category = category;
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
