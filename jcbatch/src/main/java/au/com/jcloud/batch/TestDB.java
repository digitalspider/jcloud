package au.com.jcloud.batch;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.avaje.ebean.EbeanServer;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Blog;
import au.com.jcloud.model.BlogSource;
import au.com.jcloud.service.EBeanService;

public class TestDB {
	private static final Logger LOG = Logger.getLogger(TestDB.class);

	public static void main(String[] args) {
		try {
			TestDB testDb = new TestDB();
			testDb.run();
		} catch(Exception e) {
			LOG.error(e,e);
		}
	}
	
	public void run() throws IOException {		
//		EbeanServer server = Ebean.getServer("jc");
		EbeanServer server = EBeanService.getServer("jc");
		Blog blog = server.find(Blog.class).findUnique();
		System.out.println("blog="+blog);
		
		BlogSource blogSource = new BlogSource();
		blogSource.setLink("http://link");
		Blog blog1 = new Blog();
		blog1.setAuthor("david");
		blog1.setSource(blogSource);
		blog1.setTitle("title");
		blog1.setStatus(Status.ENABLED.value());
		server.save(blog1);
		
		blog = server.find(Blog.class).findUnique();
		System.out.println("blog="+blog);
		
	}
}
