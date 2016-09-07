package au.com.jcloud.service;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.persistence.Entity;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;

import au.com.jcloud.model.BaseBean;
import au.com.jcloud.model.IdBean;
import au.com.jcloud.util.Constants;
import au.com.jcloud.util.DelimiterConstants;
import au.com.jcloud.util.PropertyUtil;
import au.com.jcloud.util.ReflectUtil;

public class EBeanService {

	private static final Logger LOG = Logger.getLogger(EBeanService.class);
	private static EbeanServer server;
	
	public static EbeanServer getServer() {
		return getServer("default", true);
	}
	
	public static EbeanServer getServer(String name, boolean test) {
		if (server==null) {
			try {
				init(name, test);
			} catch (Exception e) {
				LOG.error(e,e);
			}
		}
		return server;
	}
	
	public static void init(String name, boolean test) throws Exception {
		LOG.debug("init() name="+name+" test="+test);
		ServerConfig config = new ServerConfig();
		Properties properties = null;
		if (test) {
			properties = PropertyUtil.loadProperties(Constants.FILENAME_PROPERTIES_EBEAN_TEST);
		} else {
			properties = PropertyUtil.loadProperties(Constants.FILENAME_PROPERTIES_EBEAN);
		}
		config.setDefaultServer(true);
		config.setName(name);
		config.loadFromProperties(properties);
		config.addClass(IdBean.class);
		config.addClass(BaseBean.class);
		String classString = properties.getProperty(Constants.EBEAN+DelimiterConstants.FULLSTOP+name);
		if (StringUtils.isBlank(classString)) {
			classString = properties.getProperty(Constants.EBEAN_DEFAULT);
		}
		if (StringUtils.isNotBlank(classString)) {
			String[] classArray = classString.split(",");
			for (String classItem : classArray) {
				LOG.debug("classItem="+classItem);
				if (classItem.endsWith(".*")) {
					String packageName = classItem.substring(0, classItem.length()-2);
					List<Class> classList = ReflectUtil.getClasses(packageName, Entity.class, new EBeanService().getClass().getClassLoader());
					for (Class<?> classToAdd : classList) {
						config.addClass(classToAdd);
					}
				}
				else {
					Class<?> classToAdd = Class.forName(classItem);
					config.addClass(classToAdd);
				}
			}
		}
		String jndiName = properties.getProperty(Constants.EBEAN_JNDI_DS+DelimiterConstants.FULLSTOP+name);
		if (StringUtils.isBlank(jndiName)) {
			jndiName = properties.getProperty(Constants.EBEAN_JNDI_DS);
		}
		LOG.debug("jndiName="+jndiName);
		if (StringUtils.isNotBlank(jndiName)) {
			DataSource ds = (DataSource) InitialContext.doLookup(jndiName);
			LOG.debug("ds="+ds);
			if (ds!=null) {
				config.setDataSource(ds);
			}
		}
		
		server = EbeanServerFactory.create(config);
	}
}
