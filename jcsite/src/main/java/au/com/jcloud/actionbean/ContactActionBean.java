package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.ACTION_PUBLIC_CONTACT;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import au.com.jcloud.WebConstants;
import au.com.jcloud.service.EmailService;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by david.vittor on 3/08/16.
 */
@UrlBinding(ACTION_PUBLIC_CONTACT)
public class ContactActionBean extends JCActionBean {

	@SpringBean
	private EmailService emailService;

	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Validate(required = true, minlength = 2)
	private String name;
	@Validate(required = true, converter = EmailTypeConverter.class)
	private String email;
	@Validate(required = true, minlength = 2)
	private String subject;
	@Validate(required = true, minlength = 2)
	private String message;

	@HandlesEvent("contact")
	public Resolution contact() {
		if (executorService != null) {
			// submit to executor service thread
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					sendEmail();
				}
			});
		}
		else {
			sendEmail();
		}
		return new ForwardResolution(WebConstants.PAGE_CONTACT);
	}

	private void sendEmail() {
		try {
			if (emailService != null) {
				emailService.sendFromEmail(name, email, subject, message);
			}
		} catch (Exception e) {
			LOG.error(e, e);
		}
	}

	protected void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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
