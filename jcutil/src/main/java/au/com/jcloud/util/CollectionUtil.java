package au.com.jcloud.util;

import java.util.Collection;

/**
 * Created by david on 07/10/16.
 */
public class CollectionUtil {

	/**
	 * Check is a collection is not empty
	 */
	public static boolean isNotEmpty(Collection<?> cl) {
		return ((cl != null) && (cl.size() > 0));
	}

	/**
	 * Check is a collection is empty
	 */
	public static boolean isEmpty(Collection<?> cl) {
		return !isNotEmpty(cl);
	}

	/**
	 * Returns true if any String in the collections starts with the prefix
	 *
	 * @param stringList the list of values
	 * @param prefix the value being searched for
	 * @return true if any String in the collections starts with the prefix
	 */
	public static boolean startsWith(Collection<String> stringList, String prefix) {
		if (isEmpty(stringList)) {
			return false;
		}
		for (String stringItem : stringList) {
			if (stringItem.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}
}