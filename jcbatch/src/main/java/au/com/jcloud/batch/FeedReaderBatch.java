package au.com.jcloud.batch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

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

	public static void main(String[] args) {
		try {
			FeedReaderBatch r = new FeedReaderBatch();
			String url = "https://therealsasha.wordpress.com/feed/";
			SyndFeed feed = r.getSyndFeedForUrl(url);
			System.out.println("feed.url=" + url);
			//System.out.println("feed=" + feed);
			System.out.println("feed.link=" + feed.getLink());
			System.out.println("feed.title=" + feed.getTitle());
			System.out.println("feed.desc=" + feed.getDescription());
			System.out.println("feed.author=" + feed.getAuthor());
			System.out.println("feed.type=" + feed.getFeedType());
			System.out.println("feed.pubdate=" + feed.getPublishedDate());
			for (Object catObject : feed.getCategories()) {
				SyndCategory category = (SyndCategory) catObject;
				//System.out.println("feed.entries.cat.uri=" + category.getTaxonomyUri());
				System.out.println("feed.tag.value=" + category.getName());
			}
			System.out.println("feed.entries.size=" + feed.getEntries().size());
			SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
			//System.out.println("feed.entry.1=" + entry);
			System.out.println("feed.entries.link=" + entry.getLink());
			System.out.println("feed.entries.title=" + entry.getTitle());
			System.out.println("feed.entries.author=" + entry.getAuthor());
			System.out.println("feed.entries.uri=" + entry.getUri());
			System.out.println("feed.entries.pubdate=" + entry.getPublishedDate());
			System.out.println("feed.entries.desc.type=" + entry.getDescription().getType());
			System.out.println("feed.entries.desc.value=" + entry.getDescription().getValue());
			SyndContent content = (SyndContent) entry.getContents().get(0);
			System.out.println("feed.entries.content.type=" + content.getType());
			//System.out.println("feed.entries.content.value=" + content.getValue());
			for (Object catObject : entry.getCategories()) {
				SyndCategory category = (SyndCategory) catObject;
				//System.out.println("feed.entries.cat.uri=" + category.getTaxonomyUri());
				System.out.println("feed.entries.cat.value=" + category.getName());
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
}
