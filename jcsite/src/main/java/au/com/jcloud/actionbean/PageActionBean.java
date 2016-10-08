package au.com.jcloud.actionbean;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import au.com.jcloud.WebConstants;
import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Blog;
import au.com.jcloud.util.HttpUtil;
import au.com.jcloud.util.PathParts;

import static au.com.jcloud.WebConstants.URL_CC_PREFIX;

/**
 * Created by david.vittor on 14/09/16.
 */
@UrlBinding(WebConstants.ACTION_PUBLIC_PAGE)
public class PageActionBean extends JCActionBean {

	public static final int DEFAULT_ROWS = 1;
	public static final int MAX_ROWS = 12;

	private Blog page = new Blog();
	private List<Blog> relatedPages = new ArrayList<Blog>();

	@Override
	@DefaultHandler
	public Resolution action() throws Exception {
		String url = WebConstants.PAGE_PAGE;
		LOG.info("action() START");
		PathParts pathParts = HttpUtil.getPathParts(getRequest());
		LOG.info("pathParts=" + pathParts); // 0=link, 1=category, 2=maxRows
		String queryString = getQueryString();
		LOG.info("queryString=" + queryString);


		try {
			String tags = null;
			int maxRows = DEFAULT_ROWS;
			if (pathParts.size() > 1) {
				if (pathParts.isNumeric(1)) {
					maxRows = pathParts.getInt(1,0,MAX_ROWS);
				} else {
					tags = pathParts.get(1);
					if (pathParts.isNumeric(2)) {
						maxRows = pathParts.getInt(2,0,MAX_ROWS);
					}
				}
			}
			ExpressionList<Blog> query = Ebean.find(Blog.class).setMaxRows(maxRows).where().eq("status", Status.ENABLED.value());
			if (StringUtils.isNotBlank(tags)) {
				query = query.and().ilike("tags", '%' + tags + '%');
			}
			LOG.info("query=" + query);
			page = query.findUnique();
			LOG.info("page=" + page);
			relatedPages = query.findList();
			LOG.info("relatedPages=" + relatedPages);
		} catch (Exception e) {
			LOG.error(e, e);
		}
		return new ForwardResolution(url);
	}

	public Blog getPage() {
		return page;
	}

	public void setPage(Blog page) {
		this.page = page;
	}

	public List<Blog> getRelatedPages() {
		return relatedPages;
	}

	public void setRelatedPages(List<Blog> relatedPages) {
		this.relatedPages = relatedPages;
	}
}
