package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public final class Category {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int staffId;
	@ManyToMany
//(mappedBy = "categories")
	private List<Product> products;

	public Category(){}
	
	public Category(String name, int staffId){
		this.id = 0;
		this.name = name;
		this.staffId = staffId;
		this.products = null;
	}
	
	public Category(int id, String name, int staffId, List<Product> products) {
		this.id = id;
		this.name = name;
		this.staffId = staffId;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStaffId() {
		return staffId;
	}

	public List<Product> getProducts() {
		return products;
	}

}
