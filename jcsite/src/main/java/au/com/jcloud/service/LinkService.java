package au.com.jcloud.service;

import org.apache.commons.lang3.StringUtils;

import au.com.jcloud.model.Blog;
import au.com.jcloud.model.Link;
import au.com.jcloud.util.ConvertUtil;

public class LinkService {

	public void setName(Blog blog) {
		if (StringUtils.isNotBlank(blog.getTitle())) {
			blog.setName(ConvertUtil.getLowerCaseWithDash(blog.getTitle()));
		}
	}

	public void setName(Link link) {
		if (StringUtils.isNotBlank(link.getTitle())) {
			link.setName(ConvertUtil.getLowerCaseWithDash(link.getTitle()));
		}
	}
}
