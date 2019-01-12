package dependency.breaking;

import org.junit.Test;

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
}
