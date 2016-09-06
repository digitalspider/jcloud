package au.com.jcloud.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by david.vitor on 1/09/16.
 */
@Entity
@Table(name = "blog", uniqueConstraints = @UniqueConstraint(columnNames={"link"}))
public class Blog extends BaseBean {
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "source_id", referencedColumnName = "id")
	protected BlogSource source;
	@Column(nullable = false)
	protected String link;
	@Column(nullable = false)
	protected String title;
	protected String author;
	protected String uri;
	@Column(nullable = false)
	protected Date publistedDate;
	protected String descriptionType;
	@Column(length = 512)
	protected String description;
	protected String contentType;
	@Column(columnDefinition = "TEXT")
	protected String content;
	protected String tags;

	@Override
	public String toString() {
		return super.toString() + " title=" + title;
	}

	public BlogSource getSource() {
		return source;
	}

	public void setSource(BlogSource source) {
		this.source = source;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getPublistedDate() {
		return publistedDate;
	}

	public void setPublistedDate(Date publistedDate) {
		this.publistedDate = publistedDate;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
