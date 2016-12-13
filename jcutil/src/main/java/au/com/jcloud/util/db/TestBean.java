package au.com.jcloud.util.db;

public class TestBean implements IdBeanIF {

	private long id;
	private String testName;
	private String testImage;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestImage() {
		return testImage;
	}

	public void setTestImage(String testImage) {
		this.testImage = testImage;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
}
