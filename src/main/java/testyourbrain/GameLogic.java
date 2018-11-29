package testyourbrain;

public class GameLogic {
    public static final boolean DEBUGMODE = false;
    private static GameDifficulty difficulty;
    private static GameCategory category;
    private static GameState gameState;
    private static Game game;

    public static void setDifficulty(GameDifficulty difficulty) {
        GameLogic.difficulty = difficulty;
    }

    public static void setCategory(GameCategory category) {
        GameLogic.category = category;
    }

    public static void setGameState(GameState gameState) {
        GameLogic.gameState = gameState;
    }

    public static void startGame() {
        game = new Game(category, difficulty);
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

    public static Game getGame() {
        return game;
    }
}
