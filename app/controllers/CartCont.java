package controllers;

import java.util.List;

import javax.persistence.TypedQuery;

import models.Product;
import models.ProductsInCart;
import models.TempCart;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.carts.*;

public class CartCont extends Controller {

	@Transactional
	public static Result showCarts() {
		User user = getUserFromSession();
		
		if(user==null){
			return redirect(routes.UserCont.showLoginForm());
		}
		
		return ok(showCart.render(user));
	}

	@Transactional
	public static Result showCart(int userId) {
		User user = getUserFromDb(userId);

		if (user == null) {
			return notFound("No user found");
		}

		return ok(showCart.render(user));
	}
	@Transactional
	public static Result addToCart(int prodId){
		User user = getUserFromSession();
		
		
		if (user != null){	
			Product prod = JPA.em().find(Product.class, prodId);
			ProductsInCart prodInCart = new ProductsInCart(user, 1, prod);
			JPA.em().persist(prodInCart);
		} else{
			return redirect(routes.UserCont.showLoginForm());
		}
		

		return ok(showCart.render(user));
	}

	@Transactional
	private static List<Product> getAllProductsFromDb() {
		return null; 
	}

	@Transactional
	private static User getUserFromDb(int userId) {
		return JPA.em().find(User.class, userId);
	}
	@Transactional
	private static User getUserFromSession() {
		
		TypedQuery<User> query = JPA.em().createQuery( "SELECT c FROM User c WHERE c.email = :email", User.class);
		query.setParameter("email", session().get("username"));
		
		List<User> activeUser = query.getResultList();
		
		if(activeUser.size() == 0){
			return null;
		}else{
		return activeUser.get(0);
	}}
}
