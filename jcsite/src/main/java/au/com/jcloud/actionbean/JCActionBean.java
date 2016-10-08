package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.PAGE_LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import au.com.jcloud.context.JCActionBeanContext;
import au.com.jcloud.model.User;
import au.com.jcloud.util.HttpUtil;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Created by david.vittor on 14/08/16.
 */
public class JCActionBean implements ActionBean {
	protected final Logger LOG = Logger.getLogger(getClass());

	protected JCActionBeanContext context;

	@DontValidate
	@DontBind
	@DefaultHandler
	public Resolution action() throws Exception {
		String url = PAGE_LOGIN;
		String queryString = getQueryString();
		if (StringUtils.isNotBlank(queryString)) {
			url += "?" + queryString;
		}
		return new RedirectResolution(url);
	}

	public String getRequestId() {
		return null;
	}

	public String getIpAddress() {
		return HttpUtil.getIpAddress(context.getRequest());
	}

	public String getUserAgent() {
		return HttpUtil.getUserAgent(context.getRequest());
	}

	protected HttpServletRequest getRequest() {
		return context.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return context.getResponse();
	}

	protected Resolution getSourcePageResolution() {
		return context.getSourcePageResolution();
	}

	protected String getReferrer() {
		return HttpUtil.getReferrer(context.getRequest());
	}

	protected String getQueryString() {
		String queryString = context.getRequest().getQueryString();
		return queryString;
	}

	protected void addGlobalValidationError(String messageKey, Object... params) {
		ValidationErrors errors = new ValidationErrors();
		errors.addGlobalError(new LocalizableError(messageKey, params));
		context.setValidationErrors(errors);
	}

	protected String getConextPath() {
		String contextPath = HttpUtil.getContextUrl(getRequest());
		LOG.debug("contextPath=" + contextPath);
		return contextPath;
	}

	protected PathParts getPathParts() {
		PathParts pathParts = HttpUtil.getPathParts(getRequest());
		LOG.debug("pathParts=" + pathParts);
		return pathParts;
	}

	protected User getUser() {
		return context.getUser();
	}

	protected JCActionBeanContext getJCContext() {
		return context;
	}

	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = (JCActionBeanContext) context;
	}

}
