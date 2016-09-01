package au.com.jcloud.batch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

public class FeedReaderBatch {

	/*
	view-source:http://blog.booktopia.com.au/feed/
	view-source:http://feeds.feedburner.com/CoinDesk?format=xml
	view-source:http://feeds.feedburner.com/AllAtlassianBlogs?format=xml
	view-source:http://feeds.feedburner.com/JIRABlog?format=xml
	view-source:https://www.javablog.com/blog/-/blogs/rss
	view-source:https://therealsasha.wordpress.com/feed/

http://www.mkyong.com/feed/
http://www.mkyong.com/category/wildfly/feed/

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
			SyndFeed feed = r.getSyndFeedForUrl("http://blog.booktopia.com.au/category/top-stories/feed/");
			System.out.println("feed=" + feed);
			System.out.println("feed.entries.size=" + feed.getEntries().size());
			System.out.println("feed.entries.1=" + feed.getEntries().get(0));
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
