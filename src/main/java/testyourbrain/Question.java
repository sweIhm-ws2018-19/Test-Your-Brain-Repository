package testyourbrain;

public class Question {
    private String question;
    private String hint;
    private String[] possibleSolutions;
    private String solution;
    private String category;
    private int difficulty;

    public Question(String question, String hint, String[] possibleSolutions, String solution, String category, int difficulty) {
        this.question = question;
        this.hint = hint;
        this.possibleSolutions = possibleSolutions;
        this.solution = solution;
        this.category = category;
        this.difficulty = difficulty;
    }
}
