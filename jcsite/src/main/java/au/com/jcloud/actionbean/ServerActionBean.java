package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.PATH_SECURE;
import static au.com.jcloud.WebConstants.PATH_SECURE_JSP;
import static au.com.jcloud.actionbean.ServerActionBean.JSP_BINDING;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;

import au.com.jcloud.model.Product;
import au.com.jcloud.model.Server;
import au.com.jcloud.model.User;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by david.vittor on 3/08/16.
 */
@PermitAll
@UrlBinding(PATH_SECURE + JSP_BINDING)
public class ServerActionBean extends JCSecureActionBean {

	public static final String JSP_BINDING = "/server";
	public static final String JSP_ADDSERVER = "addserver.jsp";

	@Validate(required = true, minlength = 3)
	private String hostname;
	@Validate(required = true)
	private boolean terms;
	@Validate(required = true)
	private String memory;
	@Validate(required = true)
	private String disk;
	@Validate(required = true)
	private String backups;
	@Validate(required = true)
	private String cores;
	@Validate(required = true)
	private String os;
	@Validate(required = true)
	private String dbserver;
	@Validate(required = true)
	private String appserver;
	@Validate(required = true)
	private String webserver;
	@Validate(required = true)
	private String application;
	@Validate(required = true)
	private String support;

	private String selectedProduct;
	private List<Product> products = new ArrayList<>();

	private List<Server> servers = new ArrayList<>();

	public String getAddServerJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + JSP_ADDSERVER;
	}

	public Resolution getAddServerResolution() {
		return new ForwardResolution(getAddServerJsp());
	}

	@PermitAll
	@DontValidate
	@DontBind
	@DefaultHandler
	@Override
	public Resolution action() {
		User user = getUser();
		servers = user.getServers();
		if (servers.isEmpty()) {
			servers = Ebean.find(Server.class).where().eq("user_id", user.getId()).findList();
		}

		PathParts pathParts = getPathParts(); // 0=server, 1=123 2=start
		LOG.info("pathParts=" + pathParts);
		if (pathParts.size() > 1) {
			String action = pathParts.get(1);
			LOG.info("action=" + action);
			switch (action) {
			case ("add"):
				products = Ebean.find(Product.class).findList();
				return getAddServerResolution();
			case ("edit"):
				return getEditResolution();
			default:
				if (pathParts.isNumeric(1) && pathParts.size() > 2) {
					int serverId = pathParts.getInt(1);
					String serverAction = pathParts.get(2);
					switch (serverAction) {
					case ("view"):
						LOG.info(serverAction + " server " + serverId);
						break;
					case ("start"):
						LOG.info(serverAction + " server " + serverId);
						break;
					case ("stop"):
						LOG.info(serverAction + " server " + serverId);
						break;
					case ("restart"):
						LOG.info(serverAction + " server " + serverId);
						break;
					case ("service"):
						LOG.info(serverAction + " server " + serverId);
						break;
					}
					return getShowResolution();
				}
			}
		}
		return getDefaultResolution();
	}

	@HandlesEvent("addserver")
	public Resolution addServer() throws Exception {
		if (StringUtils.isNotBlank(selectedProduct) && StringUtils.isNotBlank(hostname)) {
			return new RedirectResolution(PATH_SECURE + CheckoutActionBean.JSP_BINDING + "/cart/add/" + selectedProduct+"/"+hostname);
		}
		else {
			addGlobalValidationError("product.not.selected");
			return getAddServerResolution();
		}
	}

	@Override
	public String getJSPBinding() {
		return JSP_BINDING;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean isTerms() {
		return terms;
	}

	public void setTerms(boolean terms) {
		this.terms = terms;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getBackups() {
		return backups;
	}

	public void setBackups(String backups) {
		this.backups = backups;
	}

	public String getCores() {
		return cores;
	}

	public void setCores(String cores) {
		this.cores = cores;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDbserver() {
		return dbserver;
	}

	public void setDbserver(String dbserver) {
		this.dbserver = dbserver;
	}

	public String getAppserver() {
		return appserver;
	}

	public void setAppserver(String appserver) {
		this.appserver = appserver;
	}

	public String getWebserver() {
		return webserver;
	}

	public void setWebserver(String webserver) {
		this.webserver = webserver;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(String selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
}
