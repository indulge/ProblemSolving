package sg.ds.stacks;

import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        String test1 = "()";
        printResult(test1, Program.balancedBrackets(test1));
    }

    static void printResult(String test, boolean result) {
        System.out.println(test+":"+result);
    }
    static class Program {

        public static boolean balancedBrackets(String str) {
            if (str == null) return false;
            if (str.isEmpty()) return true;

            Stack<Character> stk = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                Character c = str.charAt(i);
                if (isOpen(c)) {
                    stk.push(c);
                } else if (isClose(c)) {
                    if (stk.empty()) return false;
                    if (!closingOf(stk.pop(), c)) return false;
                }
            }
            if (stk.empty()) {
                return true;
            }
            return false;
        }

        private static boolean closingOf(Character open, Character close) {
            if (open.equals("(".charAt(0)) && close.equals(")".charAt(0))) return true;
            if (open.equals("{".charAt(0)) && close.equals("}".charAt(0))) return true;
            if (open.equals("[".charAt(0)) && close.equals("]".charAt(0))) return true;
            return false;
        }
        private static boolean isOpen(Character c) {
            if (c == "[".charAt(0) || c == "{".charAt(0) || c == "(".charAt(0)) return true;
            return false;
        }

        private static boolean isClose(Character c) {
            if (c == "]".charAt(0) || c == "}".charAt(0) || c == ")".charAt(0)) return true;
            return false;
        }
    }

}
