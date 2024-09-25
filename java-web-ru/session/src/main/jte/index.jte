@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
        <div class = "mx-auto p-4 py-md-5">
            @if(page.getName() != null)
                Добро пожаловать, ${page.getName().toString()}
                <form action="${NamedRoutes.logoutPath()}" method="post">
                    <button type="submit">Выйти</button>
                </form>
            @else
                <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
            @endif

        </div>
    `
)
