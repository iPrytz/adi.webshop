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
	
	public ProductsInOrder(){
		this.id = 0;
		this.order = null;
		this.quantity = 0;
		this.product = null;
	}
	
	public ProductsInOrder(int id, Order order, int quantity, Product product) {
		this.id = id;
		this.order = order;
		this.quantity = quantity;
		this.product = product;
	}
}
