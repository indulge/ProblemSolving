package sg.recursion;

import java.util.*;

public class InterWovenStrings {

    static Map<Pair, List<String>> stringMap = new HashMap<>();
    static Map<Pair, List<Boolean>> cacheAll = new HashMap<>();
    static Map<Pair, Boolean> cache = new HashMap<>();

    static boolean enableCache = true;
    static boolean method1 = true;
    static boolean method2 = true;

    private static void init() {
        stringMap = new HashMap<>();
        cacheAll = new HashMap<>();
        cache = new HashMap<>();
    }

    private static class Test {
        final String one;
        final String two;
        final String three;

        public Test(String one, String two, String three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        static Test t1 = new Test("aaa", "aaaf", "aaafaaa");

    }

    public static void main(String[] args) {
        List<Boolean> results = new ArrayList<>();
        boolean result = false;
        result = test1WithCache();
        results.add(result);
        result =test1WithOutCache();
        results.add(result);
        result = test1Method2();
        results.add(result);
        result = test1Method2WithCache();
        results.add(result);
        System.out.println("Results:"+results);
    }

    private static boolean test1WithCache() {
        method1 = true;
        method2 = false;
        enableCache = true;
        init();
        boolean result = false;
        result = Program.interweavingStrings(Test.t1.one, Test.t1.two, Test.t1.three);
        printResult(Test.t1.three, result);
        return result;
    }

    private static boolean test1WithOutCache() {
        method1 = true;
        method2 = false;
        enableCache = false;
        init();
        boolean result = false;
        result = Program.interweavingStrings(Test.t1.one, Test.t1.two, Test.t1.three);
        printResult(Test.t1.three, result);
        return result;
    }

    private static boolean test1Method2() {
        method1 = false;
        method2 = true;
        enableCache = false;
        init();
        boolean result = false;
        result = Program.interweavingStrings(Test.t1.one, Test.t1.two, Test.t1.three);
        printResult(Test.t1.three, result);
        return result;
    }

    private static boolean test1Method2WithCache() {
        method1 = false;
        method2 = true;
        enableCache = true;
        init();
        boolean result = false;
        result = Program.interweavingStrings(Test.t1.one, Test.t1.two, Test.t1.three);
        printResult(Test.t1.three, result);
        return result;
    }

    private static void printResult(String three, boolean result) {
        System.out.println("~~~~~~~~~~~~~can weave: " + three + ": " + result +"\n");
        stringMap.forEach((k, v) -> {
            if (enableCache)
                System.out.println(k + " : " + v + " Cache All: " + cacheAll.get(k) + " Cache: " + cache.get(k));
            else
                System.out.println(k + " : " + v + " Cache All: " + cacheAll.get(k));
        });
    }

    private static class Program {
        public static boolean interweavingStrings(String one, String two, String three) {

            if (one.length() + two.length() != three.length()) {
                System.out.println("One + Two != Three");
                return false;
            }

            if (method1) {
                System.out.println("\nMethod 1: (EnableCache: " + enableCache + ")");
                boolean m1 = interWeavingString(one, two, 0, 0, "", three);
                if (!method2) return m1;
            }

            if (method2) {
                System.out.println("\nMethod 2: (EnableCache:+" + enableCache + ")");
                boolean m2 = interWeavingString(one, two, 0, 0, 0, "", three);
                return m2;
            }

            return false;

        }

        //Method 1. Insane recursion.
        private static boolean interWeavingString(String one, String two, int index1, int index2,
                                                  String currentString,
                                                  String three) {

            System.out.print("Rec String: " + currentString);
            if (enableCache) {
                Boolean cacheHit = cache.get(new Pair(index1, index2));
                if (cacheHit != null) {
                    System.out.print(" Cache Hit Value: " + cacheHit);
                    System.out.print(" "+new Pair(index1, index2));
                    System.out.print(" "+cache);
                    System.out.println("");
                    return cacheHit;
                } else {
                    System.out.print(" Cache Miss");
                }
                System.out.print(" "+new Pair(index1, index2));
                System.out.print(" "+cache);
            }
            System.out.println("");


            List<String> strings =
                    stringMap.computeIfAbsent(new Pair(index1, index2), (k) -> new ArrayList<>());
            strings.add(currentString);

            if (currentString != null && currentString.equalsIgnoreCase(three)) {
                System.out.println("Method 1: found: " + currentString);
                List<Boolean> caches = cacheAll.computeIfAbsent(new Pair(index1, index2), (k) -> new ArrayList<>());
                if (enableCache) cache.put(new Pair(index1, index2), true);
                caches.add(true);
                return true;
            } else if (currentString.length() == three.length()) {
                cacheAll.computeIfAbsent(new Pair(index1, index2), (k) -> new ArrayList<>()).add(false);
                if (enableCache) cache.put(new Pair(index1, index2), false);
                return false;
            }

            boolean found1 = false;
            boolean found2 = false;

            if (index1 < one.length()) {
                found1 = interWeavingString(one, two, index1 + 1, index2,
                        currentString + one.charAt(index1), three);
//                System.out.println("Ret1 String: " + currentString);
                List<Boolean> caches = cacheAll.computeIfAbsent(new Pair(index1, index2), (k) -> new ArrayList<>());
                caches.add(found1);
            }

            if (index2 < two.length()) {
                found2 = interWeavingString(one, two, index1, index2 + 1,
                        currentString + two.charAt(index2),
                        three);
//                System.out.println("Ret2 String: " + currentString);
                List<Boolean> caches = cacheAll.computeIfAbsent(new Pair(index1, index2), (k) -> new ArrayList<>());
                caches.add(found2);

            }
            if (enableCache)
                cache.put(new Pair(index1, index2), found1||found2) ;
            return found1 || found2;
        }

        //method 2: recurse only for first char match.
        private static boolean interWeavingString(String one, String two,
                                                  int index1, int index2, int index3,
                                                  String currentString,
                                                  String three) {

            System.out.print("Rec String: " + currentString);
            if (enableCache) {
                Boolean cacheHit = cache.get(new Pair(index1, index2));
                if (cacheHit != null) {
                    System.out.print(" Cache Hit Value: " + cacheHit);
                    System.out.print(" "+new Pair(index1, index2));
                    System.out.print(" "+cache);
                    System.out.println("");
                    return cacheHit;
                } else {
                    System.out.print(" Cache Miss");
                }
                System.out.print(" "+new Pair(index1, index2));
                System.out.print(" "+cache);
            }
            System.out.println("");

            if (currentString != null && currentString.equalsIgnoreCase(three)) {
                System.out.println("Method 2: found: " + currentString);
                return true;
            }
            boolean found = false;

            if (index1 < one.length() && (one.charAt(index1) == three.charAt(index3))) {
                found = interWeavingString(one, two, index1 + 1, index2, index3 + 1,
                        currentString + one.charAt(index1), three);
//                System.out.println("Ret1 String: "+currentString);
            }
            if (!found && index2 < two.length() && (two.charAt(index2) == three.charAt(index3))) {
                found = interWeavingString(one, two, index1, index2 + 1, index3 + 1,
                        currentString + two.charAt(index2),
                        three);
//                System.out.println("Ret2 String: "+currentString);
            }
            if (enableCache)
                cache.put(new Pair(index1, index2), found) ;
            return found;
        }
    }

    private static class Pair {
        final int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a &&
                    b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
        }
    }
}




