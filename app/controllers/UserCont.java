package controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.*;
import views.html.webshop.index;

public class UserCont extends Controller {

	public static Result showLoginForm(){
		
		return ok(login.render("login"));
	}
	public static Result logout(){
		
		session().clear();
		
		return ok(index.render("The Exception Webshop.", "You've logged out", null));
	}
	
	@Transactional
	public static Result login(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String email = form.get("email")[0];
		String password = form.get("password")[0];
		
		boolean emailIsEmpty = "".equals(email);
		boolean passwordIsEmpty = "".equals(password);
		
		if(emailIsEmpty || passwordIsEmpty){
			if(emailIsEmpty){
				flash().put("username-empty", "yes");
			}
			if(passwordIsEmpty){
				flash().put("password-empty", "yes");
			}
			
			return redirect(routes.UserCont.showLoginForm());
		}
		
		TypedQuery<User> query = JPA.em().createQuery( "SELECT c FROM User c WHERE c.email = :email AND c.password = :password", User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		List<User> matchingUsers = query.getResultList();
		
		if(matchingUsers.size() == 1){
			session().put("username", email);
			
			return redirect(routes.WebshopCont.webshop());
		} else {
			flash().put("no-username-password-match", "yes");
			
			return redirect(routes.UserCont.showLoginForm());
		}
		
		
	}
	
	
	}
