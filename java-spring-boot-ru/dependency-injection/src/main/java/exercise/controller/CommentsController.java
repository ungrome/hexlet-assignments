package exercise.controller;

import exercise.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id= " + id + " not found"));
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment data) {
        commentRepository.save(data);
        return data;
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@PathVariable long id, @RequestBody Comment data) {
        var targetComment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment with id" + id + " not found"));
        targetComment.setBody(data.getBody());
        return commentRepository.save(targetComment);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
