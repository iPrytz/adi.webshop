package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue
	private final int id;
	@ManyToOne
	private User user;
	Date date = null;
	private final double cost;
	private final double profit;
	@OneToMany(mappedBy="order", cascade= CascadeType.ALL)
	private List<ProductsInOrder> products;

	public Order() {
		this.id = 0;
		this.user = null;
		this.date = null;
		this.cost = 0;
		this.profit = 0;
		this.products = null;
	}

	public Order(int id, User user, String date, double cost, double profit,
			List<ProductsInOrder> products) {
		this.id = id;
		this.user = user;
		convertToDate(date);
		this.cost = cost;
		this.profit = profit;
		this.products = products;
	}
	public Order(User user, String date, double cost, double profit,
			List<ProductsInOrder> products) {
		this.id = 0;
		this.user = user;
		convertToDate(date);
		this.cost = cost;
		this.profit = profit;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public double getCost() {
		return cost;
	}

	public double getProfit() {
		return profit;
	}

	public List<ProductsInOrder> getProducts() {
		return products;
	}
	public void setProducts(List<ProductsInOrder> products) {
		this.products = products;
	}

	private void convertToDate(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = sdf.parse(stringDate);
			this.date = date;

		} catch (ParseException e) {
			e.getMessage();
		}
	}

}
