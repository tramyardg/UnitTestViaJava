package dependency.breaking.pos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArtR56Display implements IDisplay {

    private final Logger log = LogManager.getLogger();

    @Override
    public void showLine(String line) {
        log.info(line);
    }
}
