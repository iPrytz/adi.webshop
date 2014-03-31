package controllers;
import models.User;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import views.html.webshop.admin;


public class MyAuthenticator extends play.mvc.Security.Authenticator{
	
	@Override
	public String getUsername(final Context ctx) {
		
		String user = ctx.session().get("username");

		if(user != null){
		if(user.equals("admin@admin")){
			return user;
		}}
	
			return null;
		
	}
	
	@Override
    public Result onUnauthorized(Context ctx) {
		
	
			return redirect(routes.UserCont.showLoginForm());
		
		
    }

}
