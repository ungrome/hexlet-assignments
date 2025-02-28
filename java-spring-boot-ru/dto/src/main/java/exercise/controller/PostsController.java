package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.PostDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @GetMapping("")
    public List<PostDTO> getPosts() {
        var posts = postRepository.findAll();
        var result = posts.stream()
                .map(this :: toPostDTO)
                .collect(Collectors.toList());
        return result;
    }
    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post with id " + id + " not found"));
        return toPostDTO(post);
    }

    public PostDTO toPostDTO(Post post) {
        var comments = commentRepository.findByPostId(post.getId());
        var commentsDTO = comments.stream()
                .map(this::toCommentDTO)
                .toList();

        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());
        dto.setComments(commentsDTO);
        return dto;
    }

    public CommentDTO toCommentDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }

}
// END
