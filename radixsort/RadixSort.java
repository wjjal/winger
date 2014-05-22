package radixsort;

import java.util.ArrayList;
import java.util.Map;

public class RadixSort {
	private static final int RADIX = 10;

	void radixSort(int[] data, int keynum) {
		Map<Integer, ArrayList<Integer>> bucket = MyHashMap.getInstance();
		for (int i = 0; i < RADIX; i++)
			bucket.put(i, new ArrayList<Integer>());
		for (int i = 1; i <= keynum; i++) {
			Distribute(data, i, bucket);
			Collect(data, i, bucket);
		}
	}

	private void Distribute(int[] data, int place, Map<Integer, ArrayList<Integer>> bucket) {
		for (int i = 0; i < data.length; i++) {
			int temp = getPlaceValue(data[i], place);
			bucket.get(temp).add(data[i]);
		}
	}

	private void Collect(int[] data, int place, Map<Integer, ArrayList<Integer>> bucket) {
		int k = 0;
		for (int i = 0; i < RADIX; i++) {
           for(int j = 0;j<bucket.get(i).size();j++)
        	   data[k++] =  bucket.get(i).get(j);
           bucket.get(i).clear();
		}
	}

	private int getPlaceValue(int value, int place) {
		value = (int) (value % Math.pow(10, place));
		value = (int) (value / Math.pow(10, place-1));
		return value;
	}
}
