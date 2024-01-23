package exercise;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private static String filePath;
    private static Map<String, String> storage;
    public FileKV(String filePath, Map<String,String> storage){
        this.filePath = filePath;
        this.storage = new HashMap<>(storage);
        String data = Utils.serialize(storage);
        Utils.writeFile(filePath, data);
    }
    private Map<String, String> useData(){
        String data = Utils.readFile(filePath);
        return Utils.unserialize(data);
    }
    @Override
    public void set(String key, String value) {
        Map<String, String> storage = useData();
        storage.put(key, value);
        String data = Utils.serialize(storage);
        Utils.writeFile(filePath, data);
    }
    @Override
    public void unset(String key) {
        Map<String, String> storage = useData();
        storage.remove(key);
        String data = Utils.serialize(storage);
        Utils.writeFile(filePath, data);
    }
    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> storage = useData();
        return storage.getOrDefault(key, defaultValue);
    }
    @Override
    public Map<String, String> toMap() {
        Map<String, String> storage = useData();
        return new HashMap<>(storage);
    }
}
// END
