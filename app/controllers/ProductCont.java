package controllers;

import java.util.List;
import java.util.Map;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.products.*;
import models.*;

public class ProductCont extends Controller {
	@Transactional
	public static Result showProducts() {
		List<Product> products = getAllProductsFromDb();
		return ok(showAllProducts.render(products));
	}

	@Transactional
	public static Result showProduct(int id) {
		Product product = getProductFromDb(id);
		if (product == null) {
			return notFound("Product with id: " + id + " not found!");
		}
		return ok(showProduct.render(product));
	}

	@Transactional
	public static Result showProductsInCategory(String categoryName) {
		List<Category> categories = JPA
				.em()
				.createQuery("FROM Category WHERE name = :name", Category.class)
				.setParameter("name", categoryName).getResultList();
		return ok(showProductsInCategory.render(categories.get(0)));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result addProducts() {
		List<Category> categories = CategoryCont.getCategoriesFromDB();
		return ok(addProducts.render(categories));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result addProduct() {
		Map<String, String[]> form = request().body().asFormUrlEncoded();

		String prodName = form.get("name")[0];
		String priceS = form.get("price")[0];
		String rrpS = form.get("rrp")[0];
		String desc = form.get("desc")[0];

		double price = Double.parseDouble(priceS);
		double rrp = Double.parseDouble(rrpS);

		JPA.em().persist(new Product(prodName, desc, price, rrp));
		flash().put("product", prodName);

		List<Category> categories = CategoryCont.getCategoriesFromDB();
		
		return ok(addProducts.render(categories));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result delProducts() {
		List<Product> products = getAllProductsFromDb();
		return ok(delProducts.render(products));
	}

	@Transactional
	@Security.Authenticated(MyAuthenticator.class)
	public static Result delProduct(int id) {
		Product prod = JPA.em().find(Product.class, id);
		JPA.em().remove(prod);

		List<Product> products = getAllProductsFromDb();
		return ok(delProducts.render(products));
	}

	@Transactional
	private static List<Product> getAllProductsFromDb() {
		List<Product> products = JPA.em()
				.createQuery("SELECT c FROM Product AS c", Product.class)
				.getResultList();
		return products;
	}

	@Transactional
	private static Product getProductFromDb(int id) {
		return JPA.em().find(Product.class, id);
	}

}
