package DealerOn;

import java.text.DecimalFormat;
import java.util.ArrayList;


//product service will have a static API so it will not have to be instantiated and will make the code look cleaner
public class ProductService 
{
	//array list to hold all products as added to order
	private static ArrayList<Product> productList = new ArrayList<>();
	
	//variable to hold all taxes added to the entire order
	private static double totalTaxes=0;
	
	//variable to hold total printed onto receipt
	private static double orderTotal = 0;
	
	
	public static void addProductToList(String name, double price) 
	{	
		//creates and stores a new product and calculateTaxes method sets new price with taxes added
		productList.add(new Product(calculateTaxes(name, price), name));
	}
	
	//private helper method to help the addProduct method to make it look cleaner
	//SUPPOSED to be private, but forced to make public for unit tests
	public static double calculateTaxes(String name, double price) 
	{
			double itemTax = 0;
			double importTax = 0;
			
			//checks import tax
			if(name.contains("Imported") || name.contains("imported")) {importTax = (price*.05);}
			
			//checks which items are untaxed then simply ignoring them and taxing the others
			if(name.contains("Book") || name.contains("Food") || name.contains("Medical Product") 
			|| name.contains("book") || name.contains("food") || name.contains("medical product")) 
			{
				
			}
			//everything else gets a 10% tax
			else {itemTax = itemTax + (price*.1);}
			
			//rounds taxes to nearest tenthes place
			DecimalFormat df = new DecimalFormat("#.##");
			itemTax = Double.valueOf(df.format(itemTax));
			importTax = Double.valueOf(df.format(importTax));
			
			//arbitrary fix to round to nearest 5 cents for itemTax
			itemTax = itemTax*20;
			itemTax = Math.ceil(itemTax);
			itemTax = itemTax/20;
			
			//arbitrary fix to round to nearest 5 cents for importTax
			importTax = importTax*20;
			importTax = Math.ceil(importTax);
			importTax = importTax/20;
			
			//stores sales taxes added onto each item
			totalTaxes = totalTaxes + itemTax + importTax;
			
			return Double.valueOf(df.format(price+itemTax+importTax));
		
	}
	
	//printReceipt algorithm takes a nested for loop, so it is a little inefficient at O(n^2) so it doesn't scale well
	public static void printReceipt() 
	{
		//list to be printed onto receipt
		ArrayList<Product> receiptList = new ArrayList<>();
		
		boolean isOnReceipt = false;
		
		//cycles through list to add products to the receipt, this is necessary because the program requires
		//if there is more than one quantity of an item, combine them on the receipt
		for(int i=0;i<productList.size();i++) 
		{
				for(int j=0; j<receiptList.size();j++) 
				{
					//finds item in receipt list then increments the count to be printed onto receipt
					if(receiptList.get(j).getName().equalsIgnoreCase(productList.get(i).getName())) 
					{
						receiptList.get(j).setCount(receiptList.get(j).getCount()+1);
						isOnReceipt = true; break;
					}
				}
				
				//if it's not on the receipt, then add it. written long hand for readability
				if(isOnReceipt==false){receiptList.add(productList.get(i));}
		}
		
		//finally prints receipt
		for(Product receipt: receiptList) 
		{
			//perfects the printing. Example only wants special quantity format print if quantity is > 1
			if(receipt.getCount()==1) {
				System.out.println(receipt.getName() + " : " + receipt.getPrice());
			} else {
			System.out.println(receipt.getName() + " : " + receipt.getPrice()*receipt.getCount() + 
					" (" + receipt.getCount() + " @ " + receipt.getPrice() + ")");
			}
			
			//multiplies the price of the item by the count on the order
			orderTotal = orderTotal + (receipt.getPrice()*receipt.getCount());
			
		}
		
		System.out.printf("Sales Tax: "+ "%.2f" +"\n", totalTaxes);
		System.out.printf("Total: "+ "%.2f" +"\n", orderTotal);
		
		//clears list after printing receipt for easy reuse
		productList.clear();
		
	}
	
	
}