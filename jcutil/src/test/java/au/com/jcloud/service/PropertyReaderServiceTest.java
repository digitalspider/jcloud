package au.com.jcloud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by david on 7/08/16.
 */
public class PropertyReaderServiceTest {
    PropertyReaderService service = new PropertyReaderService();

    @Test
    public void testLoadProperties() throws IOException {
        Properties properties = service.loadProperties("email.properties");
        assertNotNull(properties);
        assertTrue(properties.containsKey("override"));
        assertTrue(properties.containsKey("email.username"));
        assertEquals("dvittor@gmail.com", properties.getProperty("email.username"));
    }
}
