package au.com.jcloud.service;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by david.vittor on 18/08/16.
 */
public class BaseService implements ApplicationContextAware {

	protected final Logger LOG = Logger.getLogger(getClass());

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public <T> T getBean(String name) {
		if (context != null) {
			return (T) context.getBean(name);
		}
		return null;
	}
}
