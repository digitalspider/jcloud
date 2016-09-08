package au.com.jcloud.batch;

import com.avaje.ebean.Ebean;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.persistence.PersistenceException;

import au.com.jcloud.model.Blog;
import au.com.jcloud.model.BlogSource;

/**
 * Created by david.vittor on 8/09/16.
 */
public class FeedUtil {
	private static final Logger LOG = Logger.getLogger(FeedUtil.class);

	/**
	 * Return the blogSource for the first item in the list, or a database one with the same link,
	 * or null.
	 *
	 * @param link
	 * @return
	 */
	public static BlogSource getBlogSourceByLink(String link) {
		BlogSource source = Ebean.getDefaultServer().find(BlogSource.class).where().eq("link",link).findUnique();
		return source;
	}

	public static Blog getBlogByLink(String link) {
		Blog blog = Ebean.getDefaultServer().find(Blog.class).where().eq("link",link).findUnique();
		return blog;
	}

	/**
	 * Get the {@link SyndFeed} for a given url
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws FeedException
	 */
	public static SyndFeed getFeedForUrl(String url) throws IOException, IllegalArgumentException, FeedException {
		SyndFeed feed = null;
		InputStream is = null;

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(3000); //set timeout to 3 seconds
			is = connection.getInputStream();
			if ("gzip".equals(connection.getContentEncoding())) {
				is = new GZIPInputStream(is);
			}
			InputSource source = new InputSource(is);
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(source);
		} finally {
			if (is != null)
				is.close();
		}

		return feed;
	}

	/**
	 * Map a {@link SyndFeed} to a List of {@link Blog}s
	 *
	 * @param feed
	 * @return
	 */
	public static List<Blog> mapFeedToBlog(SyndFeed feed) {
		List<Blog> blogs = new ArrayList<>();
		BlogSource source = null;
		BlogSource sourceInDB = getBlogSourceByLink(feed.getLink());
		if (sourceInDB==null) {
			source = mapSyndFeedToBlogSource(feed);
			Ebean.save(source);
		} else {
			source = sourceInDB;
		}
		LOG.debug("feed.entries.size=" + feed.getEntries().size());
		for (Object feedObj : feed.getEntries()) {
			Blog blog = mapSyndEntryToBlog((SyndEntry) feedObj);
			blog.setSource(source);
			blogs.add(blog);
		}
		return blogs;
	}

	/**
	 * Write the blogs to the database
	 *
	 * @param blogs
	 */
	public static void writeBlogs(List<Blog> blogs) {
		for (Blog blog : blogs) {
			if (blog.getDescription()!=null && blog.getDescription().length()>=512) {
				blog.setDescription(blog.getDescription().substring(0,512));
			}
			try {
//				Blog blogInDB = getBlogByLink(blog.getLink());
//				if (blogInDB==null) {
//					Ebean.save(blog);
//				}
				Ebean.save(blog);
			} catch (PersistenceException e) {
				if (e.getCause()!=null && (e.getCause().getMessage().startsWith("Duplicate entry") ||
						e.getCause().getMessage().startsWith("Unique index or primary key violation"))) {
					// Duplicate - can be ignored
					LOG.warn(e.getCause());
				} else {
					LOG.error(e, e);
				}
			}
		}
	}

	/**
	 * Map a {@link SyndFeed} to a new {@link BlogSource}
	 *
	 * @param feed
	 * @return
	 */
	public static BlogSource mapSyndFeedToBlogSource(SyndFeed feed) {
		BlogSource source = new BlogSource();
		source.setLink(feed.getLink());
		source.setTitle(feed.getTitle());
		source.setDescription(feed.getDescription());
		source.setAuthor(feed.getAuthor());
		source.setType(feed.getFeedType());
		source.setPublistedDate(feed.getPublishedDate());
		StringBuffer tags = new StringBuffer();
		for (Object catObject : feed.getCategories()) {
			SyndCategory category = (SyndCategory) catObject;
			if (tags.length()>0) {
				tags.append(" ");
			}
			tags.append(category.getName().toLowerCase().replace(" ","-"));
		}
		if (tags.length()>0) {
			source.setTags(tags.toString());
		}
		source.setEntries(feed.getEntries().size());
		return source;
	}

	/**
	 * Map a {@link SyndEntry} to a new {@link Blog}.
	 *
	 * Note: BlogSource is not populated by this method and should be set on the return blog.
	 *
	 * @param entry
	 * @return
	 */
	public static Blog mapSyndEntryToBlog(SyndEntry entry) {
		Blog blog = new Blog();
		blog.setLink(entry.getLink());
		blog.setAuthor(entry.getAuthor());
		blog.setTitle(entry.getTitle());
		blog.setAuthor(entry.getAuthor());
		blog.setUri(entry.getUri());
		blog.setPublistedDate(entry.getPublishedDate());
		blog.setDescriptionType(entry.getDescription().getType());
		blog.setDescription(entry.getDescription().getValue());
		StringBuffer contentBuffer = new StringBuffer();
		for (Object contentObject : entry.getContents()) {
			SyndContent content = (SyndContent) contentObject;
			if (contentBuffer.length()>0) {
				contentBuffer.append(" ");
			}
			contentBuffer.append(content.getValue());
			if (blog.getContentType()==null) {
				blog.setContentType(content.getType());
			}
		}
		if (contentBuffer.length()>0) {
			blog.setContent(contentBuffer.toString());
		}
		StringBuffer tags = new StringBuffer();
		for (Object catObject : entry.getCategories()) {
			SyndCategory category = (SyndCategory) catObject;
			if (tags.length()>0) {
				tags.append(" ");
			}
			tags.append(category.getName().toLowerCase().replace(" ","-"));
		}
		if (tags.length()>0) {
			blog.setTags(tags.toString());
		}
		return blog;
	}
}
