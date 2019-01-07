package testyourbrain.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.Mockito;
import testyourbrain.Question;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtil {

    public static HandlerInput mockHandlerInputbySlot(String slot, String value) {
        HashMap<String, String> slotMap = new HashMap<>();
        HashMap<String, Object> attributesMap = new HashMap<>();
        slotMap.put(slot, value);
        return mockHandlerInput(slotMap, attributesMap, attributesMap, attributesMap);
    }

    public static HandlerInput mockHandlerInput(Map<String, String> slots, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
        when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);

        final Intent.Builder intentBuilder = Intent.builder();
        slots.forEach((key, value) ->
                intentBuilder.putSlotsItem(key, Slot.builder().withName(key).withValue(value).build())
        );

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(intentBuilder.build())
                        .build())
                .build();


        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

        return input;
    }

    public static HandlerInput mockHandlerInput(String playerName, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
        return mockHandlerInput(Collections.singletonMap("PlayerName", playerName), sessionAttributes, persistentAttributes, requestAttributes);
    }

    public static ArrayList<Question> allQuestions() {
        String allQuestions = " {\n" +
                "    \"fragen\": [\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Otto von Bismarck, Gustav Stresemann, Franz Joseph Strauss\",\n" +
                "        \"Frage\": \"Von welcher Persoenlichkeit stammt folgendes Zitat: \\\"Je weniger die Leute davon wissen wie Wuerste oder Gesetze gemacht werden, desto besser schlafen sie.\",\n" +
                "        \"Hinweis\": \"Er war erster Reichskanzler des deutschen Reichs.\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Otto von Bismarck, Bismarck, Otto\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \"Zitate, Geschichte\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Baeren, Giraffen, Elefanten\",\n" +
                "        \"Frage\": \"Welches sind die groeßten Fleischfressenden Tiere der Erde?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Baeren, Grizzlybaeren, der Baer\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Der Eiffelturm, die kleine Meerjungfrau, der Big Ben\",\n" +
                "        \"Frage\": \"Welches Wahrzeichen ist in Paris zu bewundern?\",\n" +
                "        \"Hinweis\": \"Er ist 324 Meter hoch und besteht komplett aus Eisen\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Der Eiffelturm, Eiffelturm\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Die erste Ziffer muss eine 5 sein, Die letzte Ziffer muss eine 5 oder 0 sein, Die Quersumme der Zahl muss durch 5 teilbar sein.\",\n" +
                "        \"Frage\": \"Wie stellt man fest, ob eine Zahl durch 5 teilbar ist?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Die letzte Ziffer muss eine 5 oder 0 sein, 5 oder 0\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Die Fichte, die Kiefer, die Laerche\",\n" +
                "        \"Frage\": \"Welcher Baum wirft im Winter seine Nadeln ab?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Die Laerche, Laerche, eine Laerche\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Thora, Koran, Bibel\",\n" +
                "        \"Frage\": \"Wie heißt das heilige Buch der Muslime?\",\n" +
                "        \"Hinweis\": \"Der erste Buchstabe ist ein K\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Der Koran, Koran\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"14, 21, 16\",\n" +
                "        \"Frage\": \"Wie viele Bundeslaender hat Deutschland?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"16, sechzehn\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Die Empfaengnis des heiligen Geistes, Jesu Auferstehung, die Segnung der Speisen\",\n" +
                "        \"Frage\": \"Was feiern Christen am Ostersonntag?\",\n" +
                "        \"Hinweis\": \"er ist von den Toten…\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Jesu Auferstehung, Auferstehung Jesu\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Zylinder, Wuerfel, Pyramide\",\n" +
                "        \"Frage\": \"Welcher geometrische Koerper hat fuenf Flaechen?\",\n" +
                "        \"Hinweis\": \"Davon gibt es viele in aeqypten\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Pyramide\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Levi Strauss, Peter Diesel, Tim Mustang\",\n" +
                "        \"Frage\": \"Wer erfand die Jeans?\",\n" +
                "        \"Hinweis\": \"Sein Nachname ist der eines großen Vogels\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Levi Strauss, Strauss\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"1, 2, 4\",\n" +
                "        \"Frage\": \"Wie viele Nieren hat ein Mensch im Normalfall?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": 2,\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Erfurt, Muenchen, Bamberg\",\n" +
                "        \"Frage\": \"Wie lautet die Hauptstadt von Bayern?\\nErfurt, Muenchen, Bamberg\",\n" +
                "        \"Hinweis\": \"Der Anfangsbuchstabe ist ein M\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Muenchen, Minga\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"2001, 2000, 2002\",\n" +
                "        \"Frage\": \"In welchem Jahr wurde der Euro als Bargeld eingefuehrt?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": 2002,\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"1960, 1945, 1950\",\n" +
                "        \"Frage\": \"Wann endete der 2. Weltkrieg?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": 1945,\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Berlin, Tokio, London\",\n" +
                "        \"Frage\": \"In welcher Stadt befindet sich der Big Ben?\",\n" +
                "        \"Hinweis\": \"Sie ist gleichzeitig die Hauptstadt von England\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"London\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Russland und Finnland, Spanien und Portugal, Frankreich und Großbritannien\",\n" +
                "        \"Frage\": \"Zwischen welchen beiden Laendern liegt der aermelkanal?\",\n" +
                "        \"Hinweis\": \"Ein Land ist eine Insel\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Frankreich und Großbritannien\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Ein Wettlauf ueber 42,195 Kilometer, ein Wettbewerb aus Schimmen, Radfahren und Laufen, ein beruehmtes Bauwerk in Athen\",\n" +
                "        \"Frage\": \"Was ist ein Marathon?\\nEin Wettlauf ueber 42,195 Kilometer, ein Wettbewerb aus Schimmen, Radfahren und Laufen, ein beruehmtes Bauwerk in Athen\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Ein Wettlauf ueber 42,195 Kilometer, Wettlauf\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Sauerstoff, Kohlendioxid, Helium\",\n" +
                "        \"Frage\": \"Was braucht Feuer zum Brennen?\",\n" +
                "        \"Hinweis\": \"Menschen brauchen es zum atmen\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Sauerstoff\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Ludwig Erhard, Willi Brandt, Konrad Adenauer\",\n" +
                "        \"Frage\": \"Wie hieß der erste deutsche Bundeskanzler?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Konrad Adenauer, Konrad, Adenauer\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Stier, Zwilling, Loewe\",\n" +
                "        \"Frage\": \"Welches Sternzeichen hat jemand, der am 15. Mai geboren wurde?\",\n" +
                "        \"Hinweis\": \"Das Sternzeichen hat zwei Hoerner\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Stier\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Kronen, Rubel, Euro\",\n" +
                "        \"Frage\": \"Wie nennt sich die Waehrung in Russland?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Rubel\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Mississippi, Kongo, Nil\",\n" +
                "        \"Frage\": \"Welcher Fluss ist der laengste der Welt?\",\n" +
                "        \"Hinweis\": \"Er fließt durch Afrika\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Nil\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Vitamin A, Vitamin D, Vitamin E\",\n" +
                "        \"Frage\": \"Welches Vitamin kann im Koerper nur durch Sonnenlicht gebildet werden?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Vitamin D\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"20, 15, 30\",\n" +
                "        \"Frage\": \"Wie viele Milchzaehne hat ein Kind normalerweise?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": 20,\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Denzel Washington, George Bush, George Washington\",\n" +
                "        \"Frage\": \"Wie hieß der erste Praesident von USA?\",\n" +
                "        \"Hinweis\": \"Ein Bundesstaat in Amerika traegt denselben Namen\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"George Washington, Washington\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Pluto, Mars, Jupiter\",\n" +
                "        \"Frage\": \"Welcher Planet unseres Sonnensystems wird als roter Planet bezeichnet?\",\n" +
                "        \"Hinweis\": \"Auch bekannt als ein Schokoriegel\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Mars\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"1990, 1988, 1989\",\n" +
                "        \"Frage\": \"Wann wurde die Mauer in Berlin niedergerissen?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": 1989,\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Australien, Amerika, Europa\",\n" +
                "        \"Frage\": \"Wo lebt der Dingo?\",\n" +
                "        \"Hinweis\": \"Dingo ist ein Wildhund, der in warmen Gebieten lebt\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Australien\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Steve Jobs, Bill Gates, Leo Mechelin\",\n" +
                "        \"Frage\": \"Wie heißt der Gruender von Microsoft?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Bill Gates, Bill, Gates\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Weil er von der Sonne angestrahlt wird, weil er aus heißem Gas besteht, durch staendige kleine Exposionen auf seiner Oberflaeche\",\n" +
                "        \"Frage\": \"Warum leuchtet der Mond?\",\n" +
                "        \"Hinweis\": \"Die Sonne spielt dabei eine wichtige Rolle\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Weil er von der Sonne angestrahlt wird\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Ein Geraet zur Tiefenmessung im Wasser, eine Wildkatze, ein Sueßwasserkrebs\",\n" +
                "        \"Frage\": \"Was ist ein Ozelot?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Eine Wildkatze, Die Wildkatze\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Queen Elisabeth die Erste, Margaret Thatcher, Heilige Barbara\",\n" +
                "        \"Frage\": \"Wer wurde auch als Eiserne Lady bezeichnet?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Margaret Thatcher, Thatcher\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Benjamin Franklin, Carl von Linné, die Gebrueder Montgolfier\",\n" +
                "        \"Frage\": \"Wer erfand den Heißluftballon?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Die Gebrueder Montgolfier\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Die Zugspitze, der aetna, der Montblanc\",\n" +
                "        \"Frage\": \"Welcher dieser 3 Berge ist der hoechste Berg?\",\n" +
                "        \"Hinweis\": \"Ein Stift traegt seinen Namen\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Der Montblanc, Montblanc\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Ural, Zagros, Karpaten\",\n" +
                "        \"Frage\": \"Welches Gebirge teilt Europa und Asien?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Ural\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Theodor Heuss, Karl Carstens, Heinrich Luebke\",\n" +
                "        \"Frage\": \"Wie hieß der erste deutsche Bundespraesident?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Theodor Heuss, Theodor, Heuss\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Erde, Mars, Venus\",\n" +
                "        \"Frage\": \"Wie lautet der zweite Planet in unserem Sonnensystem?\",\n" +
                "        \"Hinweis\": \"Der Name ist auch der einer griechischen Goettin\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Venus\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Gold, Schwefel, Kohlenstoff\",\n" +
                "        \"Frage\": \"Aus was besteht Diamant?\",\n" +
                "        \"Hinweis\": \"Im Periodensystem traegt es das Symbol C\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Kohlenstoff\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Pazifischer Ozean, Indischer Ozean, Atlantischer Ozean\",\n" +
                "        \"Frage\": \"Welcher Ozean liegt zwischen Europa und Amerika?\",\n" +
                "        \"Hinweis\": \"Es ist der zweitgroeßte Pazifik der Welt\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Atlantischer Ozean, Atlantik\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Adler, Feldermaeuse, Bienen\",\n" +
                "        \"Frage\": \"Welche Saeugetiere koennen fliegen?\",\n" +
                "        \"Hinweis\": \"Sie sind vor allem nachtaktiv\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Fledermaeuse\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"50, 48, 55\",\n" +
                "        \"Frage\": \"Wieviele Sterne hat die amerikanische Nationalflagge?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": 50,\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"selbststaendig, familaer, warm\",\n" +
                "        \"Frage\": \"Was versteht man unter autonom?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"selbststaendig\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Asien, Amerika, Australien\",\n" +
                "        \"Frage\": \"Was ist der groeßte Kontinent?\",\n" +
                "        \"Hinweis\": \"Ihn besiedeln rund 4 Milliarden Menschen\",\n" +
                "        \"Kategorie\": \"Geografie\",\n" +
                "        \"Loesung\": \"Asien\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Diktatur, Kommunismus, Demokratie\",\n" +
                "        \"Frage\": \"Welche Regierungsform hat die Bundesrepublik Deutschland?\",\n" +
                "        \"Hinweis\": \"Menschen haben die gleichen Rechte\",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Demokratie\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Bundesversammlung, Bundesrat, Bundeskanzler\",\n" +
                "        \"Frage\": \"Wer waehlt in der Republik Deutschland die Bundesminister?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Bundesversammlung\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Paris, Straßburg, Bordeaux\",\n" +
                "        \"Frage\": \"Wo hat das Europaeische Parlament seinen Sitz?\",\n" +
                "        \"Hinweis\": \"Die Stadt liegt in Frankreich\",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Straßburg\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Chicago, New York, Illinois\",\n" +
                "        \"Frage\": \"Wo haben die Vereinten Nationen ( UNO ) ihren Sitz?\",\n" +
                "        \"Hinweis\": \"Die Stadt liegt in Amerika\",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"New York\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"65 Mio., 82 Mio., 100 Mio\",\n" +
                "        \"Frage\": \"Wie viele Einwohner hat Deutschland?\",\n" +
                "        \"Hinweis\": \"es sind ueber 50 Millionen\",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"82 Mio\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Fusion, Inklusion, Diskussion\",\n" +
                "        \"Frage\": \"Wie nennt man die rechtliche und wirtschaftliche Verschmelzung von zwei oder mehr Unternehmen?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Fusion\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Atlantik, Pazifik, Mittelmeer\",\n" +
                "        \"Frage\": \"Welches Meer liegt zwischen Europa und Afrika?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geographie\",\n" +
                "        \"Loesung\": \"Mittelmeer\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Totes Meer, Schwarzes Meer, Rotes Meer\",\n" +
                "        \"Frage\": \"In welches Meer muendet die Donau?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geographie\",\n" +
                "        \"Loesung\": \"Schwarzes Meer\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Schleswig-Holstein, Hamburg, Nordrheinwestphalen\",\n" +
                "        \"Frage\": \"Welches ist das noerdlichste Bundesland in Deutschland?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geographie\",\n" +
                "        \"Loesung\": \"Schleswig-Holstein\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Louvre, Mona-Lisa, Genmark\",\n" +
                "        \"Frage\": \"Wie heißt ein bekanntes Pariser Museum fuer Kunst?\",\n" +
                "        \"Hinweis\": \"das Museum hat die Form einer Pyramide\",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Louvre\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Golfen,Boxen,Schwimmen\",\n" +
                "        \"Frage\": \"Welche Sportart ist bei Olympischen Spielen nur fuer Maenner zugelassen?\",\n" +
                "        \"Hinweis\": \"fuer diesen Sport benoetigt man Handschuhe\",\n" +
                "        \"Kategorie\": \"Sonstiges\",\n" +
                "        \"Loesung\": \"Boxen\",\n" +
                "        \"Schwierigkeit\": 2,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Argentinien, Suedafrika, Korea\",\n" +
                "        \"Frage\": \"Praesident welchen Landes war   Nelson Mandela?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Suedafrika\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Frankfurt, Berlin, Muenchen\",\n" +
                "        \"Frage\": \"Wo fand 1816 die erste Sitzung des   ersten Deutschen Bundestages statt?\",\n" +
                "        \"Hinweis\": \"In dieser Stadt liegt der groeßte Flughafen Deutschlands\",\n" +
                "        \"Kategorie\": \"Politik\",\n" +
                "        \"Loesung\": \"Frankfurt\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Faust, Schiller, Goethe\",\n" +
                "        \"Frage\": \"Wer schrieb „Faust“?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"Faust\",\n" +
                "        \"Schwierigkeit\": 3,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"1914, 1922,1940\",\n" +
                "        \"Frage\": \"In welchem Jahr begann der 1. Weltkrieg?\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": 1914,\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      },\n" +
                "      {\n" +
                "        \"Auswahlmoeglichkeit\": \"Spanisch, Portugiesisch, italienisch\",\n" +
                "        \"Frage\": \"Welche Sprache spricht man in Brasilien\",\n" +
                "        \"Hinweis\": \" \",\n" +
                "        \"Kategorie\": \"Geschichte\",\n" +
                "        \"Loesung\": \"portugiesisch\",\n" +
                "        \"Schwierigkeit\": 1,\n" +
                "        \"Themengebiet\": \" \"\n" +
                "      }\n" +
                "    ]}";
        JSONObject object = null;
        JSONArray fragenArray = null;
        try {
            object = new JSONObject(allQuestions);
            fragenArray = object.getJSONArray("fragen");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Question> questions = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < fragenArray.length(); i++) {
            Question question = null;
            try {
                question = (Question) mapper.readValue(fragenArray.getString(i),Question.class);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            questions.add(question);
        }
        return questions;
    }

}