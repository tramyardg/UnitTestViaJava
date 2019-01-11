package dependency.breaking;

public class Sale {

    // Sale has a Display relationship
    private Display display;
    
    public Sale(Display display) {
	this.display = display;
    }
    
    public void scan(String barcode) {
	display.showLine("barcode " + barcode);
    }
    
}
