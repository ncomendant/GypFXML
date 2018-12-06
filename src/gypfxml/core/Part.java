package gypfxml.core;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Part {
	private SimpleIntegerProperty partID;
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;
	private SimpleIntegerProperty inStock;
	private int min;
	private int max;
        
        public Part() {
            partID = new SimpleIntegerProperty();
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
	
	public void setPartID(int partID) {
            this.partID.set(partID);
	}
	
	public int getPartID() {
            return partID.get();
	}
}
