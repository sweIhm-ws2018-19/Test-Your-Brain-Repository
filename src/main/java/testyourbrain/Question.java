package testyourbrain;

import java.util.Arrays;

public class Question {
private final String question;
private final String thema;
private final String category;
private final String hint;
private final String[] suggestions;
private final String answer;
private final int difficulty;

    public Question(String question, String thema, int difficulty, String category, String hint, String suggestions, String answer) {
        this.question = question;
        this.thema = thema;
        this.category = category;
        this.hint = hint;
        this.suggestions = suggestions.split(",");
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getThema() {
        return thema;
    }

    public String getCategory() {
        return category;
    }

    public String getHint() {
        return hint;
    }

    public String[] getSuggestions() {
        return suggestions;
    }

    public String getAnswer() {
        return answer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", thema='" + thema + '\'' +
                ", category='" + category + '\'' +
                ", hint='" + hint + '\'' +
                ", suggestions=" + Arrays.toString(suggestions) +
                ", answer='" + answer + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
