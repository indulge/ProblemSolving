package sg.ds.arrays;

/**
 * https://www.algoexpert.io/questions
 */
public class AlgoExpert {
    /**
     * Q1: Two number sum. Given an array, and a number, find a pair in array, which sums unto given number.
     *
     * s1: n^2, two loops, each pair compare to sum.
     * s2: check if current num has complimentary number in hash, if not, store, sum-num in hash.: space, time: o(n)
     * s3: Sort, and keep l, r pointers. Move, left if l+r<sum, move right if  l+r > sum. time n*(log(n)), space constant.
     */

    /**
     * Q2: Three number sum. Given an array, and a number, find all triplets in array, which sums upto given number.
     *
     * S1: n^3 sol, three nested loops. constant space.
     * S2: Sort, keep, left1, left2, right pointers, keep moving left1 & match sum-left1 using left2 & right.
     * nlogn + n^2 + n
     * Complexity: n^2 runtime. constant space.
     */

    /**
     * Q3: Given two arrays, find a pair of numbers, one from each array, whose absolute difference is closest to 0.
     * trivial: NxM each pair difference compare to 0 and keep smallest.
     * sort both, & left1 in array1 & left2 in array2. if l1 < l2; l1++, else l2++. Keep track of smallest difference.
     */

    /**
     * Q4: Given an array, and a number, move all occurrences of that number to end of array.
     * S1: TODO: Recursive?
     * S2: TODO: DP?
     * S3: find two pairs.
     *
     */

    /**
     * TODO-----------------------------------------
     */

    /**
     * Q5: Four number sum,  Given an array, and a number, find all 4-tupples in array, which sums upto given number.
     */

    /**
     * ------------------Missing--------------
     */

    /**
     * Rotate an array, given an array and number of elements to shift by.
     */
}
