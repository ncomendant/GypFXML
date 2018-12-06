package gypfxml.core;

import java.util.ArrayList;

public class Product {
	private ArrayList<Part> associatedParts;
	private int productID;
	private String name;
	private double price;
	private int inStock;
	private int min;
	private int max;
	
	public Product() {
		associatedParts = new ArrayList<>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	
	public int getInStock() {
		return inStock;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public int getMax() {
		return max;
	}
	
	public void addAssociatedPart(Part part) {
		associatedParts.add(part);
	}
	
	public boolean removeAssociatedPart(int partID) {
		Part part = lookupAssociatedPart(partID);
		if (part != null) {
			associatedParts.remove(part);
			return true; 
		} else {			
			return false;
		}
	}
	
	public Part lookupAssociatedPart(int partID) {
		for (Part part : associatedParts) {
			if (part.getPartID() == partID) {
				return part;
			}
		}
		return null;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public int getProductId() {
		return productID;
	}
}
