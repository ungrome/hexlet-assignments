package exercise.controller;

import exercise.repository.CommentRepository;
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

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")

public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id) {
        Post targetPost = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post with id " + id + " not found"));
        return targetPost;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post data) {
        postRepository.save(data);
        return data;

    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable long id, @RequestBody Post data) {
        var targetPost = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post with id" + id + " not found"));
        targetPost.setTitle(data.getTitle());
        targetPost.setBody(data.getBody());
        return postRepository.save(targetPost);
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        if(!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post with id" + id + " not found");
        }
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }
}
// END
