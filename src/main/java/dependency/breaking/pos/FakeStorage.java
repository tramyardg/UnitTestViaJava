package dependency.breaking.pos;

public class FakeStorage implements IStorage {

    @Override
    public String barcode(String barcode) {
	return barcode;
    }
    
}
