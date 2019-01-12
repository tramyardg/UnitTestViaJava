package dependency.breaking;

import org.junit.Test;

import dependency.breaking.pos.FakeDisplay;
import dependency.breaking.pos.FakeStorage;
import dependency.breaking.pos.HashStorage;
import dependency.breaking.pos.Sale;
import junit.framework.TestCase;

public class SaleTest extends TestCase {
    
    @Test
    public void testDisplayAnItem() {
	FakeDisplay display = new FakeDisplay();
	Sale sale = new Sale(display);
	sale.scan("Milk $3.99");
	assertEquals("Milk $3.99", display.getLastLine());
    }
    
    @Test
    public void testDisplayBarcode() {
	FakeStorage storage = new FakeStorage();
	FakeDisplay display = new FakeDisplay();
	Sale sale = new Sale(display, storage);
	sale.scan("Milk $3.99");
	assertEquals("Milk $3.99", display.getLastLine());
    }
    
    @Test
    public void testHashStorage() {
	FakeDisplay display = new FakeDisplay();
	HashStorage storage = new HashStorage();
	
	storage.put("123", "gum, 1.99");
	storage.put("141", "cigars, 5.99");
	
	Sale sale = new Sale(display, storage);
	sale.scan("123");
	assertEquals("gum, 1.99", display.getLastLine());
	
	sale.scan("141");
	assertEquals("cigars, 5.99", display.getLastLine());
    }
}
