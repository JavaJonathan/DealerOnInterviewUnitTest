package DealerOn;

public class Product 
{
	private double price = 0.0;
	private String name;
	// using a count variable to print count onto receipt 
	private int count = 1;
	
	
	Product(double price, String name)
	{
		this.price = price;
		this.name = name;
	}
	
	//will cycle through receipt list an increment count to count number of duplicate items
	public void setCount(int count) {this.count = count;}
	public int getCount() {return this.count;}
	
	public void setPrice(double price) {this.price = price;}
	public double getPrice() {return this.price;}
	
	public void setName(String name) { this.name = name;}
	public String getName() { return this.name;}

	
}
