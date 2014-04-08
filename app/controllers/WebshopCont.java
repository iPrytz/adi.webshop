package controllers;

import java.util.List;

import models.Category;
import play.db.jpa.Transactional;
import play.mvc.Security;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.webshop.*;
import views.html.products.*;

public class WebshopCont extends Controller {

	public static Result webshop() {
		return ok(index.render("Expected Webshop with no Exception",
				"Welcome!", null));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result admin() {
		if (session().get("username") == null) {
			return redirect(routes.UserCont.showLoginForm());
		}
		List<Category> categories = CategoryCont.getCategoriesFromDB();
		return ok(addProducts.render(categories));
	}
}