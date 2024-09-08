@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage article

@template.layout.page(
content = @`
    @if(article.getErrors() != null)
        <ul>
            @for(var validator : article.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif
    <div class="mx-auto p-4 py-md-5 bg-light border" style="width: 300px">
    <form action="/articles" method="post">
        <div>
            <label for="title">
                Название статьи
                <input type="text" name="title" value="${article.getTitle()}">
            </label>
        </div>
        <div>
            <label for="content">
                <p class="mx-auto" style="width: 300px">Содержание</p>
                <textarea type="text" name="content" value="${article.getContent()}"></textarea>
            </label>
        </div>
        <div>
            <label for="button" class="mx-auto">
                <input type="submit" value="Сохранить">
            </label>
        </div>
    </form>
    </div>
`
)