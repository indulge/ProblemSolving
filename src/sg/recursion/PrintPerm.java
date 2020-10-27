package sg.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintPerm {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1,2,3);
        System.out.println("\n~~~~~~~~~~~~~~~\nFinal Solution: "+getPermutations(array)+"\n~~~~~~~~~");

    }

    public static List<List<Integer>> getPermutations(List<Integer> array) {
        return getPermutations(new ArrayList<>(), array, new ArrayList<>());
    }

    private static List<List<Integer>> getPermutations(List<Integer> aSolution,
                                                       List<Integer> array,
                                                 List<List<Integer>> solution) {
        System.out.println("Recursion: aSolution: "+aSolution+ " array: "+array+ " solution: "+solution);
        if (array.size() <= 0) {
            solution.add(aSolution);
            return solution;
        }
        //for a Solution, Modify in loop, new in recursion
        for (int i = 0; i < array.size(); i++) {
            List<Integer> subArray = getSubArray(array, i);
            List<Integer> nextSol = new ArrayList<>(aSolution);
            nextSol.add(array.get(i));
            getPermutations(nextSol, subArray, solution);
            System.out.println("Return: aSolution: "+aSolution+ " array: "+array+ " solution: "+solution);
            System.out.println("Looping: i: "+(i+1)+ " array: "+array+ " solution: "+solution + " aSolution: "+aSolution);
        }
        System.out.println("EndLoop: aSolution: "+aSolution+ " array: "+array+ " solution: "+solution);
        return solution;
    }

    private static List<Integer> getSubArray(List<Integer> array, int index) {
        List<Integer> subArray = new ArrayList<>();
        if (index < 0) return subArray;
        subArray.addAll(array.subList(0, index));
        if (index<array.size()-1) {
            subArray.addAll(array.subList(index+1, array.size()));
        }
        return subArray;
    }

}



//import java.util.*;
//
//class Program {
//    public static List<List<Integer>> getPermutations(List<Integer> array) {
//        List<List<Integer>> sol = new ArrayList<>();
//        getPermutations(array, 0, sol);
//        return sol;
//    }
//
//    public static List<List<Integer>>
//    getPermutations(List<Integer> array, int index, List<List<Integer>> overAllSolution) {
//        if (index >= array.size()) return new ArrayList<>();
//        Integer element = array.get(index);
//        System.out.println("Array: "+array+" currelem: "+element+" index: "+index);
//        if (index == array.size() - 1) {
//            List<Integer> sol = Arrays.asList(element);
//            overAllSolution.add(sol);
//            return overAllSolution;
//        } else {
//            List<Integer> perm =  getPermutations(array, index+1, overAllSolution);
//            System.out.println("Subsolution for element: "+element+" sub-sol: "+perm);
//            //prefix
//            List<Integer> pre = new ArrayList<>();
//            pre.add(element);
//            pre.addAll(perm);
//            System.out.println("prefix: "+pre);
//
//            //suffix
//            List<Integer> post = new ArrayList<>();
//            post.addAll(perm);
//            post.add(element);
//            System.out.println("suffix: "+post);
//
//            overAllSolution.add(pre);
//            overAllSolution.add(post);
//            System.out.println("overAllSolution: "+overAllSolution);
//
//        }
//        return null;
//    }
//}
