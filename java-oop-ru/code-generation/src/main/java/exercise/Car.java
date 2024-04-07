package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
public class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Car unserialize(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonRepresentation, Car.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // END
}
