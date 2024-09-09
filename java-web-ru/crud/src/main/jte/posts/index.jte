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
        <nav aria-label="...">
            <ul class="pagination">
                @if (page.getPageNumber() > 1)
                    <li class="page-item">
                        <a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() - 1}"
                           tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() - 1}"
                        >${page.getPageNumber() - 1}</a></li>
                    <li class="page-item active">
                        <a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber()}"
                        >${page.getPageNumber()} <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}"
                        >${page.getPageNumber() + 1}</a></li>
                    <li class="page-item">
                        <a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}">Next</a>
                    </li>
                @else
                    <li class="page-item disabled">
                        <a class="page-link" href="#">Previous</a>
                    </li>
                    <li class="page-item active">
                        <a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber()}"
                        >${page.getPageNumber()} <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}"
                        >${page.getPageNumber() + 1}</a></li>
                    <li class="page-item">
                        <a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}">Next</a>
                    </li>
                @endif
            </ul>
        </nav>
    </div>
    `
)
