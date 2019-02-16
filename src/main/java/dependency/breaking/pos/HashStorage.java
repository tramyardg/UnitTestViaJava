package dependency.breaking.pos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class HashStorage implements IStorage {

    private final Logger log = LogManager.getLogger();
    private HashMap<String, String> map = new HashMap<>();

    public void put(String barcode, String item) {
        log.info("storing item {} with barcode {}", item, barcode);
        map.put(barcode, item);
    }

    @Override
    public String barcode(String barcode) {
        return map.get(barcode);
    }

}
