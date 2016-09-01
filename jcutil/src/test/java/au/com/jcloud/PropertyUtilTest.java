package au.com.jcloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import au.com.jcloud.util.PropertyUtil;

/**
 * Created by david on 7/08/16.
 */
public class PropertyUtilTest {
    @Test
    public void testLoadProperties() throws IOException {
        Properties properties = PropertyUtil.loadProperties("email.properties");
        assertNotNull(properties);
        assertTrue(properties.containsKey("override"));
        assertTrue(properties.containsKey("email.username"));
        assertEquals("test@gmail.com", properties.getProperty("email.username"));
    }
}
