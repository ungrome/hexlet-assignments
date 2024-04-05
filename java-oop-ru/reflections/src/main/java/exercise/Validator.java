package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(address) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> errorMap = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();
            try {
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(obj) == null) {
                        errors.add("can not be null");
                        }
                    }
                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                    int minLengthValue = minLengthAnnotation.minLength();
                    String value = (String) field.get(obj);
                    if ((value != null) && (value.length() < minLengthValue)) {
                        errors.add("length less than " + minLengthValue);
                    }
                    }
                if (!errors.isEmpty()) {
                    errorMap.put(field.getName(), errors);
                    }
                } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return errorMap;
    }
}
// END
