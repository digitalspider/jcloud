package au.com.jcloud.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import au.com.jcloud.model.User;

public class Constants {

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

	public static void main(String[] args) {
		try {
			SlottingResource r = new SlottingResource();
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
