package com.tacoshop;

/**
 * Purchase POJO, data model for our Purchase object
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
import java.math.BigDecimal;

public class Purchase {

	/** total cost of purchase */
	private BigDecimal total;
	
	/** number of tacos included in purchase */
	private int tacoCount;
	
	/** purchase contains drink */
	private boolean drinkIncluded;
	
	/** total discount percentage on purchase, in decimal form */
	private double discount;
	
	/**
	 * Constructor allowing us to initialize some values in testing. Always start with no discount.
	 * 
	 * @param total puchase cost
	 * @param tacoCount number of tacos in purchase
	 * @param drinkIncluded if drink is included in purchase
	 */
	public Purchase(BigDecimal total, int tacoCount, boolean drinkIncluded) {
		this.total = total;
		this.tacoCount = tacoCount;
		this.drinkIncluded = drinkIncluded;
		this.discount = 0;
	}

	/**
	 * getter for purchase total
	 * 
	 * @return total cost of purchase in BigDecimal format, good practice for currency to avoid floating point issues
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * setter for purchase total
	 * 
	 * @param total total cost of purchase to be set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * getter for taco count included in purchase
	 * 
	 * @return int number of tacos
	 */
	public int getTacoCount() {
		return tacoCount;
	}

	/**
	 * setter for taco count in purchase
	 * 
	 * @param tacoCount number of tacos
	 */
	public void setTacoCount(int tacoCount) {
		this.tacoCount = tacoCount;
	}

	/**
	 * boolean flag indicating if a drink is included as part of the purchase
	 * 
	 * @return boolean true/false if drink is included
	 */
	public boolean isDrinkIncluded() {
		return drinkIncluded;
	}

	/**
	 * setter for boolean flag if drink is included
	 * 
	 * @param drinkIncluded is drink included
	 */
	public void setDrinkIncluded(boolean drinkIncluded) {
		this.drinkIncluded = drinkIncluded;
	}
	
	/**
	 * getter for discount amount of purchase
	 * 
	 * @return percentage discount in decimal format
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * setter for discount amount of purchase
	 * 
	 * @param discount amount of discount in decimal format
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
