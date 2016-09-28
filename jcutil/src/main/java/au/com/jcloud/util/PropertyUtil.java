package au.com.jcloud.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	public static Properties loadProperties(String filename) throws IOException {
		return loadProperties(filename, true);
	}

	public static Properties loadProperties(String filename, boolean useOverride) throws IOException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream(filename);
		if (in != null) {
			try {
				properties.load(in);
			} finally {
				in.close();
			}
		}
		else {
			FileReader reader = null;
			try {
				File file = new File(filename);
				if (file.exists()) {
					reader = new FileReader(file);
					properties.load(reader);
				}
				else {
					file = new File(Constants.PATH_RESOURCES_TEST + filename);
					if (file.exists()) {
						reader = new FileReader(file);
						properties.load(reader);
					}
					else {
						file = new File(Constants.PATH_RESOURCES_MAIN + filename);
						if (file.exists()) {
							reader = new FileReader(file);
							properties.load(reader);
						}
					}
				}
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		}

		if (useOverride) {
			// Look for any override properties
			String overrideParam = Constants.PARAM_OVERRIDE;
			String os = System.getProperty(Constants.PROP_SYS_OS_NAME);
			if (os.toLowerCase().contains(Constants.OS_WIN)) {
				overrideParam = overrideParam + "w";
			}
			if (properties.containsKey(overrideParam)) {
				String value = properties.getProperty(overrideParam);
				Properties overrideProperties = loadProperties(value, false);
				properties.putAll(overrideProperties);
			}
		}
		return properties;
	}
}
