package sg.rec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1,2,3);
        System.out.println("\n~~~~~~~~~~~~~~~\nFinal Solution: "+Program.powerset(array)+"\n~~~~~~~~~");
        System.out.println("\n~~~~~~~~~~~~~~~\nFinal Solution: "+Program.powerset(array).size()+"\n~~~~~~~~~");

        System.out.println("\n~~~~~~~~~~~~~~~\nFinal Solution: "+Program.powerset2(array)+"\n~~~~~~~~~");
        System.out.println("\n~~~~~~~~~~~~~~~\nFinal Solution: "+Program.powerset2(array).size()+"\n~~~~~~~~~");
    }

    static class Program {
        public static List<List<Integer>> powerset(List<Integer> array) {
            List<List<Integer>> solutions =  new ArrayList<>();
            helper(new ArrayList<>(), array,solutions);
            return solutions;
        }

        private static void helper(List<Integer> picked, List<Integer> array, List<List<Integer>> solutions) {
            if (array.size() == 0) {
                solutions.add(picked);
                return;
            }

            List<Integer> newPicked = new ArrayList<>(picked);
            List<Integer> newArray = new ArrayList<>(array);
            Integer removedNum = newArray.remove(0);
            picked.add(removedNum);

            //pick
            helper(newPicked, newArray, solutions);

            //skip
            helper(picked, newArray, solutions);
        }

        public static List<List<Integer>> powerset2(List<Integer> array) {
            List<List<Integer>> solutions =  new ArrayList<>();
            solutions.add(new ArrayList<>());
            for (Integer element:array) {
                for (int i = solutions.size()-1; i >=0; i--) {
                    List<Integer> newSubset = new ArrayList<>();
                    newSubset.add(element);
                    newSubset.addAll(solutions.get(i));
                    solutions.add(newSubset);
                }
            }
            return solutions;
        }
    }
}
