package exercise;
import java.util.Map;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Tag div = new PairedTag(
                "div",
                Map.of("class", "y-5"),
                "",
                List.of(
                        new SingleTag("br", Map.of("id", "s")),
                        new SingleTag("hr", Map.of("class", "a-5"))
                )
        );
        System.out.println(div.toString()); // <div class="y-5"><br id="s"><hr class="a-5"></div>
    }
}
