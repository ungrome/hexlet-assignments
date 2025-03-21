package exercise;

import io.javalin.Javalin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() throws IndexOutOfBoundsException{

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN

        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParam("id");
            Map<String, String> company = null;
            for (Map<String,String> item: COMPANIES) {
                if (item.containsValue(id)) {
                    company = item;
                    }
                }
            if (company != null) {
                ctx.json(company);
            } else {
                ctx.status(404);
                ctx.result("Company not found");
            }
            });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
