package testClasses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class FBApp {

	public static ArrayList<FBTransaction> UserTransactions;
	public static ArrayList<FBCategory> UserCategories;
	public static int x;
	public static int y;
	public static int z;

	// Flags used for testing
	public static int flag = 0;
	public static boolean changeCat = false;
	public static boolean validName = false;
	public static boolean trunc = false;
	public static boolean validValue = true;
	public static int catSize;
	public static int tranSize;
	public static int called = 0;
	public static boolean outOfRange = false;

	public static String catName = "";
	public static String transName = "";

	public static void main(String[] args) {
		UserCategories = new ArrayList<FBCategory>();
		UserTransactions = new ArrayList<FBTransaction>();

		// SETUP EXAMPLE DATA //
		UserCategories.add(new FBCategory("Unknown"));
		FBCategory BillsCategory = new FBCategory("Bills");
		BillsCategory.setCategoryBudget(new BigDecimal("120.00"));
		UserCategories.add(BillsCategory);
		FBCategory Groceries = new FBCategory("Groceries");
		Groceries.setCategoryBudget(new BigDecimal("75.00"));
		UserCategories.add(Groceries);
		FBCategory SocialSpending = new FBCategory("Social");
		SocialSpending.setCategoryBudget(new BigDecimal("100.00"));
		UserCategories.add(SocialSpending);

		// Creating a new list of transaction data
		UserTransactions.add(new FBTransaction("Rent", new BigDecimal("850.00"), 0));
		UserTransactions.add(new FBTransaction("Phone Bill", new BigDecimal("37.99"), 1));
		UserTransactions.add(new FBTransaction("Electricity Bill", new BigDecimal("75.00"), 1));
		UserTransactions.add(new FBTransaction("Sainsbury's Checkout", new BigDecimal("23.76"), 2));
		UserTransactions.add(new FBTransaction("Tesco's Checkout", new BigDecimal("7.24"), 2));
		UserTransactions.add(new FBTransaction("RockCity Drinks", new BigDecimal("8.50"), 3));
		UserTransactions.add(new FBTransaction("The Mooch", new BigDecimal("13.99"), 3));

		// Assigns a transaction category for each transaction
		for (int x = 0; x < UserTransactions.size(); x++) {
			FBTransaction temp = UserTransactions.get(x);
			int utCat = temp.getCategory();
			FBCategory temp2 = UserCategories.get(utCat);
			temp2.addExpense(temp.getValue());
			UserCategories.set(utCat, temp2);
		}

		//mainTest();

	}

	/* Code changed by James */
	public static void mainTest() {
		// MAIN FUNCTION LOOP
		CategoryOverview();
		System.out.println(
				"\nWhat do you want to do?\n O = [O]verview, T = List All [T]ransactions, [num] = Show Category [num], C = [C]hange Transaction Category, A = [A]dd Transaction, N = [N]ew Category, X = E[x]it");
		Scanner in = new Scanner(System.in);
		//while (in.hasNextLine()) {
			String s = in.next();
			try {
				if (s.equals("T")) {
					ListTransactions();
				} else if (s.equals("O")) {
					CategoryOverview();
				} else if (s.equals("C")) {
					ChangeTransactionCategory(in);
				} else if (s.equals("N")) {
					AddCategory(in);
				} else if (s.equals("A")) {
					AddTransaction(in);
				} else if (s.equals("X")) {
					System.out.println("Goodbye!");
				}

				/* Code changed by James */
				else {
					try {
						Integer.parseInt(s);
						ListTransactionsForCategory((int) Integer.parseInt(s));

					} catch (NumberFormatException e) {
						System.out.println("Command not recognised");
						flag = 1;
					}
				}

			} catch (Exception e) {
				System.out.println("Something went wrong: " + e.toString() + "\n");
			}

			System.out.println(
					"\nWhat do you want to do?\n O = [O]verview, T = List All [T]ransactions, [num] = Show Category [num], C = [C]hange Transaction Category, A = [A]dd Transaction, N = [N]ew Category, X = E[x]it");

		//}

		in.close();

		return;

	}

	/* Code changed by James */
	public static void ListTransactions() {
		for (x = 0; x < UserTransactions.size(); x++) {
			FBTransaction temp = UserTransactions.get(x);
			int transCat = temp.getCategory();
			System.out.println((x + 1) + ") " + temp.getName() + "(" + UserCategories.get(transCat).CategoryName() + ")"
					+ " - £" + temp.getValue().toString());
		}
		tranSize = UserCategories.size();
	}

	public static void CategoryOverview() {
		for (y = 0; y < UserCategories.size(); y++) {
			FBCategory temp = UserCategories.get(y);
			System.out.println((y + 1) + ") " + temp.toString());
		}
		catSize = UserCategories.size();
	}

	/* Code changed by James */
	public static void ListTransactionsForCategory(int chosenCategory) {
		// System.out.print(chosenCategory);
		if (chosenCategory > UserTransactions.size()) {
			outOfRange = true;
			System.out.println("Invalid Category ID");
		}
		for (z = 0; z < UserTransactions.size(); z++) {
			try {
				FBTransaction temp = UserTransactions.get(z);
				if (temp.getCategory() == chosenCategory) {
					System.out.println("Category : " + UserCategories.get(chosenCategory).CategoryName());

					System.out.println((z + 1) + ") " + temp.toString());
					called = called + 1;
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid category entered\n");
				return;
			}
		}
	}

	// Used for testing
	public static int ListTransactionSize(int category) {
		int size = 0;
		for (int j = 0; j < UserTransactions.size(); j++) {
			FBTransaction temp = UserTransactions.get(j);
			if (temp.getCategory() == category) {
				size = size + 1;
			}
		}

		return size;
	}

	/* Function code added by Jamie */
	private static void AddTransaction(Scanner in) {
		System.out.println("What is the title of the transaction?");

		in.nextLine(); // to remove read-in bug
		String title = in.nextLine();
		if (title == "") {
			System.out.println("Error adding new Transaction - Null Title");
			return;
		} else if (title.length() > 15) {
			trunc = true;
			title = title.substring(0, 15);
		}
		System.out.println("What is the value of the transaction?");
		BigDecimal tvalue;
		try {
			tvalue = new BigDecimal(in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Error adding new transaction - Invalid Value");
			validValue = false;
			return;
		}
		System.out.println("Would you like to add this transaction to a Category? [Y]es or [N]o");
		String catYN = in.nextLine();
		int tempCat = 0;
		if (catYN.equals("Y")) {
			System.out.println("Which category will it move to?");
			CategoryOverview();
			try {
				tempCat = Integer.parseInt(in.nextLine()) - 1;
				if (tempCat < 0 || tempCat > UserCategories.size()) {
					System.out.println("Invalid category");
					System.out.println("Transaction will be placed in 'Unknown' by default");
					tempCat = 0;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid category");
				System.out.println("Transaction will be placed in 'Unknown' by default");
				tempCat = 0;
			}

		} else {
			System.out.println("Transaction will be placed in 'Unknown' by default");
		}
		UserTransactions.add(new FBTransaction(title, tvalue, tempCat));
		transName = title;
		String output = title + " (" + tvalue + ") was added to " + UserCategories.get(tempCat);
		System.out.println(output);
	}

	/* Function code added by James */
	private static void ChangeTransactionCategory(Scanner in) {
		int newCat = 0;
		System.out.println("Which transaction ID?");
		in.nextLine();
		int tID = Integer.parseInt(in.nextLine()) - 1;

		// Checks whether transaction ID exists
		try {
			if (tID < 0 || tID >= UserTransactions.size()) {
				System.out.println("Invalid transaction");
				changeCat = false;
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid transaction");
			changeCat = false;
			return;
		}

		// Get transaction expense + Category
		FBTransaction currentT = UserTransactions.get(tID);
		BigDecimal expense = currentT.getValue();
		int oldC = currentT.getCategory();

		System.out.println("\t- " + UserTransactions.get(tID).toString());
		System.out.println("Which category will it move to?");
		CategoryOverview();

		// Checks whether category currently exists
		int tempCat = 0;
		try {
			tempCat = Integer.parseInt(in.nextLine()) - 1;
		} catch (NumberFormatException e) {
			System.out.println("Invalid category");
			changeCat = false;
			return;
		}

		/* Code changed by James */
		if (tempCat < 0 || tempCat >= UserCategories.size()) {
			System.out.println("Invalid category");
			changeCat = false;
			return;
		}

		// Category does exist
		newCat = tempCat;
		// newCategory is either pre-existing or is now a new category
		FBTransaction temp = UserTransactions.get(tID);
		temp.setCategory(newCat);
		UserTransactions.set(tID, temp);
		FBCategory temp2 = UserCategories.get(newCat);
		temp2.addExpense(temp.getValue()); // Add expense should update the new category expenses
		UserCategories.set(newCat, temp2);

		// Passed JUnit test
		changeCat = true;

		// Prints categories
		FBCategory newCatT = UserCategories.get(newCat);
		System.out.println(newCatT.toString());

		FBCategory oldCatT = UserCategories.get(oldC);
		// Remove Expense
		oldCatT.removeExpense(expense);
		System.out.println(oldCatT.toString());
	}

	/* function code edited by James */
	private static void AddCategory(Scanner in) {

		System.out.println("What is the title of the category?");
		in.nextLine(); // to remove read-in bug
		String title = in.nextLine();
		FBCategory tempComp = new FBCategory(title);
		for (int i = 0; i < UserCategories.size(); i++) {
			FBCategory comp = UserCategories.get(i);
			if (tempComp.CategoryName().equals(comp.CategoryName())) {
				// This category already exists
				validName = true;
				System.out.println("Error adding new catagory");
				return;
			}
		}

		if (title.equals("")) {
			System.out.println("Error adding new catagory - Null Name");
			return;

		} else if (title.length() > 15) {
			title = title.substring(0, 15);
			trunc = true;

		}

		System.out.println("What is the budget for this category?");
		BigDecimal cbudget = new BigDecimal(in.nextLine());
		FBCategory temp = new FBCategory(title);
		temp.setCategoryBudget(cbudget);
		UserCategories.add(temp);
		System.out.println("[Category added]");
		catName = title;
		CategoryOverview();
	}

}
