package testyourbrain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import testyourbrain.GameUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameUtil.class)
@PowerMockIgnore("javax.management.*")
public class LaunchRequestHandlerTest {


    @Test
    public void testLaunchWithDefualtValue() {
        PowerMockito.mockStatic(GameUtil.class);
        HandlerInput input = TestUtil.mockHandlerInputbySlot("default", "default");
        LaunchRequestHandler launch = new LaunchRequestHandler();
        try {
            when(GameUtil.getAllQuestions(input)).thenReturn(TestUtil.allQuestions());
            Optional<Response> response = launch.handle(input);
            assertTrue(response.isPresent());
            assertTrue(response.get().getOutputSpeech().toString().toLowerCase().contains("willkommen"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}