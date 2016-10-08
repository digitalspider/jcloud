package au.com.jcloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.avaje.datasource.DataSourceConfig;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;

import au.com.jcloud.model.Address;
import au.com.jcloud.model.BaseBean;
import au.com.jcloud.model.Cart;
import au.com.jcloud.model.CartItem;
import au.com.jcloud.model.Category;
import au.com.jcloud.model.CreditCard;
import au.com.jcloud.model.IdBean;
import au.com.jcloud.model.Invoice;
import au.com.jcloud.model.InvoiceLine;
import au.com.jcloud.model.OperatingSystem;
import au.com.jcloud.model.Product;
import au.com.jcloud.model.Purchase;
import au.com.jcloud.model.PurchaseProduct;
import au.com.jcloud.model.Rating;
import au.com.jcloud.model.Request;
import au.com.jcloud.model.Role;
import au.com.jcloud.model.Server;
import au.com.jcloud.model.Service;
import au.com.jcloud.model.User;
import au.com.jcloud.util.Constants;
import au.com.jcloud.util.PropertyUtil;

/**
 * Created by david.vittor on 5/08/16.
 */
public class EBeanServerService extends BaseService {
	public static final String EBEAN_PROPERTIES = "ebean.properties";
	public static final String EBEAN_PROPERTIES_TEST = "test-ebean.properties";

	private static ServerConfig serverConfig;
	private static EbeanServer ebeanServer;

	public EBeanServerService() {
		this(false);
	}

	public EBeanServerService(boolean isTest) {
		if (isTest) {
			init(EBEAN_PROPERTIES_TEST);
		} else {
			init(EBEAN_PROPERTIES);
		}
	}

	public EBeanServerService(String properiesFile) {
		init(properiesFile);
	}

	public void init(String propertiesFile) {
		try {
			if (ebeanServer == null) {
				serverConfig = new ServerConfig();
				serverConfig.setName("jc");
				serverConfig.setDefaultServer(true);
				Properties properties = PropertyUtil.loadProperties(propertiesFile);
				serverConfig.loadFromProperties(properties);
				ebeanServer = EbeanServerFactory.create(serverConfig);
			}
		} catch (Exception e) {
			LOG.error("ERROR initialising properties file: " + propertiesFile + " " + e.getMessage(), e);
		}
	}

	public static EbeanServer createEbeanServer() {

		List<Class<?>> beanClasses = new ArrayList<Class<?>>();
		beanClasses.add(IdBean.class);
		beanClasses.add(BaseBean.class);
		beanClasses.add(User.class);
		beanClasses.add(Role.class);
		beanClasses.add(Server.class);
		beanClasses.add(Service.class);
		beanClasses.add(Address.class);
		beanClasses.add(CreditCard.class);
		beanClasses.add(Invoice.class);
		beanClasses.add(InvoiceLine.class);
		beanClasses.add(Purchase.class);
		beanClasses.add(Cart.class);
		beanClasses.add(CartItem.class);
		beanClasses.add(Rating.class);
		beanClasses.add(Request.class);
		beanClasses.add(Product.class);
		beanClasses.add(PurchaseProduct.class);
		beanClasses.add(Category.class);
		beanClasses.add(OperatingSystem.class);
		System.setProperty("ebean.ignoreExtraDdl", "true");

		ServerConfig c = new ServerConfig();
		c.setName("jc");

		// requires postgres driver in class path
		DataSourceConfig tsetDb = new DataSourceConfig();
		tsetDb.setDriver("org.h2.Driver");
		tsetDb.setUsername("sa");
		tsetDb.setPassword("");
		tsetDb.setUrl("jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");
		tsetDb.setHeartbeatSql("select 1");

		c.loadFromProperties();
		c.setDdlGenerate(true);
		c.setDdlRun(true);
		c.setDefaultServer(false);
		c.setRegister(false);
		c.setDataSourceConfig(tsetDb);
		c.setClasses(beanClasses);

		// c.setDatabaseBooleanTrue("1");
		// c.setDatabaseBooleanFalse("0");
		c.setDatabaseBooleanTrue("T");
		c.setDatabaseBooleanFalse("F");

		c.setDatabasePlatform(new H2Platform());

		// c.addClass(User.class);

		return EbeanServerFactory.create(c);
	}

	public static EbeanServer getEBeanServer() {
		return ebeanServer;
	}

	public static ServerConfig getServerConfig() {
		return serverConfig;
	}

	public static void main(String[] args) {
//		EbeanServer server = EBeanServerService.createEbeanServer();
		EBeanServerService e = new EBeanServerService(true);
		EbeanServer server = e.getEBeanServer();
		System.out.println("server="+server);
		User user = server.find(User.class).findUnique();
		System.out.println("user="+user);
	}
}
