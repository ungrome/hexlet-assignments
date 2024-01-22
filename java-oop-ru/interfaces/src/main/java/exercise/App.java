package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int elements) {
        return apartments.stream()
                .limit(elements)
                .sorted(Comparator.comparingDouble(Home::getArea))
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}

// END
