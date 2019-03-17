package toggles;

import data.migration.ArrayStorage;
import dependency.breaking.pos.IDisplay;
import dependency.breaking.pos.Interac;
import dependency.breaking.pos.Sale;
import dependency.breaking.pos.StoreToggles;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestingToggles {

    @Test
    public void testArrayOnly() {
        StoreToggles.isArrayStorageEnable = true;
        StoreToggles.isHashMapEnable = false;
        StoreToggles.isUnderTest = false;

        /*
        // this will make this method fail
        // since hash map and array storage are both false
        StoreToggles.isArrayStorageEnable = false;
        StoreToggles.isHashMapEnable = false;
        StoreToggles.isUnderTest = false;
        // see ArrayStorage's put method
         */

        IDisplay display = mock(IDisplay.class);
        Interac interac = mock(Interac.class);

        ArrayStorage storage = new ArrayStorage();
        storage.put("1", "Milk, 3.99");
        Sale sale = new Sale(display, storage, interac);

        sale.scan("1");
        verify(display).showLine("Milk, 3.99");
    }

}
