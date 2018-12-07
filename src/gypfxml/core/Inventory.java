package gypfxml.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
	private ObservableList<Product> products;
	private ObservableList<Part> allParts;
	
	public Inventory() {
            products = FXCollections.observableArrayList();
            allParts = FXCollections.observableArrayList();
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
                    if (productID == product.getProductID()) {
                            return product;
                    }
            }
            return null;
	}
	
	public void updateProduct(int productID) {
	    //Defined solely to adhere to UML specifications
	}
        
        public void updateProduct(Product product) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductID()== product.getProductID()) {
                    products.set(i, product);
                    break;
                }
            }
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
            //Defined solely to adhere to UML specifications
	}
        
        public void updatePart(Part part) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getPartID() == part.getPartID()) {
                    allParts.set(i, part);
                    break;
                }
            }
        }
        
        public ObservableList<Product> getProducts() {
            return products;
        }
        
        public ObservableList<Part> getParts() {
            return allParts;
        }
}
