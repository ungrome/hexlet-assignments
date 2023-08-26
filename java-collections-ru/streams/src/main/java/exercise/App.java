package exercise;

import java.util.List;
//import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        return (int) emails.stream()
                .filter(email -> isFreeEmail(email))
                .count();
    }

    public static boolean isFreeEmail(String email) {
        List<String> freeEmails = List.of("gmail.com", "yandex.ru", "hotmail.com");
        String[] domen = email.split("@");
        return freeEmails.contains(domen[1]);
    }
}
// END
