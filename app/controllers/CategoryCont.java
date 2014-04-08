package controllers;

import java.util.List;
import java.util.Map;

import models.Category;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.categories.*;

public class CategoryCont extends Controller {
	@Transactional
	public static Result showCategories() {
		List<Category> categories = getCategoriesFromDB();
		return ok(showAllCategories.render(categories));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result addCategories() {
		return ok(addCategories.render(""));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result addCategory() {
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		String category = form.get("category")[0];
		String staff = form.get("staff")[0];
		int staffId = Integer.parseInt(staff);
		JPA.em().persist(new Category(category, staffId));
		flash().put("category", category);
		return ok(addCategories.render(""));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result delCategories() {
		return ok(delCategories.render(getCategoriesFromDB()));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result delCategory(int id) {
		Category cat = JPA.em().find(Category.class, id);
		JPA.em().remove(cat);
		return ok(delCategories.render(getCategoriesFromDB()));
	}

	@Transactional
	public static List<Category> getCategoriesFromDB() {
		List<Category> categories = JPA.em()
				.createQuery("SELECT c FROM Category AS c", Category.class)
				.getResultList();
		return categories;
	}
}
