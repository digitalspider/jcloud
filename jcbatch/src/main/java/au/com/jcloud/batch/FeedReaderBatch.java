package au.com.jcloud.batch;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

import au.com.jcloud.model.Blog;
import au.com.jcloud.service.EBeanService;
import au.com.jcloud.util.Constants;
import au.com.jcloud.util.DelimiterConstants;
import au.com.jcloud.util.PropertyUtil;

public class FeedReaderBatch {

	/*
	Author = http://blog.booktopia.com.au/author/anastasiabooktopia/
	Tag = http://blog.booktopia.com.au/tag/caroline-baum/
	Category = http://blog.booktopia.com.au/category/book-recommendations/
	*/

	private static final Logger LOG = Logger.getLogger(FeedReaderBatch.class);
	private static final String SERVER_NAME = "jc";

	static {
		LOG.info("EBean server init " + SERVER_NAME);
		EBeanService.getServer(SERVER_NAME, false);
		LOG.info("EBean server ready " + SERVER_NAME);
	}

	public static void main(String[] args) {
		try {
			FeedReaderBatch r = new FeedReaderBatch();
			Properties properties = PropertyUtil.loadProperties(Constants.FILENAME_PROPERTIES_BATCH);
			String feeds = properties.getProperty(Constants.PROP_FEEDS);
			String[] feedArray = feeds.split(DelimiterConstants.COMMA);
			for (String url : feedArray) {
				try {
					readAndWriteFeed(url.trim());
				} catch (Exception e) {
					LOG.error("ERROR in url="+url+" ERROR="+e,e);
				}
			}
		} catch (Exception e) {
			LOG.error(e,e);
		}
	}

	public static void readAndWriteFeed(String url) throws IOException, IllegalArgumentException, FeedException {
		LOG.info("feed.url=" + url);
		SyndFeed feed = FeedUtil.getFeedForUrl(url);
		LOG.trace("feed=" + feed);
		LOG.info("feed.entries.size=" + feed.getEntries().size());
		List<Blog> blogs = FeedUtil.mapFeedToBlog(feed);
		LOG.info("blogs=" + blogs);
		FeedUtil.writeBlogs(blogs);
	}
}
