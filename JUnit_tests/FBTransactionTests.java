package tests;

import org.junit.Test;
import testClasses.FBTransaction;
import org.junit.Before;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

/* Code changed by Howard and Dapo */

public class FBTransactionTests {

	private FBTransaction transaction;

	@Before

	public void setUp() {
		transaction = new FBTransaction("Test", new BigDecimal("10.00"), 1);

	}

	@Test
	//Testing Null FBTransaction Constructor
	public void testNullconstructor() {
		FBTransaction nulltest = new FBTransaction(null, null, null, null);
		nulltest.setName(null);
		assertNull(nulltest.getName());
		assertNull(nulltest.getValue());
		assertNull(nulltest.getCategory());
		assertNull(nulltest.getTimestamp());

	}

	@Test
	//Testing that name and value and not null
	public void isCompleteTwoValues() {
		assertTrue(transaction.isComplete(transaction));

	}

	@Test
	//Test name is null and value is not null
	public void nameIsNullValueSet() {
		transaction = new FBTransaction(null, new BigDecimal("10.00"), 1);
		assertFalse(transaction.isComplete(transaction));

	}

	@Test
	//Test that name is not null and value is null
	public void valueIsNullNameSet() {
		transaction = new FBTransaction("Test", null, 1);
		assertFalse(transaction.isComplete(transaction));

	}

	@Test
	//Test that name and value are both null
	public void isCompleteFalse() {
		transaction = new FBTransaction(null, null, 1);
		assertFalse(transaction.isComplete(transaction));

	}

	@Test
	//Test that all Transaction values are null
	public void nullTest() {
		FBTransaction nulltest = new FBTransaction(null, null, null, null);
		nulltest.setName("Failed test");
		assertEquals("Failed test", nulltest.getName());
		assertNull(nulltest.getValue());
		assertNull(nulltest.getCategory());
		assertNull(nulltest.getTimestamp());

	}

	@Test
	//Test that default contructor sets name to "Pending Transaction"
	public void defaultConstructor() {
		FBTransaction empty = new FBTransaction();
		assertEquals("[Pending FBTransaction]", empty.getName());

	}

	@Test
	//Testing getName function
	public void testGetName() {
		assertEquals("Test", transaction.getName());

	}

	@Test
	//Testing setName function
	public void testSetName() {
		transaction.setName("Paul");
		assertEquals("Paul", transaction.getName());

	}

	@Test
	//Testing truncation of transaction name in setName function
	public void testTruncateString() {
		String output = transaction.setName("This is a string that needs to be truncated");
		String actual = "This is a string that nee";
		assertEquals(output, actual);

	}

	@Test
	//Testing that transaction name can be set to null
	public void testSetNameNull() {
		transaction.setName(null);
		assertNull(transaction.getName());

	}

	@Test
	//Testing getValue function
	public void testGetValue() {
		assertEquals(new BigDecimal("10.00"), transaction.getValue());

	}

	@Test
	//Testing setValue function
	public void testSetValue() {
		transaction.setValue(new BigDecimal("20.00"));
		assertEquals(new BigDecimal("20.00"), transaction.getValue());

	}

	@Test(expected = IllegalArgumentException.class)
	//Testing that value cannot be set to zero via setValue function
	public void testSetValueZero() {
		transaction.setValue(BigDecimal.ZERO);

	}

	@Test(expected = IllegalArgumentException.class)
	//Testing that value cannot be set to a negative number via setValue function
	public void testSetValueNegative() {
		transaction.setValue(new BigDecimal("-10.00"));

	}

	@Test
	//Testing getCategory function
	public void testGetCategory() {
		assertSame(1, transaction.getCategory());

	}

	@Test
	//testing setCategory function
	public void testSetCategory() {
		transaction.setCategory(2);
		assertSame(2, transaction.getCategory());

	}

	@Test(expected = IllegalArgumentException.class)
	//Testing category cannot be set to a negative number via setCategory function
	public void testSetCategoryNegative() {
		transaction.setCategory(-1);

	}

	@Test
	//Testing getTimestamp function
	public void testGetTimestamp() {

		assertNotNull(transaction.getTimestamp());

	}

	@Test(expected = IllegalArgumentException.class)
	//Testing setTimestamp function with timestamp set to NULL
	public void emptyTimevar() {
		transaction.setTimestamp(null);
		transaction.getTimestamp();

	}

	@Test
	//Testing toString function
	public void testToString() {
		assertEquals("Test - £10.00", transaction.toString());
		
	}

	@Test
	//Testing that toString function still returns a string if a value/s is null
	public void valueNotset() {

		FBTransaction valueNotSet = new FBTransaction(null, new BigDecimal("5"), 2);

		assertNotNull(valueNotSet.toString());

	}

}