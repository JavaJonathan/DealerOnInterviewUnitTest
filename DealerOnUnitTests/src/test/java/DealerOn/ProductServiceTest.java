package DealerOn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductServiceTest {

	//tests for the method that calculates the taxes added onto each item
	@Test
	void test() {
		Double actual = ProductService.calculateTaxes("imported soup", 12.99);
		Double expected = 14.94;
		
		assertEquals(expected, actual, "This method calculates the taxes on the item based on its classification");
		
	}

}
