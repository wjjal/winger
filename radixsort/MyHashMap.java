package radixsort;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap<K, T> extends HashMap<K, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4870643512742709065L;

	public static <K, T> Map<K, T> getInstance() {
		return new HashMap<K, T>();
	}
}
