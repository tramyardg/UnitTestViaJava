package dependency.breaking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import dependency.breaking.pos.FakeDisplay;
import dependency.breaking.pos.FakeStorage;
import dependency.breaking.pos.HashStorage;
import dependency.breaking.pos.IDisplay;
import dependency.breaking.pos.Sale;

public class SaleTest {

    private final Logger log = LogManager.getLogger();

    @Test
    public void testDisplayAnItem() {
        FakeDisplay display = new FakeDisplay();
        Sale sale = new Sale(display);
        sale.scan("Milk $3.99");
        Assert.assertEquals("Milk $3.99", display.getLastLine());
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
        Assert.assertEquals("Milk $3.99", display.getLastLine());
    }

    @Test
    public void testHashStorage() {
        FakeDisplay display = new FakeDisplay();
        HashStorage storage = new HashStorage();

        storage.put("123", "gum, 1.99");
        storage.put("141", "cigars, 5.99");

        Sale sale = new Sale(display, storage);
        sale.scan("123");
        Assert.assertEquals("gum, 1.99", display.getLastLine());

        sale.scan("141");
        Assert.assertEquals("cigars, 5.99", display.getLastLine());
    }

    @Test
    public void testScan() {
        HashStorage storage = mock(HashStorage.class);
        when(storage.barcode("1A")).thenReturn("milk");

        log.info("{}", storage.barcode("1A"));
    }

}
