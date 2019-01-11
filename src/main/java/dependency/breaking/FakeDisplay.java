package dependency.breaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeDisplay implements Display {

    private final Logger log = LoggerFactory.getLogger(Display.class);
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
