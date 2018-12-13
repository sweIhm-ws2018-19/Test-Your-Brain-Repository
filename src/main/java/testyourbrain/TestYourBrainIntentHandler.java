
package testyourbrain;

import testyourbrain.handlers.*;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class TestYourBrainIntentHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler(),
                        new StartGameIntent(),
                        new SelectCategoryIntent(),
                        new SelectDifficultyIntent(),
                        new InsertDifficulty(),
                        new InsertCategory(),
                        new SolutionIntent(),
                        new RulesIntent(),
                        new NextQuestionIntent(),
                        new RepeatQuestionIntent()
                        )
                .withTableName("testYourBrainData")
                .withAutoCreateTable(false)
                // Add your skill id below
                //.withSkillId("")
                .build();
        //Rudis Branch
    }

    public TestYourBrainIntentHandler() {
        super(getSkill());
    }

}
