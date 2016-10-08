package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.PATH_SECURE;
import static au.com.jcloud.actionbean.InvoiceActionBean.JSP_BINDING;

import javax.annotation.security.PermitAll;

import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by david.vittor on 3/08/16.
 */
@PermitAll
@UrlBinding(PATH_SECURE + JSP_BINDING)
public class InvoiceActionBean extends JCSecureActionBean {

	public static final String JSP_BINDING = "/invoice";

	@Validate(required = true, minlength = 2)
	private String name;
	@Validate(required = true, converter = EmailTypeConverter.class)
	private String email;
	@Validate(required = true, minlength = 2)
	private String subject;
	@Validate(required = true, minlength = 2)
	private String message;

	@Override
	public String getJSPBinding() {
		return JSP_BINDING;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
