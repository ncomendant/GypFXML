package gypfxml.core;

public class Outsourced extends Part {
    private String companyName;
    
    public Outsourced(String companyName) {
        this.companyName = companyName;
    }
	
    public String getCompanyName() {
            return companyName;
    }

    public void setCompanyName(String companyName) {
            this.companyName = companyName;
    }
}
