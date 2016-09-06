package au.com.jcloud.batch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.h2.jdbc.JdbcSQLException;
import org.xml.sax.InputSource;

import com.avaje.ebean.Ebean;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

import javax.persistence.PersistenceException;

import au.com.jcloud.model.Blog;
import au.com.jcloud.model.BlogSource;
import au.com.jcloud.service.EBeanService;

public class FeedReaderBatch {

	/*
	http://blog.booktopia.com.au/feed/
	http://feeds.feedburner.com/CoinDesk?format=xml
	http://feeds.feedburner.com/AllAtlassianBlogs?format=xml
	http://feeds.feedburner.com/JIRABlog?format=xml
	https://www.javablog.com/blog/-/blogs/rss
	https://therealsasha.wordpress.com/feed/
	http://www.mkyong.com/feed/
	http://www.mkyong.com/category/maven/feed/
	http://blog.booktopia.com.au/feed/
	http://blog.booktopia.com.au/category/Fiction/feed/
	http://blog.booktopia.com.au/category/top-stories/feed/

	Author = http://blog.booktopia.com.au/author/anastasiabooktopia/
	Tag = http://blog.booktopia.com.au/tag/caroline-baum/
	Category = http://blog.booktopia.com.au/category/book-recommendations/
	*/

	private static final Logger LOG = Logger.getLogger(FeedReaderBatch.class);
	private static final String SERVER_NAME = "jc";

	static {
		LOG.info("EBean server init "+SERVER_NAME);
		EBeanService.getServer(SERVER_NAME, false);
		LOG.info("EBean server ready "+SERVER_NAME);
	}

	public static void main(String[] args) {
		try {
			FeedReaderBatch r = new FeedReaderBatch();
			String url = "http://www.mkyong.com/category/maven/feed/";
			SyndFeed feed = r.getSyndFeedForUrl(url);
			LOG.info("feed.url=" + url);
			//System.out.println("feed=" + feed);
			List<Blog> blogs = mapFeedToBlog(feed);
			LOG.info("blogs="+blogs);

			BlogSource source = null;
			if (!blogs.isEmpty()) {
				BlogSource sourceInFeed = blogs.get(0).getSource(); // Get source for first blog
				BlogSource sourceInDB = Ebean.getDefaultServer().find(BlogSource.class).where().eq("link",sourceInFeed.getLink()).findUnique();
				if (sourceInDB==null) {
					Ebean.save(sourceInFeed);
					source = sourceInFeed;
				} else {
					source = sourceInDB;
				}
			}
			for (Blog blog : blogs) {
				if (blog.getDescription()!=null && blog.getDescription().length()>=512) {
					blog.setDescription(blog.getDescription().substring(0,512));
				}
				if (blog.getContent()!=null && blog.getContent().length()>=2048) {
					blog.setContent(blog.getContent().substring(0,2048));
				}
				if (source!=null) {
					blog.setSource(source);
				}
				try {
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
//				Blog blogInDB = Ebean.getDefaultServer().find(Blog.class).where().eq("link",blog.getLink()).findUnique();
//				if (blogInDB==null) {
//					Ebean.save(blog);
//				} else {
//					// update maybe?
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SyndFeed getSyndFeedForUrl(String url) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {

		SyndFeed feed = null;
		InputStream is = null;

		try {

			URLConnection openConnection = new URL(url).openConnection();
			is = new URL(url).openConnection().getInputStream();
			if ("gzip".equals(openConnection.getContentEncoding())) {
				is = new GZIPInputStream(is);
			}
			InputSource source = new InputSource(is);
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(source);

		} catch (Exception e) {
			LOG.error("Exception occured when building the feed object out of the url", e);
		} finally {
			if (is != null)
				is.close();
		}

		return feed;
	}

	public static List<Blog> mapFeedToBlog(SyndFeed feed) {
		List<Blog> blogs = new ArrayList<>();
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

//		System.out.println("feed.link=" + feed.getLink());
//		System.out.println("feed.title=" + feed.getTitle());
//		System.out.println("feed.desc=" + feed.getDescription());
//		System.out.println("feed.author=" + feed.getAuthor());
//		System.out.println("feed.type=" + feed.getFeedType());
//		System.out.println("feed.pubdate=" + feed.getPublishedDate());
//		for (Object catObject : feed.getCategories()) {
//			SyndCategory category = (SyndCategory) catObject;
//			//System.out.println("feed.entries.cat.uri=" + category.getTaxonomyUri());
//			System.out.println("feed.tag.value=" + category.getName());
//		}
		System.out.println("feed.entries.size=" + feed.getEntries().size());

		for (Object feedObj : feed.getEntries()) {
			SyndEntry entry = (SyndEntry) feedObj;
			Blog blog = new Blog();
			blog.setSource(source);
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
			tags = new StringBuffer();
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
			blogs.add(blog);
			//System.out.println("feed.entry.1=" + entry);
//			System.out.println("feed.entries.link=" + entry.getLink());
//			System.out.println("feed.entries.title=" + entry.getTitle());
//			System.out.println("feed.entries.author=" + entry.getAuthor());
//			System.out.println("feed.entries.uri=" + entry.getUri());
//			System.out.println("feed.entries.pubdate=" + entry.getPublishedDate());
//			System.out.println("feed.entries.desc.type=" + entry.getDescription().getType());
//			System.out.println("feed.entries.desc.value=" + entry.getDescription().getValue());
//			for (Object contentObj : entry.getContents()) {
//				SyndContent content = (SyndContent) contentObj;
//				System.out.println("feed.entries.content.type=" + content.getType());
//				//System.out.println("feed.entries.content.value=" + content.getValue());
//			}
//			for (Object catObject : entry.getCategories()) {
//				SyndCategory category = (SyndCategory) catObject;
//				//System.out.println("feed.entries.cat.uri=" + category.getTaxonomyUri());
//				System.out.println("feed.entries.cat.value=" + category.getName());
//			}
		}
		return blogs;
	}
}
