package sg.ds.arrays;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Playground
 */
public class FourSumProblem {
    public static void main(String[] args) {
        Map<Integer, List<Integer>>  abc= new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry:abc.entrySet()) {
            entry.getValue().toArray();
        }


    }

    @Test
    public void TestCase2() {
        List<Integer[]> output = Program.fourNumberSum(new int[] {7, 6, 4, -1, 1, 2}, 16);
        List<Integer[]> quadruplets = new ArrayList<Integer[]>();
        quadruplets.add(new Integer[] {7, 6, 4, -1});
        quadruplets.add(new Integer[] {7, 6, 1, 2});
        assertTrue(quadruplets.size() == output.size());
        assertTrue(this.compare(quadruplets, output));
    }

    @Test
    public void TestCase3() {
        List<Integer[]> output = Program.fourNumberSum(new int[] {5, -5, -2, 2, 3, -3}, 0);
        List<Integer[]> quadruplets = new ArrayList<Integer[]>();
        quadruplets.add(new Integer[] {5,-5,-2,2});
        quadruplets.add(new Integer[] {-5,5,3,-3});
        quadruplets.add(new Integer[] {-2,2,3,-3});
        assertTrue(quadruplets.size() == output.size());
        assertTrue(this.compare(quadruplets, output));
    }

    private boolean compare(List<Integer[]> quads1, List<Integer[]> quads2) {
        for (Integer[] quad : quads2) {
            Arrays.sort(quad);
        }
        for (Integer[] quad : quads1) {
            Arrays.sort(quad);
        }
        for (Integer[] quad2 : quads2) {
            boolean found = false;
            for (Integer[] quad1 : quads1) {
                if (Arrays.equals(quad2, quad1)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                return false;
            }
        }
        return true;
    }

}

class Program {
    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        System.out.println("input: "+ Arrays.toString(array));
        System.out.println("targetSum: "+targetSum);
        List<Integer[]> solution = new ArrayList<>();
        if (array.length < 4) {
            return solution;
        }
        Map<Integer, List<List<Integer>>> tempSol = new HashMap<>();
        int counter = 2;
        while (counter < array.length) {
            System.out.println("\n~~~~\n");
            //store previous values into table.
            for (int i = counter - 2; i >= 0; i--) {
                System.out.println("counter: "+counter+" innerCounter: "+i);
                int sum = array[counter - 1] + array[i];
                // assuming there are no duplicates in array.
                List<Integer> pair = new ArrayList<Integer>();
                pair.add(array[counter - 1]);
                pair.add(array[i]);
                if (tempSol.get(sum) == null) {
                    tempSol.put(sum, new ArrayList<>());
                }
                tempSol.get(sum).add(pair);
            }
            System.out.println("tempsol: "+tempSol);

            for (int i=counter+1; i<array.length; i++) {
                int sum=array[counter]+array[i];
                int required = targetSum - sum;

                if (tempSol.get(required) != null) {
                    Integer[] aSol = new Integer[4];
                    for (List<Integer> pair:tempSol.get(required)) {
                        aSol[0] = pair.get(0);
                        aSol[1] = pair.get(1);
                        aSol[2] = array[counter];
                        aSol[3] = array[i];
                        solution.add(aSol);
                    }
                }
                System.out.println("sum: "+sum+" required: "+required+" doesExist: "+(tempSol.get(required)!=null));
                System.out.println("updated tempsol: ");
                printSol(solution);
            }
            counter++;
        }

        return solution;
    }

    private static void printSol(List<Integer[]> solution) {
        for (Integer[] sol:solution) {
            System.out.print(Arrays.toString(sol)+" ");
        }
        System.out.println("");
    }
}

//
//if (i>0 && i<array.length-1) {
//        if (number < array[i-1] || number > array[i+1]) {
//        return false;
//        }
//        } else if (i==0) {
//        if (number > array[1]) return false;
//        } else if (i == array.length - 1) {
//        if (number < array[array.length - 2]) return false;
//        } else {
//        throw new IllegalArgumentException("illegal index value");
//        }
//
//        if (!isSorted) {
//        isLargestOrSmallest(array[i], largestUnsorted, smallestUnsorted, solutionIndexes);
//        }
//        }