@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(content =
    @`
        <div class="container p-10 py-md-5" >
            <h1>${page.getPost().getName()}</h1>
            <div>${page.getPost().getBody()}</div>
            <a href="${NamedRoutes.postsPath()}" class="btn btn-primary">Назад к постам</a>
        </div>
    `
)