package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testClasses.FBApp;
import testClasses.FBCategory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Creates a single instance of FBAPP to be used for all tests during this class 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/* Everything below changed by James up unit JUnit tests changed by Jamie noted by Jamie */
class FBAppTests {

	// Setting up input and output steams
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // Output steam
	private final PrintStream originalOut = System.out; // Original stream

	// Assign all variables
	@BeforeEach
	public void setUP() {
		FBApp.main(null);
	}

	// Create new outContent steam for user outputs
	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	// Flush output steam for new data tests
	@AfterEach
	public void clearStreams() {
		try {
			outContent.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Reset to the original output stream
	@AfterAll
	public void cleanUpStreams() {
		System.setOut(originalOut);
	}

	// Main method tests

	@Test
	// Not: Correct Function
	void Registered() {
		System.out.println("test 1: Registered");
		String input = "T\n";
		InputStream transaction = new ByteArrayInputStream(input.getBytes());
		System.setIn(transaction);
		FBApp.mainTest();
		// Means that the correct number of transactions were printed
		assertEquals(FBApp.x, FBApp.UserTransactions.size());
	}

	@Test
	// Not: Unregistered Function
	void Unregistered() {
		System.out.println("test 2: Unregistered");
		String input = "Z\n";
		InputStream transaction = new ByteArrayInputStream(input.getBytes());
		System.setIn(transaction);
		FBApp.mainTest();
		assertEquals(1, FBApp.flag);
	}

	@Test
	// Registered Function with space
	void RegisteredSpace() {
		System.out.println("test 3: With Space");

		// Testing with T (print transactions) as this has been previously tested to
		// only isolate a single test
		String input = "T \n";
		InputStream transaction = new ByteArrayInputStream(input.getBytes());
		System.setIn(transaction);
		FBApp.mainTest();
		assertEquals(FBApp.x, FBApp.UserTransactions.size());
	}

	@Test
	// New existing Category "Bills"
	void RegisteredCategory() {
		System.out.println("test 4: Registered Category");

		String prompts = "N\nBills\n120.00\nX\n"; // Concatenate all input into a single string
		InputStream inputStream = new ByteArrayInputStream(prompts.getBytes());
		System.setIn(inputStream);

		FBApp.mainTest();
		/*
		 * System.setIn(catNameInput); // Category Name System.setIn(budgetInput); //
		 * Category Budget
		 */

		assertTrue(FBApp.validName);
	}

	@Test
	// Change to non existent category
	void changeTransactionCategoryInvalid() {
		System.out.println("test 5: Changing transaction category V");

		// Changing transaction rock city drinks (6) to category (7) - non existent
		String input = "C\n7\n7\n";
		InputStream transaction = new ByteArrayInputStream(input.getBytes());

		System.setIn(transaction);
		FBApp.mainTest();
		assertFalse(FBApp.changeCat);
	}

	@Test
	// Change to non existent category
	void changeTransactionCategoryValid() {
		System.out.println("test 6: Changing transaction category I");

		// Changing transaction rock city drinks (6) to category (2)
		String input = "C\n6\n2\n";
		InputStream transaction = new ByteArrayInputStream(input.getBytes());

		System.setIn(transaction);
		FBApp.mainTest();
		assertTrue(FBApp.changeCat);
	}

	// List Transaction tests

	@Test
	// Testing all transactions
	void knownCatAndTransaction() {
		System.out.println("test 7: Changing transaction category");
		FBApp.ListTransactions();
		assertEquals(7, FBApp.x);
	}

	@Test
	// Testing Unknown category and transaction
	void UnknownCatAndTransaction() {
		System.out.println("test 8: Null transaction Category");
		String input = "N\n\n200.00\n";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(4, FBApp.UserCategories.size());
	}

	// Category overview tests

	@Test
	// Testing all Categories
	void knownCategories() {
		System.out.println("test 9: Testing categories");
		FBApp.CategoryOverview();
		assertEquals(FBApp.UserCategories.size(), FBApp.catSize);
	}

	// List transactions in a category

	@Test
	void validCat() {
		System.out.println("test 10: Valid Category");
		String input = "3\n";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(FBApp.ListTransactionSize(3), FBApp.called);
	}

	@Test
	void InvalidCat() {
		System.out.println("test 11: Invalid Category check");
		String input = "99\n";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertTrue(FBApp.outOfRange);

	}

	// AddCategory() Tests

	// Testing Valid Inputs
	@Test
	void testValidInputsAC() {
		System.out.println("test 12: Valid New Category check");
		String input = "N\nTravel\n50";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(FBApp.UserCategories.size(), FBApp.catSize);
	}

	// Testing trying to add a category with an existing name
	@Test
	void testRepeatCategoryAC() {
		System.out.println("test 13: Repeat Category Check");
		String input = "N\nBills\n50";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertTrue(FBApp.validName);
	}

	// Testing the truncation of long category names
	@Test
	void testLongCategoryNameAC() {
		System.out.println("test 14: Truncation of Long Category Name Check");
		String input = "N\nTravel in Nottingham\n50";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		FBCategory newCat;
		if (FBApp.catSize != 5) {
			fail("new category not added");
		}
		assertEquals(FBApp.catName, "Travel in Notti");
	}

	/* Tests below created by Jamie */

	// Add Transaction Tests

	// Testing Valid Inputs
	@Test
	void testValidInputsAT() {
		System.out.println("test 15: valid input check");
		String input = "A\nLidl\n12.99\nN";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		int tempSize = FBApp.UserTransactions.size();
		FBApp.mainTest();
		assertEquals(FBApp.UserTransactions.size(), tempSize + 1);
	}

	// Testing the truncation of long names
	@Test
	void testLongTransactionNameAT() {
		System.out.println("test 16: Truncation of Long Transaction Name Check");
		String input = "A\nCinema in Nottingham\n5.99\nN";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(FBApp.transName, "Cinema in Notti");
	}

	// Testing invalid price value
	@Test
	void testInvalidValueAT() {
		System.out.println("test 17: Truncation of Long Transaction Name Check");
		String input = "A\nCinema in Nottingham\nxyz";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertFalse(FBApp.validValue);
	}

	// Testing valid category placement
	@Test
	void testValidCategoryAT() {
		System.out.println("test 18: Truncation of Long Transaction Name Check");
		String input = "A\nCinema\n6.99\nY\n3";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(FBApp.UserTransactions.get(7).getCategory(), 2);
	}

	// Test invalid category
	@Test
	void testInvalidCategoryAT() {
		System.out.println("test 19: Truncation of Long Transaction Name Check");
		String input = "A\nCinema\n6.99\nY\nxyz";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		int transNum = FBApp.UserTransactions.size();
		FBApp.mainTest();
		assertEquals(FBApp.UserTransactions.get(transNum).getCategory(), 0);
	}

	// Change Transaction Category Tests
	// Testing valid inputs
	@Test
	void testValidInputsCTC() {
		System.out.println("test 20: valid input check");
		String input = "C\n5\n2";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertEquals(FBApp.UserTransactions.get(4).getCategory(), 1);
	}

	// Testing invalid Transaction id
	@Test
	void testInvalidTransactionCTC() {
		System.out.println("test 21: valid input check");
		String input = "C\nxyz\n2";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertFalse(FBApp.changeCat);
	}

	// Testing out of bounds testing id
	@Test
	void testOutofBoundsTransactionCTC() {
		System.out.println("test 22: valid input check");
		String input = "C\n99\n2";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertFalse(FBApp.changeCat);
	}

	// testing invalid category id
	@Test
	void testInvalidCategoryCTC() {
		System.out.println("test 23: valid input check");
		String input = "C\n5\nxyz";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertFalse(FBApp.changeCat);
	}

	// testing out of bounds category id
	@Test
	void testOutofBoundsCategoryCTC() {
		System.out.println("test 24: valid input check");
		String input = "C\n5\n99";
		InputStream nullTest = new ByteArrayInputStream(input.getBytes());
		System.setIn(nullTest);
		FBApp.mainTest();
		assertFalse(FBApp.changeCat);
	}

}
