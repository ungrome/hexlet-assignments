@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <h1>Posts</h1>
    <a class="btn btn-primary" href="${NamedRoutes.buildPostPath()}">Create New Post</a>
    <hr>
    @if(page.getPosts().isEmpty())
        <p>No posts available</p>
    @else
        @for(var post : page.getPosts())
            <div class="post">
                <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
                <p>${post.getBody()}</p>
            </div>
        @endfor
    @endif
`
)