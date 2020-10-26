package sg.dp;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci test = new Fibonacci();
        System.out.println(test.getNthRec(10));
        System.out.println(test.getNthMemo(10, new HashMap<>()));
        System.out.println(test.getNthMemoize(10, new HashMap<>()));
        System.out.println(test.getNthDP(10));
    }

    public long getNthRec(int n) {
           if (n <= 2) return 1;
           else return getNthRec(n-1) + getNthRec(n-2);
    }

    public long getNthMemo(int n, Map<Integer, Long> memo) {
        if (memo.get(n) != null) return memo.get(n);
        if (n <= 2) return 1;
        else {
            long num = getNthMemo(n-1, memo) + getNthMemo(n-2, memo);
            memo.put(n, num);
            return num;
        }
    }

    public long getNthMemoize(long n, Map<Long, Long> cache) {
        for (long i = 0; i <=n ; i++) {
            if (i<=2) {
                cache.put(i, Long.valueOf(1l));
            } else {
                cache.put(i, cache.get(i-1) + cache.get(i-2));
            }
        }
        return cache.get(n);
    }

    public long getNthDP(long n) {
        long n0 = 0;
        long n1 = 1;
        for (long i = 2; i <=n ; i++) {
            long k = n0 + n1;
            n0 = n1;
            n1 = k;
        }
        return n1;
    }
}
