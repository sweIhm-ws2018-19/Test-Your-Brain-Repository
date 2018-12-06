package testyourbrain;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.Map;

public class GameUtil {

    public static boolean saveData(HandlerInput input, String key, String value){
        try {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.put(key,value);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getData(HandlerInput input, String key){
        try {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            return persistentAttributes.get(key).toString();
        }catch (Exception e){
            return "fehlermeldung: " +  e.getMessage();
        }
    }
}
