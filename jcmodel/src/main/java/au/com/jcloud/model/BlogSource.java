package au.com.jcloud.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by david.vitor on 1/09/16.
 */
@Entity
@Table(name = "blogsource")
public class BlogSource extends BaseBean {
	protected String url;
	protected String link;
	protected String title;
	protected String description;
	protected String author;
	protected String type;
	protected Date publistedDate;
	protected String tags;
	protected int entries;

	@Override
	public String toString() {
		return super.toString() + " title=" + title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPublistedDate() {
		return publistedDate;
	}

	public void setPublistedDate(Date publistedDate) {
		this.publistedDate = publistedDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getEntries() {
		return entries;
	}

	public void setEntries(int entries) {
		this.entries = entries;
	}
}
