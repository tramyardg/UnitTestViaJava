package dependency.breaking.pos;

public class Sale {

    // Sale has a Display relationship
    private IDisplay display;
    private IStorage storage;
    private Interac interac;

    public Sale(IDisplay display) {
        this.display = display;
    }

    public Sale(IDisplay display, IStorage storage) {
        this.display = display;
        this.storage = storage;
    }

    public Sale(IDisplay display, HashStorage storage) {
        this.display = display;
        this.storage = storage;
    }

    public Sale(IDisplay display, HashStorage hashStorage, Interac interac) {
        this.display = display;
        this.storage = hashStorage;
        this.interac = interac;
    }

    public void scan(String barcode) {
        display.showLine(barcode);
        if (storage != null) {
            display.showLine(storage.barcode(barcode));
        }
    }

}
