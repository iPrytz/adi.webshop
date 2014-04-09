package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public final class User {
	@Id
	@GeneratedValue
	private final int id;
	@Column(unique = true)
	private final String email;
	private final String password;
	private final String firstname;
	private final String surname;
	private final String streetAddress;
	private final String postcode;
	private final String town;
	private final String phonenumber;
	@OneToMany(mappedBy = "user")
	private List<ProductsInCart> productsInCart;
	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	public User() {
		this.id = 0;
		this.email = null;
		this.password = null;
		this.firstname = null;
		this.surname = null;
		this.streetAddress = null;
		this.postcode = null;
		this.town = null;
		this.phonenumber = null;
		this.productsInCart = null;
		this.orders = null;
	}

	public User(String email, String password, String firstname,
			String description, String streetAddress, String postcode, String town,
			String phonenumber) {
		this.id = 0;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.surname = description;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.town = town;
		this.phonenumber = phonenumber;
		this.orders = null;
	}

	public User(int id, String email, String password, String firstname,
			String description, String streetAddress, String postcode, String town,
			String phonenumber, List<Order> order) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.surname = description;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.town = town;
		this.phonenumber = phonenumber;
		this.orders = order;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getTown() {
		return town;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public List<ProductsInCart> getProductsInCart() {
		return productsInCart;
	}

	public void setProductsInCart(List<ProductsInCart> productsInCart) {
		this.productsInCart = productsInCart;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String toString() {
		String string;
		string = "Email / Username: " + getEmail() + "\nPassword: "
				+ getPassword() + "\nName: " + getFirstname() + " "
				+ getSurname() + "\nAddress: " + getStreetAddress() + " "
				+ getPostcode() + " " + getTown() + "\nPhonenumber: "
				+ getPhonenumber() + "\n";
		return string;
	}
}