
package testyourbrain;

import java.util.HashMap;


public enum GameDifficulty {
    EASY, MEDIUM, HARD,WRONG;
    private static final HashMap<String, GameDifficulty> synonyms;

    static {
        synonyms = new HashMap<>();
        synonyms.put("leicht",EASY);
        synonyms.put("easy",EASY);
        synonyms.put("einfach",EASY);
        synonyms.put("mittel",MEDIUM);
        synonyms.put("neutral",MEDIUM);
        synonyms.put("schwer",HARD);
        synonyms.put("sehr schwer",HARD);
        synonyms.put("hart",HARD);
    }

    public static GameDifficulty getBySynonym(String in) {
        return synonyms.getOrDefault(in.toLowerCase(),WRONG);
    }
    
    public static GameDifficulty getByInteger(int i){
        if(i < 1 && i > 3){
            throw new IllegalArgumentException("Invalid difficulty-integer! Must be 1, 2 or 3. Your Input: " + i);
        }
        
        if(i == 1){
            return EASY;
        }
        if(i == 2){
            return MEDIUM;
        }
        if(i == 3){
            return HARD;
        }
        return WRONG;
    }
    
}
