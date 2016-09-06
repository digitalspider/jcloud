package au.com.jcloud.batch;

import org.apache.log4j.Logger;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

import au.com.jcloud.enums.Status;
import au.com.jcloud.model.Blog;
import au.com.jcloud.model.BlogSource;
import au.com.jcloud.service.EBeanService;

public class TestDB {
	private static final Logger LOG = Logger.getLogger(TestDB.class);
	private static final String SERVER_NAME = "jc";

	static {
		LOG.info("EBean server init "+SERVER_NAME);
		EBeanService.getServer(SERVER_NAME, false);
		LOG.info("EBean server ready "+SERVER_NAME);
	}
	
	public static void main(String[] args) {
		try {
			TestDB testDb = new TestDB();
			testDb.run();
		} catch(Exception e) {
			LOG.error(e,e);
		}
	}
	
	public void run() throws Exception {		
		EbeanServer server = Ebean.getServer(SERVER_NAME);
		LOG.info("run() START");
//		EbeanServer server = EBeanService.getServer(SERVER_NAME, false);
		LOG.info("server="+server);
		LOG.info("meta="+server.getMetaInfoManager().getMetaBeanInfoList());
		Blog blog = server.find(Blog.class).findUnique();
		LOG.info("blog="+blog);
		
		BlogSource blogSource = new BlogSource();
		blogSource.setLink("http://link");
		Blog blog1 = new Blog();
		blog1.setAuthor("david");
		blog1.setSource(blogSource);
		blog1.setTitle("title");
		blog1.setStatus(Status.ENABLED.value());
		server.save(blog1);
		
		blog = server.find(Blog.class).findUnique();
		LOG.info("blog="+blog);
		
	}
}
