package au.com.jcloud.actionbean;

import static au.com.jcloud.WebConstants.ACTION_REST_LINK;
import static au.com.jcloud.WebConstants.URL_CC_PREFIX;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Link;
import au.com.jcloud.util.PathParts;
import net.sourceforge.stripes.action.JsonResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RestActionBean;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * Created by david.vittor on 3/08/16.
 */
@RestActionBean
@UrlBinding(ACTION_REST_LINK)
public class LinkActionBean extends JCActionBean {

	public static final int DEFAULT_ROWS = 1;
	public static final int MAX_ROWS = 12;

	public Resolution get() {
		return new JsonResolution(getLinkData());
	}

	public Resolution post() throws Exception {
		return new JsonResolution(getLinkData());
	}

	protected List<Link> getLinkData() {
		PathParts pathParts = getPathParts();
		LOG.debug("pathParts=" + pathParts); // 0=link, 1=category, 2=maxRows

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
		ExpressionList<Link> query = Ebean.find(Link.class).setMaxRows(maxRows).where().eq("status", Status.ENABLED.value());
		if (StringUtils.isNotBlank(tags)) {
			query = query.and().ilike("tags", '%' + tags + '%');
		}
		LOG.debug("query=" + query);
		List<Link> linkList = query.findList();
		for (Link link : linkList) {
			// Update link to go through clickCount
			link.setLink(getConextPath() + URL_CC_PREFIX + link.getLink());
		}
		return linkList;
	}
}
