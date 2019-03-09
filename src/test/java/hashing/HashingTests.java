package hashing;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashingTests {

    private final Logger LOG = LogManager.getLogger();

    @Test
    public void test() {
        String hash = "5D41402ABC4B2A76B9719D911017C592";
        String password = "hello";
        String md5hash = DigestUtils.md5Hex(password).toUpperCase();
        assertEquals(hash, md5hash);
        LOG.info(md5hash);
    }

}
