import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4j {
    private static final Logger log = LogManager.getLogger();

    @Test
    public void testRoot() {
        log.debug("Debug log");
        log.info("Info log");
        log.error("Error log");
    }

}
