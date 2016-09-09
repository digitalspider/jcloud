package au.com.jcloud.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david.vittor on 30/08/16.
 */
@Entity
@Table(name = "link")
public class Link extends BaseBean {
	protected String title;
	protected String extract;
	protected String url;
	protected String tags;
	protected int clickCount;
	protected int rank;

	@Override
	public String toString() {
		return super.toString() + " title="+title+" clickCount=" + clickCount+" url=" + url+" tags=" + tags+" rank=" + rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}
}
