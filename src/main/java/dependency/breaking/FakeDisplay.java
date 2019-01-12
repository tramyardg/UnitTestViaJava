package dependency.breaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeDisplay implements IDisplay {

    private final Logger log = LoggerFactory.getLogger(IDisplay.class);
    private String lastLine = "";
    
    @Override
    public void showLine(String line) {
	log.info(line);
	this.lastLine = line;
    }
    
    public String getLastLine() {
	return lastLine;
    }

}
