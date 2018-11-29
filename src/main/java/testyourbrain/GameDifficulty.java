
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
}
