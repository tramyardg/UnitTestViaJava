package dependency.breaking;

public class Sale {

    // Sale has a Display relationship
    private IDisplay display;
    private IStorage storage;
    
    public Sale(IDisplay display) {
	this.display = display;
    }
    
    public Sale(IDisplay display, IStorage storage) {
	this.display = display;
	this.storage = storage;
    }
    
    public void scan(String barcode) {
	display.showLine(barcode);
	display.showLine(storage.barcode(barcode));
    }
    
}
