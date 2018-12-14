package testyourbrain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {
private String frage;
private String kategorie;
private String hinweis;
private String auswahlmoeglichkeit;
private String loesung;
private int schwierigkeit;
private String themengebiet;

    public String getThemengebiet() {
        return themengebiet;
    }


    @JsonCreator
    public Question(@JsonProperty("Frage") String frage,@JsonProperty("Kategorie") String kategorie,@JsonProperty("Hinweis") String hinweis,@JsonProperty("Auswahlmoeglichkeit") String auswahlmoeglichkeit,@JsonProperty("Loesung") String loesung,@JsonProperty("Schwierigkeit") int schwierigkeit,@JsonProperty("Themengebiet") String themengebiet) {
        this.frage = frage;
        this.kategorie = kategorie;
        this.hinweis = hinweis;
        this.auswahlmoeglichkeit = auswahlmoeglichkeit;
        this.loesung = loesung;
        this.schwierigkeit = schwierigkeit;
        this.themengebiet = themengebiet;
    }



    public String getQuestion() {
        return frage;
    }


    public String getCategory() {
        return kategorie;
    }

    public String getHint() {
        return hinweis;
    }

    public String getSuggestions() {
        return auswahlmoeglichkeit;
    }

    public String getSolution() {
        return loesung;
    }

    public int getDifficulty() {
        return schwierigkeit;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + frage + '\'' +
                ", category='" + kategorie + '\'' +
                ", hint='" + hinweis + '\'' +
                ", suggestions=" + auswahlmoeglichkeit +
                ", answer='" + loesung + '\'' +
                ", difficulty=" + schwierigkeit +
                '}';
    }

    /**
     * @return the themengebiet
     */

}
