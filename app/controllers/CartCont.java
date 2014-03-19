package controllers;

import java.util.Arrays;
import java.util.List;


import models.Product;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.carts.*;

public class CartCont extends Controller {

	public static Result showCarts() {
		return ok(carts.render("Cart"));
	}

	public static Result showCart(int userId) {
		List<Product> products = getAllProductsFromDb();
		User user = getUserFromDb(userId);

		if (user == null) {
			return notFound("No user found");
		}

		return ok(showCart.render(products, user));
	}

	private static List<Product> getAllProductsFromDb() {
		return null; //Ebean.find(Product.class).findList();
	}

	private static User getUserFromDb(int userId) {
		return null; // Ebean.find(User.class, userId);
	}
}
