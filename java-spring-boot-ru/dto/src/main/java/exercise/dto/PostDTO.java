package exercise.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// BEGIN
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
