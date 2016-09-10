package au.com.jcloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by david.vittor on 30/08/16.
 */
@Entity
@Table(name = "link", uniqueConstraints = @UniqueConstraint(columnNames={"name","link"}))
public class Link extends BaseBeanWithName {
	@Column(nullable = false)
	protected String title;
	protected String extract;
	@Column(nullable = false)
	protected String link;
	protected String tags;
	protected int clickCount;
	protected int rank;

	@Override
	public String toString() {
		return super.toString() + " title="+title+" clickCount=" + clickCount+" link=" + link+" tags=" + tags+" rank=" + rank;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
