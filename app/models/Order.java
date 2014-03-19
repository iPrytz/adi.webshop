package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Order
{

	private final int id;
	private User user;
	Date date = null;
	private final double cost;
	private final double profit;
	private final Map<Product, Integer> products;

	public Order(int id, User user, String date, double cost, double profit,
			Map<Product, Integer> products)
	{
		this.id = id;
		this.user = user;
		convertToDate(date);
		this.cost = cost;
		this.profit = profit;
		this.products = products;
	}

	public int getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	public Date getDate()
	{
		return date;
	}

	public double getCost()
	{
		return cost;
	}

	public double getProfit()
	{
		return profit;
	}

	public Map<Product, Integer> getProducts()
	{
		return products;
	}

	@Override
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return "\n------------------------------------------------" +
				"\nDate: "
				+ sdf.format(date) + "\nOrderID: " + id + "\n\nUser Contacts:"
				+ "\nEmail: " + user.getEmail() + "\nName: "
				+ user.getFirstname() + " " + user.getSurname() + "\nAdress: "
				+ user.getStreetAddress() + ", " + user.getPostcode() + ", "
				+ user.getTown() + "\nTelephoneNr: " + user.getPhonenumber()
				+ "\n\nProducts: \n" + productsToString()
				+ "\nProfit for order: " + profit + "\n\nTotal cost:" + cost
				+ "\n------------------------------------------------";
	}

	private String productsToString()
	{
		String productsString = "-----------\n";

		for (Map.Entry<Product, Integer> entry : products.entrySet())
		{

			productsString += "\nProductID: " + entry.getKey().getId()
					+ "\nName: " + entry.getKey().getName() + "\nCost: "
					+ entry.getKey().getCost() + "\nRRP: "
					+ entry.getKey().getRRP() + "\n\nQuantity: "
					+ entry.getValue() + "\n-----------\n";

		}

		return productsString;

	}

	private void convertToDate(String stringDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try
		{
			Date date = sdf.parse(stringDate);
			this.date = date;

		}
		catch (ParseException e)
		{
			e.getMessage();
		}
	}

}
