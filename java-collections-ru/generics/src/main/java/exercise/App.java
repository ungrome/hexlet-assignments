package exercise;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> targetBooks = new ArrayList<>();

        for (Map<String, String> book : books) {
            Set<Map.Entry<String, String>> bookList = book.entrySet();
            Set<Map.Entry<String, String>> whereList = where.entrySet();

            if (bookList.containsAll(whereList)) {
                targetBooks.add(book);
            }
        }
        return targetBooks;
    }
}
//END
