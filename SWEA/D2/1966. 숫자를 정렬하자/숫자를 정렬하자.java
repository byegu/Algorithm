import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			int arr[] = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			sort(arr);

			System.out.print("#" + t + " ");
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public static void sort(int[] arr) {
		left_pivot_sort(arr, 0, arr.length - 1);
	}

	private static void left_pivot_sort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}

		int pivot = partition(arr, low, high);

		left_pivot_sort(arr, low, pivot - 1);
		left_pivot_sort(arr, pivot + 1, high);
	}

	private static int partition(int[] arr, int left, int right) {
		int low = left;
		int high = right;
		int pivot = arr[left];

		while (low < high) {
			while (arr[high] > pivot && high > low) {
				high--;
			}
			while (arr[low] <= pivot && high > low) {
				low++;
			}
			swap(arr, low, high);
		}
		swap(arr, left, low);

		return low;
	}

	private static void swap(int[] arr, int low, int high) {
		int temp = arr[low];
		arr[low] = arr[high];
		arr[high] = temp;
	}
}