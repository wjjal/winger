package mergesort;

enum MergeSort {
	INSTANCE();
	int[] mergeSort(int[] sr) {
		int len = sr.length;
		for (int gap = 1; gap < len;) {
			gap *= 2;
			int[] tr = new int[len];
			for (int group = 0; group < (float) len / gap; group++) {
				int begin = group * gap, end = begin + gap - 1, mid = (end + begin) / 2;
				if (end >= len)
					end = len - 1;
				merge(sr, tr, begin, mid, end);
			}
			sr = tr;
		}
		return sr;
	}

	private void merge(int[] sr, int[] tr, int i, int m, int n) {
		int j = m + 1, k = i;
		for (; i <= m && j <= n; k++) {
			if (sr[i] <= sr[j])
				tr[k] = sr[i++];
			else
				tr[k] = sr[j++];
		}
		if (i <= m)
			for (; k <= n; k++, i++)
				tr[k] = sr[i];
		if (j <= n)
			for (; k <= n; k++, j++)
				tr[k] = sr[j];
	}
}
