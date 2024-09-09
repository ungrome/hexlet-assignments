package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {
    private static final int PAGESIZE = 5;
    // BEGIN
    public static void index(Context ctx) {
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var posts = PostRepository.findAll(pageNumber, PAGESIZE);
        var page = new PostsPage(posts, pageNumber);
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        try {
            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Page not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", model("page", page));
        } catch (NotFoundResponse e) {
            ctx.status(404).result(e.getMessage());
        }
    }
    // END
}
