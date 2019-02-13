package dependency.breaking.pos;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashStorage implements IStorage {

    private final Logger log = LoggerFactory.getLogger(HashStorage.class);
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
