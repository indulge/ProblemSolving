package oj.codility.problems;

import java.util.Arrays;

public class DiscIntersectionCount {
	
	
	public int number_of_disc_intersections(int[] A) {
		int[] leftPoints = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			leftPoints[i] = i - A[i];
		}
		
		Arrays.sort(leftPoints);
//		System.out.println(Arrays.toString(leftPoints));
		int count  = 0;
		for (int i = 0; i < A.length - 1; i++) {
			int rpoint = A[i] + i;

			int rrank = getRank(leftPoints, rpoint);
			
			//if disk has sifnificant radius, exclude own self
			if (rpoint > i) rrank -= 1;
			int rank = rrank; 
//			System.out.println(rpoint+" : "+rank);

			rank -= i;
			count += rank;
		}
		return count;
		
	}
	
	public int getRank(int A[], int num) {
		if (A==null || A.length == 0) return -1;		
		int mid = A.length/2;
		while ((mid >= 0) && (mid < A.length)) {
			
			if (A[mid] == num) return mid;
			
			if ((mid == 0) && (A[mid] > num)) return -1;
			if ((mid == (A.length - 1)) && (A[mid] < num)) return A.length;
			if (A[mid] < num && A[mid + 1] >= num) return mid + 1;
			if (A[mid] > num && A[mid - 1] <= num) return mid - 1;
			
			if (A[mid] < num) mid = (mid + A.length)/2;
			else  mid = (mid)/2;
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		DiscIntersectionCount d = new DiscIntersectionCount();
		int[] A = 
			{1,5,2,1,4,0}
		 	//{0,0,0,0,0,0}
		//	{1,1,2}
		// {3}
		 ;
		int count = d.number_of_disc_intersections(A);
		System.out.println(count);
	}
}
