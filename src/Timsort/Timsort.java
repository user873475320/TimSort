package Timsort;

public class Timsort {
	static int MIN_MERGE = 32;

	public static int minRunLength(int n) {
		assert n >= 0;
		int r = 0;
		while (n >= MIN_MERGE) {
			r |= (n & 1);
			n >>= 1;
		}
		return n + r;
	}

	// Эта функция сортирует массив из левого индекса  к правому индексу,
	// размер которого не превышает RUN
	public static void insertionSort(int[] arr, int left,
	                                 int right) {
		for (int i = left + 1; i <= right; i++) {
			int temp = arr[i];
			int j = i - 1;
			while (j >= left && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
	}

	// Функция слияния объединяет отсортированные прогоны
	public static void merge(int[] arr, int l,
	                         int m, int r) {
		// исходный массив разбит на две части: на левый и правый
		int len1 = m - l + 1, len2 = r - m;
		int[] left = new int[len1];
		int[] right = new int[len2];
		for (int x = 0; x < len1; x++) {
			left[x] = arr[l + x];
		}
		for (int x = 0; x < len2; x++) {
			right[x] = arr[m + 1 + x];
		}

		int i = 0;
		int j = 0;
		int k = l;

		// После сравнения мы объединяем эти два массива
		while (i < len1 && j < len2) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}

		// Копируем оставшиеся элементы left, если есть
		while (i < len1) {
			arr[k] = left[i];
			k++;
			i++;
		}

		// Копируем оставшийся элемент right, если есть
		while (j < len2) {
			arr[k] = right[j];
			k++;
			j++;
		}
	}

	public void sort(int[] arr, int n) {
		int minRun = minRunLength(MIN_MERGE);

		// Сортировка отдельных подмассивов по размеру RUN
		for (int i = 0; i < n; i += minRun) {
			insertionSort(arr, i,
					Math.min((i + MIN_MERGE - 1), (n - 1)));
		}

		// Начинаем слияние с размера RUN (или 32)
		for (int size = minRun; size < n; size = 2 * size) {
			for (int left = 0; left < n;
			     left += 2 * size) {
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1),
						(n - 1));

				if (mid < right)
					merge(arr, left, mid, right);
			}
		}
	}
}