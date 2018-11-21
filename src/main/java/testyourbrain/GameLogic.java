package main.java.testyourbrain;

import java.lang.management.GarbageCollectorMXBean;

public class GameLogic {
    public static int DIFFICULTY;
    public static String CATEGORY;
    public static Game GAME;

    public GameLogic() {
        DIFFICULTY = 0;
        CATEGORY = "none";
    }

    public static void setDIFFICULTY(int difficulty){
        DIFFICULTY = difficulty;
    }

    public static void setCATEGORY(String CATEGORY) {
        GameLogic.CATEGORY = CATEGORY;
    }

    public static void startGame(){
        GAME = new Game(DIFFICULTY,CATEGORY);
    }
}
