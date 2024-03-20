package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    public String body;
    public List<Tag> child;

    public PairedTag(String tagName, Map<String, String> attributes,
                     String body, List<Tag> child) {
        super(tagName, attributes);
        this.body = body;
        this.child = child;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(new SingleTag(tagName, attributes)).append(body);
        for (Tag item: child) {
            str.append(item);
        }
        str.append("</").append(tagName).append(">");
        return str.toString();
    }
}
// END
