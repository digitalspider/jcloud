package au.com.jcloud.controller.rest;

import static au.com.jcloud.WebConstants.ACTION_REST_BLOG;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ACTION_REST_BLOG)
@RestController
public class BlogLinkRestController extends BaseRestController {

	private final Logger LOG = Logger.getLogger(BlogLinkRestController.class);

	@GetMapping("/")
	public String index() {
		return "index";
	}


}
