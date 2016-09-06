package au.com.jcloud.service;

import java.io.IOException;
import java.util.Properties;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;

import au.com.jcloud.model.BaseBean;
import au.com.jcloud.model.IdBean;
import au.com.jcloud.util.PropertyUtil;

public class EBeanService {

	private static EbeanServer server;
	
	public static EbeanServer getServer() throws IOException {
		return getServer("default");
	}
	
	public static EbeanServer getServer(String name) throws IOException {
		if (server==null) {
			init(name);
		}
		return server;
	}
	
	public static void init(String name) throws IOException {
		ServerConfig config = new ServerConfig();
		Properties properties = PropertyUtil.loadProperties("ebean.properties");
		config.setDefaultServer(true);
		config.setName(name);
		config.loadFromProperties(properties);
		config.addClass(IdBean.class);
		config.addClass(BaseBean.class);
		
//		EbeanServer server = Ebean.getServer("jc");
		server = EbeanServerFactory.create(config);
	}
}
