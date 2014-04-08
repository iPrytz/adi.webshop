package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductsInCart {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	private User user;
	private int quantity;
	@ManyToOne
	private Product product;

	public ProductsInCart() {
		this.id = 0;
		this.user = null;
		this.quantity = 0;
		this.product = null;
	}

	public ProductsInCart(User user, int quantity, Product product) {
		this.id = 0;
		this.user = user;
		this.quantity = quantity;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
