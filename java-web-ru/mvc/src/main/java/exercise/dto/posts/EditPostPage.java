package exercise.dto.posts;

import java.util.List;
import java.util.Map;


import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
import exercise.model.Post;
@AllArgsConstructor
@Getter
@NoArgsConstructor

public class EditPostPage {
    private Post post;
    private Map<String, List<ValidationError<Object>>> errors;
}
// END
