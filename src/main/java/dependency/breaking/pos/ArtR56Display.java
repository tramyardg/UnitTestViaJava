package dependency.breaking.pos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArtR56Display implements IDisplay {

    private final Logger log = LoggerFactory.getLogger(ArtR56Display.class);
    
    @Override
    public void showLine(String line) {
	log.info(line);
    }

}
