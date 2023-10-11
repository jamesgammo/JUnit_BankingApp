package testClasses;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FBCategory {
	private String CategoryName;
	private BigDecimal CategoryBudget;
	private BigDecimal CategorySpend;

	/* All code changed by Rory */

	public FBCategory() {
		CategoryName = "New Category";
		CategoryBudget = new BigDecimal("0.00");
		CategorySpend = new BigDecimal("0.00");
	}

	public FBCategory(String newTitle) {
		if (newTitle == null) {
			CategoryName = null;
			CategoryBudget = new BigDecimal("0.00");
			CategorySpend = new BigDecimal("0.00");
		} else {
			String concatenatedString = newTitle.substring(0, Math.min(newTitle.length(), 15));
			CategoryName = concatenatedString;
			CategoryBudget = new BigDecimal("0.00");
			CategorySpend = new BigDecimal("0.00");
		}

	}

	public String CategoryName() {
		if (CategoryName == "New Category") {
			return null;
		} else {
			return CategoryName;
		}

	}

	public BigDecimal CategoryBudget() {
		if (CategoryBudget == new BigDecimal("0.00")) {
			return null;
		} else {
			return CategoryBudget;
		}

	}

	public BigDecimal CategorySpend() {
		if (CategorySpend == new BigDecimal("0.00")) {

			return null;
		} else {
			return CategorySpend;
		}

	}

	public void setCategoryName(String newName) {

		if (newName.length() > 15) {
			CategoryName = CategoryName;
		} else {
			CategoryName = newName;
		}

	}

	public void setCategoryBudget(BigDecimal newValue) {

//1 means bigger, -1 means smaller, 0 means same
		if (newValue == null) {

		} else {
			boolean isIntegerFormat = newValue.scale() <= 0;
			if (isIntegerFormat == true) {

			} else {
				BigDecimal scaledValue = newValue.setScale(2, RoundingMode.HALF_UP);
				if (scaledValue.compareTo(new BigDecimal("0.00")) == 1) {
					CategoryBudget = scaledValue;
				}
			}
		}

	}

	public void addExpense(BigDecimal valueToAdd) {
		if (valueToAdd == null) {

		} else {
			valueToAdd = valueToAdd.setScale(2, RoundingMode.HALF_UP);
			int comparisonResult = valueToAdd.compareTo(BigDecimal.ZERO);
			if (comparisonResult < 0) {
				CategorySpend = CategorySpend;

			} else {
				CategorySpend = CategorySpend.add(valueToAdd);
			}
		}

	}

	public void removeExpense(BigDecimal valueToRemove) {
		if (valueToRemove == null) {

		} else {
			valueToRemove = valueToRemove.setScale(2, RoundingMode.HALF_UP);
			int comparisonResult = valueToRemove.compareTo(BigDecimal.ZERO);
			if (comparisonResult < 0) {
				CategorySpend = CategorySpend;

			} else {
				CategorySpend = CategorySpend.subtract(valueToRemove);
			}
		}

	}

	public void resetBudgetSpend() {
		CategorySpend = new BigDecimal("0.00");
	}

	public BigDecimal getRemainingBudget() {
		BigDecimal remainingBudget = CategoryBudget.subtract(CategorySpend);
		return remainingBudget;
	}

	@Override
	public String toString() {
		if (CategoryName() == null) {
			return null;
		} else if (CategorySpend() == null) {
			return null;
		} else {
			return CategoryName + "(£" + CategoryBudget.toPlainString() + ") - Est. £" + CategorySpend.toPlainString()
					+ " (£" + getRemainingBudget().toPlainString() + " remaining)";
		}

	}

}