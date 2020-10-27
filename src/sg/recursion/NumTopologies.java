package sg.recursion;

public class NumTopologies {
    public static void main(String[] args) throws Exception {
        Program test = new Program();
        System.out.println(test.numberOfBinaryTreeTopologies(2));
        System.out.println(test.numberOfBinaryTreeTopologies(3));
        System.out.println(test.numberOfBinaryTreeTopologies(4));
        System.out.println(test.numberOfBinaryTreeTopologies(5));
    }
}

class Program {
    public static int numberOfBinaryTreeTopologies(int n) {
        if (n==0 || n==1) return 1;
//        int[] cache = new int[n+1];
//        cache[0] = 1;
//        cache[1] = 1;
//        for (int i = 2; i < n; i++) {
//            cache[i] = -1;
//        }
//        return numberOfBinaryTreeTopologies(n - 1, cache);
        return numberOfBinaryTreeTopologiesRec(n);
    }

    public static int numberOfBinaryTreeTopologiesRec(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        int sum = 0;
        for (int i = 0; i <= n - 1; i++) {
            int ltrees = numberOfBinaryTreeTopologiesRec(i);
            int rtrees = numberOfBinaryTreeTopologiesRec(n-i-1);
            sum += ltrees * rtrees;
        }
        return sum;
    }
////    public static int numberOfBinaryTreeTopologies(int n, int[] cache) throws Exception {
//        if (n == 0) return 1;
//        if (cache[n] > 0) return cache[n];
//        if (n > 0) {
//            int left = numberOfBinaryTreeTopologies(n - 1, cache);
//            int right = numberOfBinaryTreeTopologies(n - 1, cache);
//            int leftAndRight = 0;
//            if (n >= 2) {
//                leftAndRight = numberOfBinaryTreeTopologies(n - 2, cache);
//            }
//            return left + right + leftAndRight;
//        } else {
//            //Exception scenario
//            throw new Exception("n < 0 n:"+n);
//        }
//    }
}