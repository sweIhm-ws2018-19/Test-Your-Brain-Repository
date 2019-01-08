package testyourbrain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import testyourbrain.handlers.TestUtil;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


@RunWith(PowerMockRunner.class)
public class GameLogicTest {

    @Test
    public void getNoQuestionsBecauseNoCategory() {
        ArrayList<Question> questionsBefore = TestUtil.allQuestions();
        GameLogic.setDifficulty(GameDifficulty.EASY);
        assertTrue(GameLogic.getMatchingQuestions().isEmpty());
    }

    @Test
    public void getNoQuestionsBecauseNoDifficulty() {
        ArrayList<Question> questionsBefore = TestUtil.allQuestions();
        GameLogic.setCategory(GameCategory.POLITIK);
        assertTrue(GameLogic.getMatchingQuestions().isEmpty());
    }


}