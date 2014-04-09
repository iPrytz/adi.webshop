package controllers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import models.Order;
import models.Product;
import models.ProductsInCart;
import models.ProductsInOrder;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.carts.*;
import views.html.orders.showOrder;

public class CartCont extends Controller {

	@Transactional
	public static Result showCarts() {
		User user = UserCont.getUserFromSession();
		if (user == null) {
			return redirect(routes.UserCont.showLoginForm());
		}
		return ok(showCart.render(user));
	}

//	@Transactional
//	public static Result showCart(int userId) {
//		User user = getUserFromDb(userId);
//		if (user == null) {
//			return notFound("No user found");
//		}
//		return ok(showCart.render(user));
//	}

	@Transactional
	public static Result addToCart(int prodId) {
		User user = UserCont.getUserFromSession();
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		Product prod = JPA.em().find(Product.class, prodId);
		int quantity = 1;
		if (form != null) {
			String qty = form.get("quantity")[0];
			try{
				quantity = Integer.parseInt(qty);				
			}catch(NumberFormatException o){
				quantity = 1; 
			}
			finally{
				if(quantity < 1){
					quantity = 1;
				}				
			}
		} else {
			quantity = 1;
		}
		if (user != null) {
			TypedQuery<ProductsInCart> query = JPA
					.em()
					.createQuery(
							"SELECT c FROM ProductsInCart c WHERE c.product = :prod AND c.user = :user",
							ProductsInCart.class);
			query.setParameter("prod", prod);
			query.setParameter("user", user);
			List<ProductsInCart> productsInCartList = query.getResultList();

			if (productsInCartList.size() != 0) {
				ProductsInCart productInCart = productsInCartList.get(0);

				if (form != null) {
					productInCart.setQuantity(quantity);
					flash().put("updated", productInCart.getProduct().getName());
				} else {
					productInCart.setQuantity(quantity
							+ productInCart.getQuantity());
					flash().put("updated", productInCart.getProduct().getName());
				}
			} else {
				ProductsInCart prodInCart = new ProductsInCart(user, quantity,
						prod);
				JPA.em().persist(prodInCart);
			}
		} else {
			return redirect(routes.UserCont.showLoginForm());
		}
		return redirect(routes.CartCont.showCarts());
//		return ok(showCart.render(user));
	}

	@Transactional
	public static Result removeFromCart(int prodId) {
		User user = UserCont.getUserFromSession();

		if (user != null) {
			Product prod = JPA.em().find(Product.class, prodId);
			List<ProductsInCart> prodInCart = user.getProductsInCart();
			if (prodInCart != null) {
				for (ProductsInCart cartRecord : prodInCart) {
					if (cartRecord.getProduct().getId() == prod.getId()) {
						JPA.em().remove(cartRecord);
					}
				}
			}
		} else {
			return redirect(routes.UserCont.showLoginForm());
		}
		return redirect(routes.CartCont.showCarts());
	}

	@Transactional
	public static Result placeOrder() {
		User user = UserCont.getUserFromSession();
		
		if(user.getProductsInCart().isEmpty()){
			return redirect(routes.CartCont.showCarts());
		}
		
		double cost = 0.0;
		List<ProductsInCart> userProductsInCart = user.getProductsInCart();
		List<ProductsInOrder> userProductsInOrder = new ArrayList<ProductsInOrder>();
		
		for (ProductsInCart productsInCart : userProductsInCart) {
			cost = cost
					+ (productsInCart.getProduct().getRRP() * (double) productsInCart
							.getQuantity());
		}

		Date date = new Date(System.currentTimeMillis());
		Order order = new Order(user, date, cost,
				userProductsInOrder);

		for (ProductsInCart productsInCart : userProductsInCart) {
			userProductsInOrder.add(new ProductsInOrder(order, productsInCart
					.getQuantity(), productsInCart.getProduct()));
			JPA.em().remove(productsInCart);
		}
		order.setProducts(userProductsInOrder);
		JPA.em().persist(order);

		return ok(showOrder.render(order));
//		return redirect(routes.OrderCont.showOrder());
	}

	@Transactional
	private static User getUserFromDb(int userId) {
		return JPA.em().find(User.class, userId);
	}

}
