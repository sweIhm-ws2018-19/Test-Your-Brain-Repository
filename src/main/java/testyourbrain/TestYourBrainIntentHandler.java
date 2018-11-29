/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package testyourbrain;

import testyourbrain.handlers.SolutionIntent;
import testyourbrain.handlers.InsertCategory;
import testyourbrain.handlers.SelectDifficultyIntent;
import testyourbrain.handlers.InsertDifficulty;
import testyourbrain.handlers.StartGameIntent;
import testyourbrain.handlers.SessionEndedRequestHandler;
import testyourbrain.handlers.SelectCategoryIntent;
import testyourbrain.handlers.RulesIntent;
import testyourbrain.handlers.NextQuestionIntent;
import testyourbrain.handlers.HelpIntentHandler;
import testyourbrain.handlers.LaunchRequestHandler;
import testyourbrain.handlers.CancelandStopIntentHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import testyourbrain.handlers.FallbackIntentHandler;

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
                        new NextQuestionIntent())
                
                // Add your skill id below
                //.withSkillId("")
                .build();
        //Rudis Branch
    }

    public TestYourBrainIntentHandler() {
        super(getSkill());
    }

}
