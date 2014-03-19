package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.categories.*;

public class CategoryCont extends Controller{

	 public static Result showCategories() {
	    	return ok(categories.render("Category"));
	    }
	 public static Result editCategories() {
		 return ok(editCategories.render("Edit Categories"));
	 }
	
	
}
