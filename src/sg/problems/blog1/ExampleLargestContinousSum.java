package sg.problems.blog1;

public class ExampleLargestContinousSum {
	static int max(int x, int y) {
		return (y > x) ? y : x;
	}

	public static int maxSubArraySum(int a[]) {
		int size = a.length;
		int max_so_far = a[0], i;
		int curr_max = a[0];

		for (i = 1; i < size; i++) {
			curr_max = max(a[i], curr_max + a[i]);
			max_so_far = max(max_so_far, curr_max);
		}
		return max_so_far;
	}

	public static void main(String[] args) {
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		//int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int max_sum = maxSubArraySum(a);
		System.out.printf("Maximum contiguous sum is %d\n", max_sum);

	}
}
