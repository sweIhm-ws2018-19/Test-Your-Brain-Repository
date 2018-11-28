/*
 * Copyright 2018 wiesbob.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.java.testyourbrain;

import java.util.HashMap;

/**
 * @author wiesbob
 */
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
