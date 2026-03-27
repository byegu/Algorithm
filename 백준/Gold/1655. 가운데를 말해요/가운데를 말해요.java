import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static PriorityQueue<Integer> maxHeap;
	static PriorityQueue<Integer> minHeap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		minHeap = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			add(num);
			sb.append(maxHeap.peek() + "\n");
		}
		
		System.out.println(sb);
	}

	public static void add(int num) {
		if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
			maxHeap.add(num);
		} else {
			minHeap.add(num);
		}

		if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.add(maxHeap.poll());
		} else if (minHeap.size() > maxHeap.size()) {
			maxHeap.add(minHeap.poll());
		}
	}
}
