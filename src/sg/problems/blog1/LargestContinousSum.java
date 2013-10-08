package sg.problems.blog1;

import java.util.Arrays;

import sg.util.ArrayUtil;
import sun.org.mozilla.javascript.internal.ast.ForInLoop;

public class LargestContinousSum {

	static class ArraySumValues {
		public final int sum;
		public final int startIndex;
		public final int endIndex;

		public ArraySumValues(int sum, int startIndex, int endIndex) {
			super();
			this.sum = sum;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public String toString() {
			return "[sum=" + sum + ", startIndex=" + startIndex + ", endIndex="
					+ endIndex + "]";
		}

	}

	public static int maxSubArraySum(int[] arr) {

		int maxSum = arr[0];
		int sum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			// System.out.println(arr[i]);
			if (sum < 0) {
				if (arr[i] > 0) {
					sum = arr[i];
				} else if (arr[i] == 0) {
					sum = 0;
				} else if (arr[i] < 0) {
					if (sum < arr[i]) {
						sum = arr[i];
					}
				}
				// sum = sum + arr[i];
			} else if (sum == 0) {
				if (arr[i] == 0) {
					continue;
				} else if (arr[i] > 0) {
					sum = sum + arr[i];
				}

			} else if (sum > 0) {
				if (arr[i] > 0) {
					sum = sum + arr[i];
				} else if (arr[i] == 0) {
					sum = sum + arr[i];
				} else if (arr[i] < 0) {
					sum = sum + arr[i];
				}
			}
			if (sum > maxSum) {
				maxSum = sum;
			}
			// sum = sum + arr[i];

		}
		return maxSum;
	}

	public static int dhansuSol(int[] arr) {
		int maxSum, currentSum;
		maxSum = arr[0];
		currentSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int num = arr[i];
			currentSum = Math.max(currentSum + num, num);
			maxSum = Math.max(currentSum, maxSum);
		}
		return maxSum; // - See more at:
						// http://www.ardendertat.com/2011/09/24/programming-interview-questions-3-largest-continuous-sum/#sthash.YXfVud9p.dpuf
	}

	public static ArraySumValues maxSubArraySum2(int[] arr) {

		int maxSum = arr[0];

		int tempStartIndex = 0;
		int startIndex = 0;
		int endIndex = 0;

		int sum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			// System.out.println(arr[i]);
			if (sum < 0) {
				sum = arr[i];
				tempStartIndex = i;
//				if (arr[i] > sum) {
//					startIndex = i;
//				}
				
			} else if (sum == 0) {
				if (arr[i] > 0) {
					sum = arr[i];
					tempStartIndex = i;
				}
			} else if (sum > 0) {
				sum = sum + arr[i];
			}
//			
//			if (sum == arr[i]) {
//				startIndex = i;
//			}

			
			if (sum > maxSum) {
				maxSum = sum;
				startIndex = tempStartIndex;
				endIndex = i;	
			}
			
			
		}

		ArraySumValues val = new ArraySumValues(maxSum, startIndex, endIndex);
		return val;
	}

	public static void main(String[] args) {

		// System.out.println("max Sum: "+ maxSum +
		// " start Index: "+startIndex);
		// for (int i = 1; i<arr.length; i++) {
		// if ((sum + arr[i]) > sum) sum = sum + arr[i];
		// else if ((sum < 0) && (arr[i] > sum)) {
		// sum = arr[i];
		// }
		// // else if (arr[i] < 0) {
		// // int sumtemp = arr[i];
		// // while (sumtemp <= 0) {
		// // i++;
		// // if (i >= arr.length) break;
		// // sumtemp = sumtemp + arr[i];
		// // }
		// // if (sumtemp > 0) sum = sum + sumtemp;
		// // }
		// //
		// }
		int pass = 0;
		int fail = 0;
		int idxpass = 0;
		int idxfail = 0;
		for (int i = 0; i < 10000; i++) {

			
			
			int[] arr = ArrayUtil.getRandomIntArray(10, -10, 10);
			// int[] arr = { -4, -8, 1, 1, -2, 6, 6, -8, 7, 8 };
			// int []arr = {-7, -9, -3, -6, -3, -3, -8, -4, -6, 0};
			// int[] arr = { -3, -6, -8, -2, -2, -2, -1, -2, -9, -2 };
			// int[] arr = { 5, 1, 4, -5, 3, 4, 2, -2, 4, 9 };
			//int[] arr = {2, -3, -9, 1, 4, 0, 9, 7, 9, -6};
//			int[] arr = {7, 0, 8, -1, -7, -5, -5, 7, -1, 6};
			
			
			
			
			// System.out.println(Arrays.toString(arr));
			// int maxSum1 = maxSubArraySum(arr);
			// ---------------------------------------------------------
			 ArraySumValues maxSumVals = maxSubArraySum2(arr); 
			
			 int maxSum1 = maxSumVals.sum;
			 int testIndex = 0;
			 for (int i1 = maxSumVals.startIndex; i1<=maxSumVals.endIndex;i1++) {
				 testIndex = testIndex + arr[i1];
			 }
			 System.out.println(Arrays.toString(arr) + " | " + maxSumVals);
			 if (maxSum1 != testIndex) {
				 System.out.println("Index Values Failed");
				 System.out.println("testIndex: "+testIndex + " maxSum1: "+maxSum1);
				 idxfail++;
			 } else {
				 idxpass++;
			 }
			 //-----------------------------------------------------------
			// int maxSum1 = sumSubArrVinay(arr);
//			int maxSum1 = dhansuSol(arr);

			int maxSum2 = ExampleLargestContinousSum.maxSubArraySum(arr);
			// System.out.println(" largest sum: " + maxSum1);
			// System.out.println(" largest sum example: " + maxSum2);
			// + ExampleLargestContinousSum.maxSubArraySum(arr, arr.length));
			if (maxSum1 != maxSum2) {
				System.out.println("Fail for :" + Arrays.toString(arr));
				System.out.println(" largest sum: " + maxSum1);
				System.out.println(" largest sum example: " + maxSum2);
				System.out.println(Arrays.toString(arr) + " | " + maxSumVals);
				fail++;
			} else {
				// System.out.println(" pass " + i);
				pass++;
			}
			
		}
		
		System.out.println("\nPass :" + pass);
		System.out.println("Fail :" + fail);
		System.out.println("\nidxPass :" + idxpass);
		System.out.println("idxFail :" + idxfail);

	}

//	public static int sumSubArrVinay(int[] arr) {
//		int start = 0, end = 0;
//		// int[] arr=new int[]{1,-2,3,-5,2,-4,8};
//		// int[] arr = { -4, -8, 1, 1, -2, 6, 6, -8, 7, 8 };
//		// int []arr = {-7, -9, -3, -6, -3, -3, -8, -4, -6, 0};
//		// int[] arr = { -3, -6, -8, -2, -2, -2, -1, -2, -9, -2 };
//		int maxVal = arr[0];
//		for (int len = 0; len <= arr.length; len++) {
//			for (int i = 0; i + len <= arr.length; i++) {
//				int max = findMax(arr, i, len, maxVal);
//				if (max > maxVal) {
//					maxVal = max;
//					start = i;
//					end = start + len;
//				}
//			}
//		}
//		// System.out.println(maxVal+"["+start+","+end+"]");
//		return maxVal;
//	}
//
//	private static int findMax(int[] arr, int start, int len, int max) {
//		int m = 0;
//		for (int i = start; i < start + len; i++) {
//			m += arr[i];
//		}
//		if (m > max)
//			max = m;
//		return max;
//	}
}
