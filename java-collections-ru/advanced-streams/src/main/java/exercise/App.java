package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String content) {
        String[] lines = content.split("\n");
        String modifyContent = Arrays.stream(lines)
                .filter(line -> line.startsWith("environment"))
                .map(line -> line.replaceAll("environment=",""))
                .map(line -> line.replaceAll(" ",""))
                .map(line -> line.replaceAll("\"",""))
                .map(line -> line.replaceAll(","," "))
                .collect(Collectors.joining(" "));
        List<String> variables = List.of(modifyContent.split(" "));
        return variables.stream()
                .filter(item -> item.contains("X_FORWARDED_"))
                .map(line -> line.replaceAll("X_FORWARDED_",""))
                .collect(Collectors.joining(","));

    }
}
//END
