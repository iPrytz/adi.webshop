package controllers;


import play.cache.Cache;
import play.db.jpa.Transactional;
import play.mvc.Security;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.webshop.*;
import views.html.products.*;

public class WebshopCont extends Controller {

    public static Result webshop() {
//    	// Access the cache
//    	News userNews = Cache.get(uuid+"item.key");
//    	if(userNews==null) {
//    		userNews = generateNews(uuid);
//    		Cache.set(uuid+"item.key", userNews );
//    	}

//    	session("sessionID", "");

    	
//    	if ("".equals(session("sessonID"))||session("sessionID") == null){	
//    	setSessoionUUID();
//    	};
    	
        return  ok(index.render("The Exception Webshop.", "Welcome!", null));
    }
    @Security.Authenticated(MyAuthenticator.class)
    public static Result admin(){
    	
    	if(session().get("username") == null){
    		return redirect(routes.UserCont.showLoginForm()); 
    	}
    	
    	return ok(addProducts.render(""));
    }
    
    
    
@Transactional    
public static void setSessoionUUID() {
	
	// Generate a unique ID
	String uuid=session("sessionId");
	if(uuid==null) {
		uuid=java.util.UUID.randomUUID().toString();
		session("sessionID", uuid);
	}
	
		
	}
    
}
