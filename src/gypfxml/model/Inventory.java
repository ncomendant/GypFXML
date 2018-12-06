package gypfxml.model;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Product> products;
	private ArrayList<Part> allParts;
	
	public Inventory() {
		products = new ArrayList<>();
		allParts = new ArrayList<>();
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public boolean removeProduct(int productID) {
		Product product = lookupProduct(productID);
		if (product != null) {
			products.remove(product);
			return true;
		} else {
			return false;
		}
	}
	
	public Product lookupProduct(int productID) {
		for (Product product : products) {
			if (productID == product.getProductId()) {
				return product;
			}
		}
		return null;
	}
	
	public void updateProduct(int productID) {
		
	}
	
	public void addPart(Part part) {
		allParts.add(part);
	}
	
	public boolean deletePart(Part part) {
		return allParts.remove(part);
	}
	
	public Part lookupPart(int partID) {
		for (Part part : allParts) {
			if (part.getPartID() == partID) {
				return part;
			}
		}
		return null;
	}
	
	public void updatePart(int partID) {
		
	}
	
}
