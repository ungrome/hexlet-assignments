@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <h1>Users</h1>
    <table class="table" width="100px">
    <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
        </tr>
    </thead>

    <tbody>
        @for (var user: page.getUsers())
            <tr>
                <td>${user.getId()}</td>
                <td><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></td>
            </tr>
        @endfor
    </tbody>
    </table>
    }
    `
)