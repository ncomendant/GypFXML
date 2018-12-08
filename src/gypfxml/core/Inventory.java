package gypfxml.core;

import java.util.List;
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
                return products.remove(product);
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
	
	public void addPart(Part part) {
            allParts.add(part);
	}
	
	public boolean deletePart(Part part) {
            boolean removed = allParts.remove(part);
            //Products are not updated per directions from mentor on Course Chatter
            //updateAssociatedParts();
            return removed;
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
        
        //Swaps existing references in case part switched between Inhouse and Outsource
        public void updatePart(Part part) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getPartID() == part.getPartID()) {
                    allParts.set(i, part);
                    break;
                }
            }
            updateAssociatedParts();
        }
  
        private void updateAssociatedParts() {
            for (Product product : products) {
                List<Part> productParts = product.getAssociatedParts();
                for (int i = productParts.size()-1; i >= 0; i--) {
                    int associatedID = productParts.get(i).getPartID();
                    productParts.set(i, lookupPart(associatedID));
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
