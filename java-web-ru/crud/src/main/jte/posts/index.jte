@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    content = @`
    <div>
        <h1>Все посты</h1>
            <table class="table table-striped">
                @for (var post: page.getPosts())
                    <tr>
                        <td>
                            ${post.getId()}
                        </td>
                        <td>
                            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}"</a>
                        </td>
                        <td>
                            ${post.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
    </div>
    <div>

       @if (page.getPageNumber() > 1)
           <a href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() - 1}"
              class="btn btn-primary">Предыдущая</a>
       @else
           <span>Предыдущая</span>
       @endif
        <a href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}" class="btn btn-primary">Следующая</a>

    </div>
    `
)
