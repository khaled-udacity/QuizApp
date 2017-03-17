package khaledharthi.com.quizapp;

/**
 * Created by khaled on 17/03/17.
 */

public class Quiz {
    String question;
    String[] choices;
    int checked_item = -1;
    int correct;
    public Quiz (String question, String[] choices, int correct){
        this.question = question;
        this.choices = choices;
        this.correct = correct;
    }

}
