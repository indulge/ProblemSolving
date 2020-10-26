package sg.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Variations:
 *  1) Recursive
 *  2) O(n) + O(n)
 *  3) O(n) + O(1)
 *  4) Max Sum + index of number summing up.
 *  5) with -ve numbers.
 */
public class MaxSumNonAdjacent {
    public static void main(String[] args) {
        int[] input = {10, 5, 20, 25, 15, 5, 5, 15, 3, 15, 5, 5, 15};
//        int[] input = {1, 2};
        int sol  = Program.maxSubsetSumNoAdjacent(input);
        System.out.println("Rec Sol:"+sol);

        sol  = Program2.maxSubsetSumNoAdjacent(input);
        System.out.println("DP Sol:"+sol);
    }


static class Program2 {
    public static int maxSubsetSumNoAdjacent(int[] array) {
        System.out.println("array: "+ Arrays.toString(array));
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int[] arraySolution = new int[array.length];
        arraySolution[0] = array[0];
        arraySolution[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length ; i++) {
            int sumPick = array[i] + arraySolution[i - 2];
            System.out.println("sumPick: "+sumPick);
            int sumSkip = arraySolution[i - 1];
            System.out.println("sumSkip: "+sumPick);
            arraySolution[i] = Math.max(sumPick, sumSkip);
            System.out.println("arraySolution: "+Arrays.toString(arraySolution));
        }
        return arraySolution[arraySolution.length-1];
    }
}

static class Program {
    public static int maxSubsetSumNoAdjacent(int[] array) {
        System.out.println("array: "+ Arrays.toString(array));
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int maxSum = 0;
        for (int i=0; i<array.length; i++) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            Map<Integer, Boolean> cantVisitPick = new HashMap<>();
            cantVisitPick.put(i-1, true);
            cantVisitPick.put(i, true);
            cantVisitPick.put(i+1, true);
            System.out.println("Picking, Sum | "+array[i] + "," + array[i]);
            int sumPick = maxSumRec(array[i], array, cantVisitPick);
            System.out.println("sumPick: "+sumPick);
            Map<Integer, Boolean> cantVisitSkip = new HashMap<>();
            cantVisitSkip.put(i, true);
            System.out.println("Skipping, Sum | "+array[i] + "," + 0);
            int sumSkip = maxSumRec(0, array, cantVisitSkip);
            System.out.println("sumSkip: "+sumPick);
            if (Math.max(sumPick, sumSkip) > maxSum) maxSum = Math.max(sumPick, sumSkip);
            System.out.println("~~~~~~~~~~~"+maxSum+"~~~~~~~~~~~~~~~~~");
        }
        return maxSum;
    }
    public static int maxSumRec(int sumSoFar, int[] array, Map<Integer, Boolean> cantVisit) {
        Map<Integer, Boolean> cantVisitPick = new HashMap<>();
        cantVisitPick.putAll(cantVisit);
        Map<Integer, Boolean> cantVisitSkip = new HashMap<>();
        cantVisitSkip.putAll(cantVisit);
        System.out.println("cantVisit: "+cantVisit);
        for (int i = 0; i < array.length; i++) {
            if (cantVisit.get(i) != null && cantVisit.get(i)) continue;
            cantVisitPick.put(i-1, true);
            cantVisitPick.put(i, true);
            cantVisitPick.put(i+1, true);
            System.out.println(" Picking, Sum | "+ array[i] +" , " + (sumSoFar  + array[i]));
            int sumPick = maxSumRec(sumSoFar + array[i], array, cantVisitPick);
            System.out.println("sumPick: "+sumPick);
            cantVisitSkip.put(i, true);
            System.out.println("Skipping, Sum | "+array[i] + "," + sumSoFar);
            int sumSkip = maxSumRec(sumSoFar, array, cantVisitSkip);
            System.out.println("sumSkip: "+sumSkip);
            System.out.println("Max: "+Math.max(sumPick, sumSkip));
            return Math.max(sumPick, sumSkip);
        }
        return sumSoFar;
    }
}
}