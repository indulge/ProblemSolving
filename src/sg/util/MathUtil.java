package sg.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MathUtil {

	// public static long bigMod(long a, long b, long n) {
	// if (b < 0) {
	// return 0;
	// }
	// if (b == 0) {
	// return 1;
	// }
	// if (b == 1) {
	// return a % n;
	// }
	// if (b % 2 == 0) {
	// return ((long) (Math.pow(bigMod(a, b / 2, n), 2)) % n);
	// } else {
	// return ((a % n) * bigMod(a, (b - 1), n)) % n;
	// }
	// }
	
	//a^b % n
	public static long modPow(long a, long b, long n) {
		BigInteger ba = BigInteger.valueOf(a);
		BigInteger bb = BigInteger.valueOf(b);
		BigInteger bn = BigInteger.valueOf(n);
		BigInteger ret = ba.modPow(bb, bn);
		return ret.longValue();

	}

	public static int bitCount(long n) {
		// int count = 0;
		// while (n > 0) {
		// count++;
		// n &= (n - 1);
		// }
		// return count;

		BigInteger bn = BigInteger.valueOf(n);
		return bn.bitCount();
	}

	public static int bitLength(long n) {
		// int count = 0;
		// while (n > 0) {
		// count++;
		// n >>= 1;
		// }
		// return count;
		BigInteger bn = BigInteger.valueOf(n);
		return bn.bitLength();
	}

	public static boolean isKSparse(long num, int k) {

		if (num <= 0 || num == 1) {
			return false;
		}

		if ((num & (num - 1)) == 0) {
			return true;
		}

		while (num > 0) {
			if ((num & 1) > 0) {
				int zcount = 0;
				num >>= 1;
				while ((num > 0) && ((num & 1) == 0)) {
					zcount++;
					num >>= 1;
				}
				if (zcount < k)
					return false;
			} else {
				num >>= 1;
			}
			if (num == 1) {
				return true;
			}
		}

		return true;
	}

	public static boolean isPrime(long number) {
		if (Math.abs(number) <= 1)
			return false;
		if (number == 2)
			return true;
		if (number % 2 == 0)
			return false;
		for (long i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
		// if (primeFactorsCount(number) == 1) return true;
		// return false;
	}

	public static int uniquePrimeFactorsCount(long number) {
		List<Integer> ret = primeFactors(number);
		Set<Integer> upf = new HashSet<Integer>(ret);
		return upf.size();
	}

	public static Set<Integer> uniquePrimeFactors(long number) {
		List<Integer> ret = primeFactors(number);
		Set<Integer> upf = new HashSet<Integer>(ret);
		return upf;
	}

	public static int primeFactorsCount(long number) {
		return primeFactors(number).size();
	}

	public static List<Integer> primeFactors(long number) {

		List<Integer> ret = new LinkedList<Integer>();
		for (int i = 2; i <= number; i++) {
			if (number % i == 0) {
				ret.add(i);
				number /= i;
				i--;
			}
		}

		return ret;
	}

	public static int largestPrimeFactor(long number) {

		List<Integer> ret = primeFactors(number);
		if (ret.size() > 0) {
			return ret.get(ret.size() - 1);
		}
		return -1;
	}

	// public static double factorialRec(int num) {
	// if (num == 0 || num == 1)
	// return 1;
	//
	// return num * factorialRec(num - 1);
	// }

	public static int digitSum(long num) {
		int ret = 0;
		while (num > 0) {
			ret += num % 10;
			num /= 10;
		}
		return ret;
	}

	public static int bigTrailingZero(BigInteger num) {
		int ret = 0;
		
		BigInteger b10 = BigInteger.valueOf(10);
		while (num.compareTo(BigInteger.ZERO) > 0) {
			// bret = bret.add();
			if (num.mod(b10).compareTo(BigInteger.ZERO) == 0)
				ret++;
			else
				break;
			num = num.divide(b10);
		}
		return ret;

	}

	public static BigInteger bigFactorial(int num) {
		BigInteger ret = BigInteger.ONE;
		for (int i = 2; i < num; i++) {
			ret = ret.multiply(BigInteger.valueOf(i));
		}
		return ret;
	}

	public static int bigDigitSum(BigInteger num) {

		BigInteger bret = BigInteger.ZERO;
		BigInteger b10 = BigInteger.valueOf(10);
		while (num.compareTo(BigInteger.ZERO) > 0) {
			bret = bret.add(num.mod(b10));
			num = num.divide(b10);
		}
		return bret.intValue();
	}

	public static double factorial(int num) {

		if (num == 0 || num == 1)
			return 1;

		double ret = 1;
		for (int i = 2; i <= num; i++)
			ret *= i;

		return ret;
	}

	public static double catalanMult(int num) {
		if (num < 2)
			return 1;
		double cat = 1;
		for (int i = 2; i <= num; i++) {
			cat *= (double) (num + i) / (double) i;
		}

		return cat;
		// return (long) Math.ceil(cat);
	}

	public static double catalanFact(int num) {
		if (num < 2)
			return 1;
		double cat = 1;
		for (int i = 2; i <= num; i++) {
			cat = factorial(2 * num) / (factorial(num + 1) * factorial(num));
		}

		return cat;
	}

	public static boolean isCarmichael(long number) {
		if (number < 4)
			return false;

		boolean mayBeCarm = true;
		for (int j = 2; j < number; j++) {
			long m = MathUtil.modPow(j, number, number);
			if (m != j) {
				mayBeCarm = false;
				break;
			}
		}
		if (mayBeCarm) {
			if (uniquePrimeFactorsCount(number) > 1) {
				return true;
			}
		}
		return false;
	}

	// (N choose K+1) = (N choose K) * (N-K)/(K+1)
	public static double ncr_double(int n, int k) {
		if (k == 0)
			return 1;
		return ((n - k + 1) / (double) k) * ncr_double(n, k - 1);
	}

	public static BigInteger ncr_big(final int n, final int r) {
		BigInteger ret = BigInteger.ONE;
		for (int k = 0; k < r; k++) {
			ret = ret.multiply(BigInteger.valueOf(n - k)).divide(
					BigInteger.valueOf(k + 1));
		}
		return ret;
	}

	public static long gcd(long a, long b) {
		if (a % b == 0)
			return b;
		return gcd(b, a % b);
	}

	public static long lcm(long a, long b) {
		double term1 = a / (double) gcd(a, b);
		double term2 = term1 * b;

		long lcm = Math.round(term2);

		return lcm;
	}

	public static class Fibonacii {

		public int getSumUptoK(int k) {
			int num1 = 0;
			int num2 = 1;
			return getSumUptoK(k - 2, num1, num2);

		}

		private int getSumUptoK(int k, int num1, int num2) {
			System.out.println(" num1: " + num1 + " num2: " + num2 + " k: " + k
					+ " sum: " + (2 * num1 + num2) + " ssf: "
					+ (2 * num1 + num2));

			if (k <= 0) {
				return 2 * num1 + num2;
			} else {

				int temp = num2;
				num2 = num1 + num2;
				num1 = temp;

				// sum = sum + num2;
				return getSumUptoK(k - 1, num1, num2);
			}
		}

		public static double fib(int n) {
			double root5 = Math.pow(5, 0.5);
			double fibn = (1 / root5)
					* (Math.pow(((1 + root5) / (long) 2), n) - Math.pow(
							((1 - root5) / (long) 2), n));
			return fibn;
		}

	}

	// get nth fibonacii number

	public static List<Integer> printCarmichaelNumbers(long upto) {
		List<Integer> carmichael = new ArrayList<Integer>();
		for (int i = 3; i < upto; i++) {
			boolean mayBeCarm = true;
			// System.out.println("");
			for (int j = 2; j < i; j++) {
				long m = MathUtil.modPow(j, i, i);
				// System.out.print(" i: "+ i + " j: "+ j + " mod: "+m);
				if (m != j) {
					mayBeCarm = false;
					break;
				}
			}
			if (mayBeCarm) {
				if (uniquePrimeFactorsCount(i) > 1) {
					// System.out.println(uniquePrimeFactors(i));
					carmichael.add(i);
				}
			}
		}
		System.out.println("carmichael: " + carmichael);
		return carmichael;
	}

	public static int numZerosFact(int num) {
		int count = 0;
		if (num < 0) {
			System.out.println("Factorial is not defined for < 0");
			return 0;
		}
		for (int i = 5; num / i > 0; i *= 5) {
			count += num / i;
		}
		return count;
	}

	public static int checkMultipleOf7(int num) {

		if (num < 0) {
			return checkMultipleOf7(-1 * num);
		}

		if (num == 0 || num == 7) {
			return 1;
		} else if (num < 10) {
			return -1;
		}
		// System.out.println(" check for: "+ (num/10 - 2 * (num -
		// ((int)(num/10))*10)));
		return checkMultipleOf7(num / 10 - 2 * (num - ((int) (num / 10)) * 10));

	}

	static int add(int x, int y) {
		// Iterate till there is no carry
		while (y != 0) {
			// carry now contains common set bits of x and y
			int carry = x & y;

			// Sum of bits of x and y where at least one of the bits is not set
			x = x ^ y;

			// Carry is shifted by one so that adding it to x gives the required
			// sum
			y = carry << 1;
		}
		return x;
	}

	static int multiply(int x, int y) {

		if (x < 0 && y < 0) {
			return multiply(-1 * x, -1 * y);
		} else if (y < 0) {
			return -1 * multiply(x, -1 * y);
		} else if (x < 0) {
			return -1 * multiply(-1 * x, y);
		}

		int result = 0;

		while (y > 0) {
			if ((y & 1) > 0) {
				result = result + x;
			}
			x = x << 1;
			y = y >> 1;
		}

		return result;
	}

	public static void main(String[] args) {
		long a = (long) Math.abs((Math.random() * Integer.MAX_VALUE));
		int b = (int) Math.abs((Math.random() * Integer.MAX_VALUE));
		long n = 122;

		BigDecimal ba = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		BigDecimal bn = new BigDecimal(n);

		// BigDecimal rem = (ba.pow(b)).remainder(bn);
		// System.out.println(rem);
		// System.out.println("---");
		System.out.println("a: " + a + " b:" + b);
		long mod = modPow(a, b, n);
		System.out.println(mod);

		System.out.println(primeFactors(12));
		System.out.println(uniquePrimeFactors(12));
		System.out.println(largestPrimeFactor(13195));
		System.out.println(largestPrimeFactor(600851475143L));

		// printCarmichaelNumbers(10000);

		System.out.println(isCarmichael(561));
		System.out.println(isCarmichael(562));
		//
		// for (int i = 0; i < 100; i++) {
		// double cat1 = catalanMult(i);
		// double cat2 = catalanFact(i);
		// System.out.println("cat: "+Math.round(cat1));
		// //System.out.println(cat1 == cat2);
		// }

		// for (int i = 0; i < 100; i++) {
		// double fac = fact(i);
		// System.out.println("i: "+ i + " fac: " + fac);
		// }
		System.out.println(ncr_big(52, 2));
		System.out.println(Math.round(ncr_double(52, 2)));
		// System.out.println("fibonacii");
		// for (int i = 1; i < 100; i++)
		// {
		// double f =Math.round(fib(i));
		// System.out.println(" i:"+i+" : "+f);
		// }

		// System.out.println(lcm(700000, 500000));

		// for (int i = 1; i < 100; i++)
		// {
		// if (isPrime(i)) {
		// System.out.println("prime: "+i);
		// }
		// //System.out.println(" i: "+ i +": "+isPrime(i));
		// }

		// for (int i = 1; i < 1000; i++)
		// {
		// System.out.println(i+" : "+String.valueOf(factorial(i)));
		// //System.out.println(" i: "+ i +": "+isPrime(i));
		// }

		// for (int i = 1; i < 1000; i++)
		// {
		// System.out.println(i+" : " +digitSum(i));
		// }
		// bigFactorial
		long time1 = System.nanoTime();
		for (int i = 1000; i < 1001; i++) {
			BigInteger bf = bigFactorial(i);
			int digsum = MathUtil.bigDigitSum(bf);
			System.out.println(i + " : " + bf + " digit sum: " + digsum);
			System.out.println(" trailing zeros: "
					+ MathUtil.bigTrailingZero(bf));
			// System.out.println(" i: "+ i +": "+isPrime(i));
		}
		long time2 = System.nanoTime();
		System.out.println("time:(ms) " + (time2 - time1) / 1000l);
		System.out.println("time:(s) " + (time2 - time1) / 1000000000l);

		BigInteger b1 = new BigInteger("100001", 2);
		System.out.println(b1);
		System.out.println(b1.bitCount());
		System.out.println(b1.bitLength());
		System.out.println(bitCount(33));
		System.out.println(bitLength(33));

		BigInteger b2 = new BigInteger("10001001", 2);
		System.out.println(isKSparse(b2.longValue(), 3));

	}
}
