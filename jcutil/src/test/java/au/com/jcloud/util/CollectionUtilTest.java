package au.com.jcloud.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Unit test which check correct work of CollectionUtil
 */
public class CollectionUtilTest {

	@Test
	public void checkEmpty() {
		List<String> list = null;
		assertFalse("Collection Utility class is broken, method for checking on not empty list is not working", CollectionUtil.isNotEmpty(list));
		assertTrue("Collection Utility class is broken, method for checking on empty list is not working", CollectionUtil.isEmpty(list));

		list = new ArrayList<String>();
		assertFalse("Collection Utility class is broken, method for checking on not empty list is not working", CollectionUtil.isNotEmpty(list));
		assertTrue("Collection Utility class is broken, method for checking on empty list is not working", CollectionUtil.isEmpty(list));

		list.add("test");
		assertTrue("Collection Utility class is broken, method for checking on not empty list is not working", CollectionUtil.isNotEmpty(list));
		assertFalse("Collection Utility class is broken, method for checking on empty list is not working", CollectionUtil.isEmpty(list));
	}

	@Test
	public final void startsWith_shouldReturnFalseWhenNullOrEmpty() {
		assertFalse(CollectionUtil.startsWith(null, "text"));
		assertFalse(CollectionUtil.startsWith(new ArrayList<String>(), "text"));
	}

	@Test
	public final void startsWith_shouldReturnFalseWhenNotInList() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("try");
		stringList.add("testing");
		assertFalse(CollectionUtil.startsWith(stringList, "text"));
	}

	@Test
	public final void startsWith_shouldReturnTrueWhenInList() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("try");
		stringList.add("testing");
		assertTrue(CollectionUtil.startsWith(stringList, "test"));
	}
}