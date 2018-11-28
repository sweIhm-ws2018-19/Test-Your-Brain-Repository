package main.java.testyourbrain;

import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;

public class GameLogic {
    public static GameDifficulty DIFFICULTY;
    public static GameCategory CATEGORY;
    public static GameState GAMESTATE;
    public static Game GAME;

    public static void setDIFFICULTY(GameDifficulty DIFFICULTY){
        GameLogic.DIFFICULTY = DIFFICULTY;
    }

    public static void setCATEGORY(GameCategory CATEGORY) {
        GameLogic.CATEGORY = CATEGORY;
    }
    
    public static void setGAMESTATE(GameState GAMESTATE){
        GameLogic.GAMESTATE = GAMESTATE;
    }

    public static void startGame(){
        GAME = new Game(CATEGORY, DIFFICULTY);
    }
}
