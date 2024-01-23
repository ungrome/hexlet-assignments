package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage){
       for (Map.Entry<String, String> entry : storage.toMap().entrySet()){
           String newKey = entry.getValue();
           String newValue = entry.getKey();
           storage.unset(entry.getKey());
           storage.set(newKey, newValue);

       }
    }
}
// END
