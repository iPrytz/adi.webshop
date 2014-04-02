package controllers;

import models.Order;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.orders.*;

public class OrderCont extends Controller {
	@Transactional
	public static Result showOrders() {
		User user = UserCont.getUserFromSession();

		if (user != null) {
			if (user.getOrders() == null) {
				return redirect(routes.CartCont.showCarts());
			}
		} else {
			return redirect(routes.UserCont.showLoginForm());
		}

		return ok(showOrders.render(user));
	}

	@Transactional
	public static Result showOrderWithId(int orderId) {
		Order order = JPA.em().find(Order.class, orderId);
		if (order == null) {
			return notFound("Order with Order ID: " + orderId + " not found!");
		}

		return ok(showOrder.render(order));
	}

	public static Result showOrder() {

		return ok(showOrder.render(null));
	}

	// private static Order getOrderFromDb(int userId) {
	//
	// return null;
	// }

}
