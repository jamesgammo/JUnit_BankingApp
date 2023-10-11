package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import testClasses.FBCategory;

/* All tests created by Rory */

class FBCategoryTests {

	@Test
	public void testDefaultConstructor() {
		FBCategory category = new FBCategory();

		// Verify the default values
		assertEquals(null, category.CategoryName());
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());
	}

	@Test
	public void testDefaultConstructorbig() {
		String categoryName = "Thisismorethan15char";
		FBCategory category = new FBCategory(categoryName);
		assertEquals("Thisismorethan1", category.CategoryName());
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());

	}

	@Test
	public void testConstructorWithParameters() {
		String categoryName = "TestConst";
		BigDecimal budget = new BigDecimal("100.00");
		FBCategory category = new FBCategory(categoryName);
		category.setCategoryBudget(budget);
		// Verify the values
		assertEquals(categoryName, category.CategoryName());
		assertEquals(budget, category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());

	}

	@Test
	public void testNullConstructorWithName() {
		String categoryName = "TestConst";
		FBCategory category = new FBCategory(categoryName);
		// Verify the values
		assertEquals(categoryName, category.CategoryName());
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());

	}

	@Test
	public void testCategoryNameWhenEmpty() {
		FBCategory category = new FBCategory();
		// Verify the category name is null
		assertNull(category.CategoryName());

	}

	@Test
	public void getvalidnamebudgetandspend() {
		FBCategory category = new FBCategory();
		// Verify the default values
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());

	}

	@Test
	public void testsetcatname() {
		FBCategory category = new FBCategory();
		category.setCategoryName("TestCatName");
		assertEquals("TestCatName", category.CategoryName());

	}

	@Test
	public void testsetinvalidcatname() {
		FBCategory category = new FBCategory();
		category.setCategoryName("TestCatNamebutitisfartoolong");
		assertEquals(null, category.CategoryName());

	}

	@Test
	public void testsetnewcatname() {
		FBCategory category = new FBCategory("TestConst");
		category.setCategoryName("TestCatName");
		assertEquals("TestCatName", category.CategoryName());
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());
	}

	@Test
	public void testsetnewcatnamelong() {
		FBCategory category = new FBCategory("TestConst");
		category.setCategoryName("TestCatNamebutitsisfartoolong");
		assertEquals("TestConst", category.CategoryName());

	}

	public void testsetnewcatnameNULL() {
		FBCategory category = new FBCategory();
		category.setCategoryName(null);
		assertEquals("New Category", category.CategoryName());

	}

	@Test
	public void testsetcatbudget() {
		BigDecimal budget = new BigDecimal("20.00");
		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		assertEquals(budget, category.CategoryBudget());

	}

	@Test
	public void testsetcatbudgetnull() {
		FBCategory category = new FBCategory();
		category.setCategoryBudget(null);
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());

	}

	@Test
	public void testsetcatbudgetlong() {
		BigDecimal budget = new BigDecimal("10.999999");
		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		assertEquals(new BigDecimal("11.00"), category.CategoryBudget());

	}

	@Test
	public void testsetcatbudgetint() {
		BigDecimal budget = new BigDecimal("20");
		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		assertEquals(new BigDecimal("0.00"), category.CategoryBudget());

	}

	@Test
	public void testAddExpenseWithValidValue() {
		BigDecimal expense = new BigDecimal("50.00");
		FBCategory category = new FBCategory();
		category.addExpense(expense);

		// Verify the spend is updated correctly
		assertEquals(expense, category.CategorySpend());
	}

	@Test
	public void testAddExpenseWithInvalidValue() {
		BigDecimal expense = new BigDecimal("-5.00");
		BigDecimal initialSpend = new BigDecimal("20.00");
		FBCategory category = new FBCategory();
		category.addExpense(initialSpend); // Set the initial spend value
		BigDecimal expectedSpend = category.CategorySpend(); // Get the initial spend value
		category.addExpense(expense);

		// Verify the spend remains unchanged
		assertEquals(expectedSpend, category.CategorySpend());
	}

	@Test
	public void testAddNullExpense() {
		BigDecimal expense = null;
		BigDecimal initialSpend = new BigDecimal("20.00");

		FBCategory category = new FBCategory();
		category.addExpense(initialSpend); // Set the initial spend value

		BigDecimal expectedSpend = category.CategorySpend(); // Get the initial spend value

		category.addExpense(expense);

		// Verify the spend remains unchanged
		assertEquals(expectedSpend, category.CategorySpend());
	}

	@Test
	public void testAddExpenseBigDecimalFormat() {
		BigDecimal expense = new BigDecimal("10.9999999");

		FBCategory category = new FBCategory();
		category.addExpense(expense);

		BigDecimal expectedSpend = new BigDecimal("11.00");

		// Verify the spend value matches the expected value
		assertEquals(expectedSpend, category.CategorySpend());
	}

	@Test
	public void testInvalidExpenseInitialization() {
		String invalidExpense = "100"; // Invalid expense format (integer instead of decimal)

		FBCategory category = new FBCategory();
		category.addExpense(new BigDecimal(invalidExpense));
	}

	@Test
	public void testRemoveExpense() {
		BigDecimal initialExpense = new BigDecimal("50.00");
		BigDecimal expenseToRemove = new BigDecimal("20.00");
		BigDecimal expectedRemainingBudget = new BigDecimal("30.00");

		FBCategory category = new FBCategory();
		category.addExpense(initialExpense);
		category.removeExpense(expenseToRemove);

		BigDecimal actualRemainingBudget = category.CategorySpend();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	@Test
	public void testRemoveExpenseWithInvalidValue() {
		BigDecimal expense = new BigDecimal("-5.00");
		BigDecimal initialSpend = new BigDecimal("20.00");

		FBCategory category = new FBCategory();
		category.addExpense(initialSpend); // Set the initial spend value

		category.removeExpense(expense);

		// Verify the spend remains unchanged
		assertEquals(initialSpend, category.CategorySpend());
	}

	@Test
	public void testRemoveExpense_NullExpense() {

		BigDecimal expense = null;

		FBCategory category = new FBCategory();
		BigDecimal initialCategorySpend = category.CategorySpend();

		category.removeExpense(expense);

		BigDecimal updatedCategorySpend = category.CategorySpend();

		assertEquals(initialCategorySpend, updatedCategorySpend);
	}

	@Test
	public void testRemoveExpensebig() {
		BigDecimal initialExpense = new BigDecimal("50.00");
		BigDecimal expenseToRemove = new BigDecimal("10.99999999");
		BigDecimal expectedRemainingBudget = new BigDecimal("39.00");

		FBCategory category = new FBCategory();
		category.addExpense(initialExpense);
		category.removeExpense(expenseToRemove);

		BigDecimal actualRemainingBudget = category.CategorySpend();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	@Test
	public void testRemoveExpenseint() {
		BigDecimal initialExpense = new BigDecimal("50.00");
		BigDecimal expenseToRemove = new BigDecimal("20");
		BigDecimal expectedRemainingBudget = new BigDecimal("30.00");

		FBCategory category = new FBCategory();
		category.addExpense(initialExpense);
		category.removeExpense(expenseToRemove);

		BigDecimal actualRemainingBudget = category.CategorySpend();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	@Test
	public void testResetBudgetSpend() {
		FBCategory category = new FBCategory();
		BigDecimal initialCategorySpend = category.CategorySpend();

		// Add some expenses to the category spend
		BigDecimal expense1 = new BigDecimal("10.00");
		BigDecimal expense2 = new BigDecimal("5.00");
		category.addExpense(expense1);
		category.addExpense(expense2);

		// Verify that the category spend is not zero
		assertEquals(initialCategorySpend.add(expense1).add(expense2), category.CategorySpend());

		// Reset the budget spend
		category.resetBudgetSpend();

		// Verify that the category spend is reset to zero
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());
	}

	@Test
	public void testResetnegBudgetSpend() {
		FBCategory category = new FBCategory();

		// Add some expenses to the category spend
		BigDecimal expense1 = new BigDecimal("10.00");
		BigDecimal expense2 = new BigDecimal("5.00");
		category.removeExpense(expense1);
		category.removeExpense(expense2);

		// Verify that the category spend is not zero

		// Reset the budget spend
		category.resetBudgetSpend();

		// Verify that the category spend is reset to zero
		assertEquals(new BigDecimal("0.00"), category.CategorySpend());
	}

	@Test
	public void testGetRemainingBudget_BudgetGreaterThanSpend() {
		BigDecimal budget = new BigDecimal("100.00");
		BigDecimal spend = new BigDecimal("50.00");

		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		category.addExpense(spend);

		BigDecimal expectedRemainingBudget = budget.subtract(spend);
		BigDecimal actualRemainingBudget = category.getRemainingBudget();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	public void testGetRemainingBudget_BudgetLessThanSpend() {
		BigDecimal budget = new BigDecimal("50.00");
		BigDecimal spend = new BigDecimal("190.00");

		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		category.addExpense(spend);

		BigDecimal expectedRemainingBudget = budget.subtract(spend);
		BigDecimal actualRemainingBudget = category.getRemainingBudget();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	public void testGetRemainingBudget_BudgetEqualsThanSpend() {
		BigDecimal budget = new BigDecimal("50.00");
		BigDecimal spend = new BigDecimal("50.00");

		FBCategory category = new FBCategory();
		category.setCategoryBudget(budget);
		category.addExpense(spend);

		BigDecimal expectedRemainingBudget = budget.subtract(spend);
		BigDecimal actualRemainingBudget = category.getRemainingBudget();

		assertEquals(expectedRemainingBudget, actualRemainingBudget);
	}

	@Test
	public void testToString() {
		String categoryName = "Shopping";
		BigDecimal categoryBudget = new BigDecimal("100.00");
		BigDecimal categorySpend = new BigDecimal("50.00");

		FBCategory category = new FBCategory();
		category.setCategoryName(categoryName);
		category.setCategoryBudget(categoryBudget);
		category.addExpense(categorySpend);

		String expectedString = "Shopping(£100.00) - Est. £50.00 (£50.00 remaining)";
		String actualString = category.toString();

		assertEquals(expectedString, actualString);
	}

	@Test
	public void testToString_NullCategory() {
		FBCategory category = new FBCategory();
		category.setCategoryBudget(new BigDecimal("100.00"));
		category.addExpense(new BigDecimal("50.00"));

		String actualString = category.toString();

		assertEquals(null, actualString);
	}

	@Test
	public void testToString_NullSpend() {
		String categoryName = "Shopping";
		BigDecimal categoryBudget = new BigDecimal("100.00");
		FBCategory category = new FBCategory();
		category.setCategoryName(categoryName);
		category.setCategoryBudget(categoryBudget);

		String actualString = category.toString();

		assertEquals("Shopping(£100.00) - Est. £0.00 (£100.00 remaining)", actualString);
	}

}