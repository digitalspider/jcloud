package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.ACTION_REST_BLOG;
import static au.com.jcloud.WebConstants.URL_CC_PREFIX;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Blog;
import au.com.jcloud.util.HttpUtil;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.JsonResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RestActionBean;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * Created by david.vittor on 3/08/16.
 */
@RestActionBean
@UrlBinding(ACTION_REST_BLOG)
public class BlogLinkActionBean extends JCActionBean {

	public static final int DEFAULT_ROWS = 1;
	public static final int MAX_ROWS = 12;

	public Resolution get() {
		return new JsonResolution(getActiveBlogs());
	}

	public Resolution post() throws Exception {
		return new JsonResolution(getActiveBlogs());
	}

	protected List<Blog> getActiveBlogs() {
		LOG.info("getActiveBlogs() START");
		PathParts pathParts = HttpUtil.getPathParts(getRequest());
		LOG.info("pathParts=" + pathParts); // 0=link, 1=category, 2=maxRows

		List<Blog> data = new ArrayList<>();
		try {
			String tags = null;
			int maxRows = DEFAULT_ROWS;
			if (pathParts.size() > 1) {
				if (pathParts.isNumeric(1)) {
					maxRows = pathParts.getInt(1, 0, MAX_ROWS);
				}
				else {
					tags = pathParts.get(1);
					if (pathParts.isNumeric(2)) {
						maxRows = pathParts.getInt(2, 0, MAX_ROWS);
					}
				}
			}
			ExpressionList<Blog> query = Ebean.find(Blog.class).setMaxRows(maxRows).where().eq("status", Status.ENABLED.value());
			if (StringUtils.isNotBlank(tags)) {
				query = query.and().ilike("tags", '%' + tags + '%');
			}
			LOG.info("query=" + query);
			data = query.findList();
			LOG.info("data=" + data);
		} catch (Exception e) {
			LOG.error(e, e);
		}
		for (Blog blog : data) {
			// Update link to go through clickCount
			blog.setLink(getConextPath() + URL_CC_PREFIX + blog.getLink());
		}
		LOG.info("getActiveBlogs() DONE. data=" + data);
		return data;
	}
}
