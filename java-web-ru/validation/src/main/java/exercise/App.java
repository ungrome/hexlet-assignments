package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var article = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("article", article));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            var article = new BuildArticlePage();
            ctx.render("articles/build.jte", model("article", article));
        });

        app.post("/articles", ctx -> {
            try {
                var title = ctx.formParamAsClass("title", String.class)
                        .check(titleText -> titleText.length() > 1,
                                "Название не должно быть короче двух символов")
                        .check(titleText -> !ArticleRepository.existsByTitle(titleText),
                                "Статья с таким названием уже существует" )
                        .get();
                var content = ctx.formParamAsClass("content", String.class)
                        .check(contentText -> contentText.length() > 9,
                                "Статья должна быть не короче 10 символов")
                        .get();
                var article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                var page = new BuildArticlePage(ctx.formParam("title"), ctx.formParam("content"), e.getErrors());
                ctx.json(e.getErrors()).status(422);
                ctx.render("articles/build.jte", model("article", page));
            }
        });
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
