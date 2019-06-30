package DealerOn;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DealerOnUnitTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealerOnUnitTestsApplication.class, args);
		
		Scanner scanner = new Scanner(System.in);

		// infinite loop so user can continue to add products or print receipts until
		// they decide to close
		
		while (true) {

			System.out.println("Please either type 'Add a Product' or 'Print Receipt', if finished.");
			
			String userChoice = scanner.nextLine();

			// we will not use a switch case to use equalsIgnoreCase on user choice
			if (userChoice.equalsIgnoreCase("Add a Product")) {
				System.out.println("Please enter the name of the product you would like to buy.");
				String productName = scanner.nextLine();
				System.out.println("Please enter the cost of the product you would like to buy.");
				//bug fix where scanner does not clear after inputting a double. 
				//forcing it to read the next line then parsing to a double solves the issue
				Double productPrice = Double.parseDouble(scanner.nextLine());

				ProductService.addProductToList(productName, productPrice);
			} else if (userChoice.equalsIgnoreCase("Print Receipt")) {
				ProductService.printReceipt();
			} else {
				System.out.println("Error. Please input correct command.");
			}

		}

	}

}

