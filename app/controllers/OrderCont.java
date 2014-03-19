package controllers;

import models.Order;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.orders.*;

public class OrderCont extends Controller{

	public static Result showOrders() {
    	return ok(orders.render("Order"));
    }
	public static Result showOrder(int userId) {
		Order order = getOrderFromDb(userId);
		if (order == null){
			return notFound("Order with UserID: "+ userId + " not found!");
		}
		
		return ok(showOrder.render(order));
	}
   
	
	private static Order getOrderFromDb(int userId){
		switch (userId) {
		case 1:
			return new Order(0, null, "2012-12-12", 233.2, 20, null);			
		case 2:
			return new Order(1, null, "2012-12-12", 233.2, 20, null);			
		case 3:
			return new Order(2, null, "2012-12-12", 233.2, 20, null);			
		case 4:
			return new Order(3, null, "2012-12-12", 233.2, 20, null);			
		default:
			return null;
		}
		
	
		
	}
	
	
}
