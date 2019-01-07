package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import testyourbrain.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class NextQuestionIntentTest {
    NextQuestionIntent nextQuestionIntent;
    HandlerInput goodHandlerInput;

    @Before
    public void setup(){
        nextQuestionIntent = new NextQuestionIntent();
        goodHandlerInput = TestUtil.mockHandlerInputbySlot("NextQuestionIntent","Naechste Frage");
        GameLogic.setGameState(GameState.GAME);
    }

    @Test
    public void askForNextQuestion(){
        GameLogic.setDifficulty(GameDifficulty.EASY);
        GameLogic.setCategory(GameCategory.POLITIK);
        assertTrue(nextQuestionIntent.handle(goodHandlerInput).isPresent());
    }

    @Test
    public void askPoliticsEasy(){
        GameLogic.setDifficulty(GameDifficulty.EASY);
        GameLogic.setCategory(GameCategory.POLITIK);
        ArrayList<Question> singleQuestion = (ArrayList<Question>) TestUtil.allQuestions().stream()
                .filter(question->GameDifficulty.getByInteger(question.getDifficulty()) == GameLogic.getDifficulty())
                .filter(question -> GameCategory.getByString(question.getCategory()) == GameLogic.getCategory())
                .limit(1).collect(Collectors.toList());
        GameLogic.setMatchingQuestions(singleQuestion);
        String question= singleQuestion.get(0).getQuestion().toLowerCase();
        Optional<Response> response =nextQuestionIntent.handle(goodHandlerInput);
        assertTrue(response.isPresent());
        assertTrue(response.get().getOutputSpeech().toString().toLowerCase().contains(question));
    }





}