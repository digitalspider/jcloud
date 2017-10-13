package au.com.jcloud.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController extends BaseController {

	private final Logger LOG = Logger.getLogger(AccountController.class);

	@GetMapping("/")
	public String index() {
		return "index";
	}


}
