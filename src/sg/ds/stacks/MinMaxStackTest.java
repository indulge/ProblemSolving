package sg.ds.stacks;

import java.util.LinkedList;
import java.util.List;

public class MinMaxStackTest {



    public static void testMinMaxPeek(int min, int max, int peek, Program.MinMaxStack stack) {
        stack.printStacks("testMinMaxPeek");
        stack.printMinMaxPeek("testMinMaxPeek");
        System.out.println(stack.getMin() == min);
        System.out.println(stack.getMax() == max);
        System.out.println(stack.peek() == peek);
    }
    void test() {
        Program.MinMaxStack stack = new Program.MinMaxStack();
        stack.push(2);
        stack.printStacks("");
        stack.printMinMaxPeek("");

        stack.push(7);
        stack.printStacks("");
        stack.printMinMaxPeek("");

        stack.push(1);
        stack.printStacks("");
        stack.printMinMaxPeek("test");
    }
    public static void main(String[] args) {
        TestCase2();
    }

    public static void TestCase2() {
        Program.MinMaxStack stack = new Program.MinMaxStack();
        stack.push(5);
        testMinMaxPeek(5, 5, 5, stack);
        stack.push(5);
        testMinMaxPeek(5, 5, 5, stack);
        stack.push(5);
        testMinMaxPeek(5, 5, 5, stack);
        stack.push(5);
        testMinMaxPeek(5, 5, 5, stack);
        stack.push(8);
        testMinMaxPeek(5, 8, 8, stack);
        stack.push(8);
        testMinMaxPeek(5, 8, 8, stack);
        stack.push(0);
        testMinMaxPeek(0, 8, 0, stack);
        stack.push(8);
        testMinMaxPeek(0, 8, 8, stack);
        stack.push(9);
        testMinMaxPeek(0, 9, 9, stack);
        stack.push(5);
        testMinMaxPeek(0, 9, 5, stack);
        System.out.println(stack.pop() == 5);
        testMinMaxPeek(0, 9, 9, stack);
        System.out.println(stack.pop() == 9);
        testMinMaxPeek(0, 8, 8, stack);
        System.out.println(stack.pop() == 8);
        testMinMaxPeek(0, 8, 0, stack);
        System.out.println(stack.pop() == 0);
        testMinMaxPeek(5, 8, 8, stack);
        System.out.println(stack.pop() == 8);
        testMinMaxPeek(5, 8, 8, stack);
        System.out.println(stack.pop() == 8);
        testMinMaxPeek(5, 5, 5, stack);
        System.out.println(stack.pop() == 5);
        testMinMaxPeek(5, 5, 5, stack);
        System.out.println(stack.pop() == 5);
        testMinMaxPeek(5, 5, 5, stack);
        System.out.println(stack.pop() == 5);
        testMinMaxPeek(5, 5, 5, stack);
        System.out.println(stack.pop() == 5);
    }
    
    static void testcase1() {
            Program.MinMaxStack stack = new Program.MinMaxStack();
            stack.push(2);
            testMinMaxPeek(2, 2, 2, stack);
            stack.push(7);
            testMinMaxPeek(2, 7, 7, stack);
            stack.push(1);
            testMinMaxPeek(1, 7, 1, stack);
            stack.push(8);
            testMinMaxPeek(1, 8, 8, stack);
            stack.push(3);
            testMinMaxPeek(1, 8, 3, stack);
            stack.push(9);
            testMinMaxPeek(1, 9, 9, stack);
            System.out.println(stack.pop() == 9);
            testMinMaxPeek(1, 8, 3, stack);
            System.out.println(stack.pop() == 3);
            testMinMaxPeek(1, 8, 8, stack);
            System.out.println(stack.pop() == 8);
            testMinMaxPeek(1, 7, 1, stack);
            System.out.println(stack.pop() == 1);
            testMinMaxPeek(2, 7, 7, stack);
            System.out.println(stack.pop() == 7);
            testMinMaxPeek(2, 2, 2, stack);
            System.out.println(stack.pop() == 2);

    }

    static class Program {

        static class MinMaxStack {
            List<Integer> stack = new LinkedList<>();
            List<Integer> maxStack = new LinkedList<>();
            List<Integer> minStack = new LinkedList<>();

            public void printStacks(String msg) {
                System.out.println(msg);
                System.out.println("Stk:"+stack);
                System.out.println("MinStk:"+minStack);
                System.out.println("MaxStk:"+maxStack);
            }

            public void printMinMaxPeek(String msg) {
                System.out.println(msg);
                System.out.println("min:"+getMin());
                System.out.println("max:"+getMax());
                System.out.println("peek:"+peek());
            }

            public int peek() {
                if (stack.size() > 0)
                return stack.get(stack.size()-1);

                return -1;
            }

            public int pop() {
                if (stack.size() > 0) {
                    Integer num = stack.remove(stack.size()-1);

                    if (minStack.size() > 0 && num == minStack.get(minStack.size()-1))
                        minStack.remove(minStack.size()-1);
                    if (maxStack.size() > 0 && num == maxStack.get(maxStack.size()-1))
                        maxStack.remove(maxStack.size()-1);
                    return num;
                }
                printMinMaxPeek("Pop:");
                printStacks("Pop:");
                return -1;
            }

            public void push(Integer number) {
                stack.add(number);
                if (stack.size() == 1) {
                    minStack.add(number);
                    maxStack.add(number);
                }
                if (number <= minStack.get(minStack.size()-1)) {
                    minStack.add(number);
                }
                if(number >= maxStack.get(maxStack.size()-1)) {
                    maxStack.add(number);
                }
            }

            public int getMin() {
                if (minStack.size() > 0)
                return minStack.get(minStack.size()-1);

                return -1;
            }

            public int getMax() {
                if (maxStack.size() > 0)
                return maxStack.get(maxStack.size()-1);

                return -1;
            }
        }
    }

}


