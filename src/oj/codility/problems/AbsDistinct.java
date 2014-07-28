package oj.codility.problems;
import java.util.HashSet;
import java.util.Set;

public class AbsDistinct {
	
	
	public static int countDistinct(int[] nums) {
	    int lastLeft = Math.abs(nums[0]);
	    int lastRight = Math.abs(nums[nums.length - 1]);
	    int count = 0;
	    for (int a = 1, b = nums.length - 2; a <= b;) {
	        int left = Math.abs(nums[a]);
	        int right = Math.abs(nums[b]);
	        if (left == lastLeft) {
	            a++;
	            lastLeft = left;
	        } else if (right == lastRight) {
	            b--;
	            lastRight = right;
	        } else if (lastLeft == lastRight) {
	            a++;
	            b--;
	            lastLeft = left;
	            lastRight = right;
	            count++;
	        } else if (lastLeft > lastRight) {
	            count++;
	            a++;
	            lastLeft = left;
	        } else {
	            count++;
	            b--;
	            lastRight = right;
	        }
	    }
	    count += (lastLeft == lastRight ? 1 : 2);
	    return count;
	}
	
	
	public int countDistinctUsingSet(int[] nums) {
	    Set<Integer> s = new HashSet<Integer>();
	    for (int n : nums)
	        s.add(Math.abs(n));
	    int count = s.size();
	    return count;
	}
	
	public int countAbsoluteDistinct(int[] arr) {
		if (arr == null) {
			return 0;
		}
		if (arr.length < 2) {
			return 1;
		}

		int leftp = 0;
		int rightp = arr.length - 1;
		int count = 0;
		
		while (  (leftp < arr.length) && (leftp < rightp)  ) {
			while (Math.abs(arr[rightp]) >= Math.abs(arr[leftp])) {
				if (Math.abs(arr[rightp]) > Math.abs(arr[leftp])) {
					count++;
				}
				rightp--;
				if (rightp == leftp)
					break;
			}
			leftp++;
			count++;
			if (leftp >= arr.length) {
				return count;
			}

			while (arr[leftp - 1] == arr[leftp]) {
				leftp++;
				if (leftp >= arr.length || leftp >= rightp) {
					return count;
				}
			}
		}
		
		return count;

	}
	
//	public static void main(String[] args) {
//		AbsDistinct abs = new AbsDistinct();
//		
//		int[] arr = 
//		//{1,1,1,2,-1}
//		//{-5,-3,0,1,-3}
//		{-5,-5,-6,-6,-6} 
//		;
//		Arrays.sort(arr);
//		int count = abs.countAbsoluteDistinct(arr);
//		
//		System.out.println(count);
//	}
//	
	
//	public static void main(String[] args) throws Exception {
//		
//		AbsDistinct abs = new AbsDistinct();
//		
//	    for (int len : new int[]{100 * 1000 * 1000, 10 * 1000 * 1000, 1000 * 1000, 100 * 1000, 10 * 1000, 1000}) {
//	        int[] nums = new int[len];
//	        for (int i = 0; i < len; i++)
//	            nums[i] = (int) (Math.random() * (Math.random() * 2001 - 1000));
//	        Arrays.sort(nums);
//
//	        long timeArray = 0;
//	        long timeSet = 0;
//	        int runs = len > 1000 * 1000 ? 10 : len >= 100 * 1000 ? 100 : 1000;
//	        for (int i = 0; i < runs; i++) {
//	            long time1 = System.nanoTime();
//	            int count = abs.countAbsoluteDistinct(nums);
//	            long time2 = System.nanoTime();
//	            int count2 = abs.countDistinctUsingSet(nums);
//	            long time3 = System.nanoTime();
//	            timeArray += time2 - time1;
//	            timeSet += time3 - time2;
//	            assert count == count2;
//	        }
//	        System.out.printf("For %,d numbers, using an array took %,d us on average, using a Set took %,d us on average, ratio=%.1f%n",
//	                len, timeArray / 1000 / runs, timeSet / 1000 / runs, 1.0 * timeSet / timeArray);
//	    }
//	}
	
	public static void main(String[] arg){
		
		AbsDistinct abs = new AbsDistinct();
		//int[] a = {34,2,3,4,3,-2,3};
		//out.println("distinct elements are" + abs.countAbsoluteDistinct(a));
//		int[] aa={-5,-3,0,1,3};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(aa));
//		int[] ab={-5,-3,0,1,3, 4, 6, 7};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(ab));
//		int[] ac={-5};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(ac));
//		int[] acc={9};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(acc));
//		int[] ad={9,9,9};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(ad));
//		int[] ae={-5,-5};
//		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(ae));
		int[] aee={1,5,5,5,5};
		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(aee));
		int[] af={-9, -6, -5, -5, -5, -5, -3, -3, 0, 0, 1, 5, 6, 7, 7, 8};
		System.out.println("distinct elements count " + abs.countAbsoluteDistinct(af));

		}
}




