package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.EDIT_JSP;
import static au.com.jcloud.WebConstants.LIST_JSP;
import static au.com.jcloud.WebConstants.PATH_SECURE_JSP;
import static au.com.jcloud.WebConstants.SHOW_JSP;

import javax.annotation.security.PermitAll;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * Created by david on 30/08/16.
 */

public abstract class JCSecureActionBean extends JCActionBean {

	public Resolution defaultResolution = getListResolution();

	/**
	 * Return the url binding of the jsp page. This is prepended with {@link PATH_SECURE_JSP}
	 * and appended with show.jsp, edit.jsp or list.jsp.
	 *
	 * @return the jsp binding for this JCSecureActionBean, e.g. /account
	 */
	public abstract String getJSPBinding();

	@PermitAll
	@DontValidate
	@DontBind
	@DefaultHandler
	@Override
	public Resolution action() {
		return getDefaultResolution();
	}

	public String getShowPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + SHOW_JSP;
	}

	public String getEditPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + EDIT_JSP;
	}

	public String getListPageJsp() {
		return PATH_SECURE_JSP + getJSPBinding() + "/" + LIST_JSP;
	}

	public Resolution getShowResolution() {
		return new ForwardResolution(getShowPageJsp());
	}

	public Resolution getEditResolution() {
		return new ForwardResolution(getEditPageJsp());
	}

	public Resolution getListResolution() {
		return new ForwardResolution(getListPageJsp());
	}

	public Resolution getDefaultResolution() {
		return defaultResolution;
	}

	public void setDefaultResolution(Resolution defaultResolution) {
		this.defaultResolution = defaultResolution;
	}
}
