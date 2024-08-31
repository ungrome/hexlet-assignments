@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-4" align="center">
            <h2 >${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
            <p>${page.getUser().getEmail()}</p>
        </div>
    `
)