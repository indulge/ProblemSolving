package sg.practice.algoexpert.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SingleCycleCheck {
    class Template {
        class Program {
            public boolean hasSingleCycle(int[] array) {
                // Write your code here.
                return false;
            }
        }
    }

    class Solution2 {
        class Program {
            public boolean hasSingleCycle(int[] array) {
                System.out.println(Arrays.toString(array));

                //another trick is:
                //1. make sure after array.size iterations, current index == 0
                //2. And also that index 0 is never reached while iterating through array.
                Set<Integer> tracker = new HashSet<>();

                //Tricky part: first index is to be calculated from array[0], not value = 0;
                int initIndex = nextIndex(array[0], 0, array.length);
                tracker.add(initIndex);
                for (int i = 0, index = initIndex; i < array.length; i++) {
                    int addValue = array[index];
                    index = nextIndex(index, addValue, array.length);
                    System.out.println("nextIndex = " + index);
                    tracker.add(index);
                }
                if (tracker.size() == array.length) return true;
                return false;
            }

            private int nextIndex(int index, int addValue, int size) {
                addValue = addValue % size;
                int nextIndex = (index + addValue);
                if (nextIndex < 0) return size + nextIndex;
                return nextIndex % size;
            }
        }
    }

}