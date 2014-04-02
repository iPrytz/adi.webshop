package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn; 
import javax.persistence.OneToMany;

@Entity
public final class Product {
	@Id
	@GeneratedValue
	private final int id;
	private final String name;
	private final String description;
	private final double cost;
	private final double RRP;
	@ManyToMany
	@JoinTable(name="category_product",
    joinColumns = @JoinColumn(name = "products_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductsInCart> productsInCart;

	
	Product() {
		this.id = 0;
		this.name = "";
		this.description = null;
		this.cost = 0;
		this.RRP = 0;
		this.categories = null;
		this.productsInCart = null;
	}
	public Product(int id, String name, double cost, double RRP) {
		this.id = id;
		this.name = name;
		this.description = null;
		this.cost = cost;
		this.RRP = RRP;
		this.categories = null;
		this.productsInCart = null;
	}
	public Product(String name, String desc, double cost, double RRP) {
		this.id = 0;
		this.name = name;
		this.description = desc;
		this.cost = cost;
		this.RRP = RRP;
		this.categories = null;
		this.productsInCart = null;
	}

	public Product(int id, String name, String description, double cost,
			double RRP, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.RRP = RRP;
		this.categories = categories;
		this.productsInCart = null;
	}

	public Product(String name, String description, double cost, double RRP,
			List<Category> categories) {
		this.id = 0;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.RRP = RRP;
		this.categories = categories;
		this.productsInCart = null;
	}

	@Override
	public String toString() {
		return "Product ID: " + getId() + "\nName: " + getName()
				+ "\nDescription: " + getDescription() + "\nCost: " + getCost()
				+ "\nRRP: " + getRRP() + "\nCategories: " + getCategories()
				+ "\n";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getRRP() {
		return RRP;
	}

	public double getCost() {
		return cost;
	}
	public List<Category> getCategories() {
		return categories;
	}
}
