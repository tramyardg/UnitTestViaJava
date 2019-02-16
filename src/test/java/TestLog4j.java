import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4j {
    private static Logger rootLog = LogManager.getLogger();
    private static Logger consoleLog = LogManager.getLogger("analytics.console");
    private static Logger fileLog = LogManager.getLogger("analytics");

    @Test
    public void testRoot() {
        rootLog.debug("Debug log");
        rootLog.info("Info log");
        rootLog.error("Error log");
    }

}
