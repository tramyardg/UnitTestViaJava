package dependency.breaking;

public class FakeStorage implements IStorage {

    @Override
    public String barcode(String barcode) {
	return barcode;
    }
    
}
