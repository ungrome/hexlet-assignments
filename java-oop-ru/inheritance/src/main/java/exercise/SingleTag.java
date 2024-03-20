package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("<").append(tagName);
        for (Map.Entry<String, String> entry: attributes.entrySet()) {
            str.append(" ").append(entry.getKey()).append("=\"")
                    .append(entry.getValue()).append("\"");
        }
        str.append(">");
        return str.toString();
    }
}
// END
