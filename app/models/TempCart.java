package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class TempCart {
	@Id
	@GeneratedValue
	int id;
	private String sessionId;
	private int quantity;
	@OneToMany
	private List<Product> products = new ArrayList<Product>();
	
	public TempCart(){
		this.id = 0;
		this.sessionId = null;
		this.quantity = 0;
		this.products = null;
	}
	
	public TempCart(String sessionId, int quantity, Product products) {
		this.id = 0;
		this.sessionId = sessionId;
		this.quantity = quantity;
		this.products.add(products);
	}

	public int getId() {
		return id;
	}

	public String sessionId() {
		return sessionId;
	}

	public int getQuantity() {
		return quantity;
	}

	public List<Product> getProducts() {
		return products;
	}
}

	

