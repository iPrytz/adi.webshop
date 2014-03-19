package controllers;

import java.util.List;


import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.*;
import models.*;

public class ProductCont extends Controller {

	public static Result showProducts() {
		List<Product> products = getAllProductsFromDb();

		return ok(showAllProducts.render(products));
	}

	public static Result showProduct(int id) {
		Product product = getProductFromDb(id);

		if (product == null) {
			return notFound("Product with id: " + id + " not found!");
		}
		return ok(showProduct.render(product));
	}

	public static Result showProductsInCategory(String category) {
		return ok(products.render("Products in " + category));
	}

	public static Result editProducts() {
		return ok(editProducts.render("Edit products"));
	}

	
	
	private static List<Product> getAllProductsFromDb() {
		 
		return null; //Ebean.find(Product.class).findList();
	}

	private static Product getProductFromDb(int id) {
		return null; // Ebean.find(Product.class, id);
	}


}
