package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import gg.jte.runtime.StringUtils;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {
        var body = ctx.formParam("body");
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 1, "Название не должно быть короче двух символов")
                    .get();
            ctx.sessionAttribute("flash", "Post was successfully created!");
            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var page = new BuildPostPage(ctx.formParam("name"), body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page));
        }
    }

    public static void index(Context ctx) {
        String flash = ctx.consumeSessionAttribute("flash");
        var posts = PostRepository.getEntities();
        var page = new PostsPage(posts);
        page.setFlash(flash);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
