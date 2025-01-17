package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {

    private List<Post> posts = Data.getPosts();
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUsersPosts(@PathVariable int id) {
        List<Post> userPosts = posts.stream()
                .filter(p->(id == p.getUserId())).collect(Collectors.toList());

        return ResponseEntity.ok(userPosts);
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id,
                       @RequestBody Post post) {

        post.setUserId(id);
        posts.add(post);
        return post;
    }
}
// END
