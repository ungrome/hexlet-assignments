package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.containsValue("male"))
                .sorted(Comparator.comparing(d -> d.get("birthday")))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }
}
// END
