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
			return redirect(routes.CartCont.showCarts());		
		}
		
		User user = UserCont.getUserFromSession();
		for(Order userOrder :user.getOrders()){
			if(userOrder.getId() == orderId){
				return ok(showOrder.render(order));				
			}
		}
		return redirect(routes.CartCont.showCarts());		
	}

	public static Result showOrder() {
		return ok(showOrder.render(null));
	}
}
