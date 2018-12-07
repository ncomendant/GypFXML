package gypfxml.test;

import gypfxml.App;
import gypfxml.core.Inhouse;
import gypfxml.core.Outsourced;
import gypfxml.core.Part;
import gypfxml.core.Product;
import java.util.List;
import java.util.UUID;

public class Test {
    public static void addRandomParts(App app, int count) {
        for (int i = 0; i < count; i++) {
            Part part;
            if (Math.random() < 0.5) {
                part = new Inhouse(Test.randNum(0, 1000));
            } else {
                part = new Outsourced(UUID.randomUUID().toString());
            }
            part.setInStock(Test.randNum(0, 99));
            part.setMin(Test.randNum(0, 50));
            part.setMax(Test.randNum(part.getMin(), 100));
            part.setPrice(Test.randNum((double)part.getMin(), (double)part.getMax(), 2));
            part.setName(UUID.randomUUID().toString());
            app.addPart(part);
        }
    }
    
    public static void addRandomProducts(App app, List<Part> parts, int count) {
        
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setInStock(Test.randNum(0, 99));
            product.setMin(Test.randNum(0, 50));
            product.setMax(Test.randNum(product.getMin(), 100));
            product.setPrice(Test.randNum((double)product.getMin(), (double)product.getMax(), 2));
            product.setName(UUID.randomUUID().toString());
            
            int partCount = Test.randNum(0, 5);
            for (int j = 0; j < partCount; j++) {
                int partIndex = Test.randNum(0, parts.size()-1);
                product.addAssociatedPart(parts.get(partIndex));
            }
            
            app.addProduct(product);
        }
    }
    
    private static int randNum(int min, int max) {
        int gap = max - min;
        return ((int)((Math.random()*gap)+min));
    }
    
    private static double randNum(double min, double max) {
        double gap = max - min;
        return (((Math.random()*gap)+min));
    }
    
    private static double randNum(double min, double max, int digitsAfterDecimal) {
        double num = Test.randNum(min, max); 
        return Test.roundNum(num, digitsAfterDecimal);
    }
    
    private static double roundNum(double num, int digitsAfterDecimal) {
        num *= Math.pow(10, digitsAfterDecimal);
        num = Math.round(num);
        num /= Math.pow(10, digitsAfterDecimal);
        return num;
    }
    
    private Test(){}
}
