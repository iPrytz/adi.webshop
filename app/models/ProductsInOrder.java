package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductsInOrder {
	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	private Order order;
	private int quantity;
	@ManyToOne
	private Product product;

	public ProductsInOrder() {
		this.id = 0;
		this.order = null;
		this.quantity = 0;
		this.product = null;
	}

	public ProductsInOrder(Order order, int quantity, Product product) {
		this.id = 0;
		this.order = order;
		this.quantity = quantity;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public Order getOrder() {
		return order;
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
