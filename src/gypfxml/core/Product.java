package gypfxml.core;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
	private ObservableList<Part> associatedParts;
        private SimpleIntegerProperty productID;
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;
	private SimpleIntegerProperty inStock;
	private int min;
	private int max;
        
        public Product() {
            associatedParts = FXCollections.observableArrayList();
            productID = new SimpleIntegerProperty();
            name = new SimpleStringProperty();
            price = new SimpleDoubleProperty();
            inStock = new SimpleIntegerProperty();
            min = 0;
            max = 0;
        }
	
	public void setName(String name) {
            this.name.set(name);
	}
	
	public String getName() {
            return name.get();
	}
	
	public void setPrice(double price) {
            this.price.set(price);
	}
	
	public double getPrice() {
            return price.get();
	}
	
	public void setInStock(int inStock) {
            this.inStock.set(inStock);
	}
	
	public int getInStock() {
            return this.inStock.get();
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
        
        public void setProductID(int productID) {
            this.productID.set(productID);
	}
	
	public int getProductID() {
            return productID.get();
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
}
