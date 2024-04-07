package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path filePath, Car car) {
        String jsonRepresentation = car.serialize();
        try {
            Files.write(filePath, jsonRepresentation.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Возникла ошибка, проверьте данные");
        }
    }

    public static Car extract(Path filepath) {
        try {
           String json = new String(Files.readAllBytes(filepath));
           return Car.unserialize(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
// END
