package au.com.jcloud.batch;

import org.apache.log4j.Logger;

import com.avaje.ebean.Ebean;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Blog;
import au.com.jcloud.model.BlogSource;

public class TestDB {
	private static final Logger LOG = Logger.getLogger(FeedReaderBatch.class);

	public static void main(String[] args) {
		try {
			TestDB testDb = new TestDB();
			testDb.run();
		} catch(Exception e) {
			LOG.error(e,e);
		}
	}
	
	public void run() {
		Blog blog = Ebean.getServer("jc").find(Blog.class).findUnique();
		System.out.println("blog="+blog);
		
		BlogSource blogSource = new BlogSource();
		blogSource.setLink("http://link");
		Blog blog1 = new Blog();
		blog1.setAuthor("david");
		blog1.setSource(blogSource);
		blog1.setTitle("title");
		blog1.setStatus(Status.ENABLED.value());
		Ebean.getServer("jc").save(blog1);
		
		blog = Ebean.getServer("jc").find(Blog.class).findUnique();
		System.out.println("blog="+blog);
		
	}
}
