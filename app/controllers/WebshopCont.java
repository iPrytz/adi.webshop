package controllers;


import models.Product;
import models.User;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.webshop.*;


public class WebshopCont extends Controller {

    public static Result webshop() {
        return  ok(index.render("This is Webshop.", null));
    }
    
    
public static Result  skapaFejkDataMetod() {
	
	
//	for (Product product: Ebean.find(Product.class).findList()) {
//		//Ebean.delete(Product.class, product.getId());
//	}
//	for (User user: Ebean.find(User.class).findList()) {
//		//Ebean.delete(User.class, user.getId());
//	}
	
	
//		Ebean.save(new Product(1, "Snö", 1.00, 1.99));
//		Ebean.save(new Product(2, "Boll", 12.00, 15.99));
//		Ebean.save(new Product(3, "Bil", 2000.00, 2500.00));
//		Ebean.save(new Product(4, "Häst", 1200.00, 1500.99));
//		
//		Ebean.save(new User(1, "email1", "paww", "Isak1", "Prytz", "Gatan1", "2342", "bönstad", "1223123123"));
//		Ebean.save(new User(2, "email2", "paww", "Isak2", "Prytz", "Gatan2", "2342", "bönstad", "1223123123"));		
//		Ebean.save(new User(3, "email3", "paww", "Isak3", "Prytz", "Gatan3", "2342", "bönstad", "1223123123"));
//		Ebean.save(new User(4, "email4", "paww", "Isak4", "Prytz", "Gatan4", "2342", "bönstad", "1223123123"));			
		
		return ok("Skapat fejkdata, Users: " +  ", Products:  ");
		
	}
    
}
