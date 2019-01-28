package dependency.breaking;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import dependency.breaking.pos.FakeDisplay;
import dependency.breaking.pos.FakeStorage;
import dependency.breaking.pos.HashStorage;
import dependency.breaking.pos.IDisplay;
import dependency.breaking.pos.Sale;
import junit.framework.TestCase;
import mockito.MockitoTest;

public class SaleTest extends TestCase {
    
    HashStorage storage;
    IDisplay display;
    
    public SaleTest(IDisplay display, HashStorage storage) {
	this.display = display;
	this.storage = storage;
    }
    
    private final Logger log = LoggerFactory.getLogger(MockitoTest.class);
    
    @Test
    public void testDisplayAnItem() {
	FakeDisplay display = new FakeDisplay();
	Sale sale = new Sale(display);
	sale.scan("Milk $3.99");
	assertEquals("Milk $3.99", display.getLastLine());
    }
    
    @Test
    public void testVerify() {
	IDisplay display = mock(IDisplay.class);
	// do not mock Sale class
	Sale sale = new Sale(display);
	sale.scan("Milk $3.99");
	verify(display).showLine("Milk $3.99");	
    }
    
    @Test
    public void testDisplayBarcode() {
	FakeStorage storage = new FakeStorage();
	FakeDisplay display = new FakeDisplay();
	Sale sale = new Sale(display, storage);
	sale.scan("Milk $3.99");
	System.out.println(display.getLastLine());
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
    
    @Test
    public void testScan() {
	IDisplay display = mock(IDisplay.class);
	HashStorage storage = mock(HashStorage.class);
	when(storage.barcode("1A")).thenReturn("milk");
	
	Sale sale = new Sale(display);
	log.info("{}", storage.barcode("1A"));
    }
    
}
