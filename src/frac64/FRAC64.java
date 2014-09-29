package frac64;

import static java.lang.Math.abs;

public final class FRAC64 {

	static long frac(int integer) {
		return integer << 32 | 1;		
	}
	
	static long frac(int numerator, int denominator) {
		if (denominator < 0)
			numerator = -numerator;
		return ((long)numerator << 32) | abs(denominator);
	}
	
	static void show(long v) {
		System.out.println(String.format("%64s", Long.toBinaryString(v)));
	}
	
	static boolean isNaN(long frac64) {
		return denominator(frac64) == 0;
	}
	
	static long add(long left, long right) {
		int l_d = denominator(left);
		int r_d = denominator(right);
		if (l_d == r_d) // fast path for equal d's
			return cancel( left + right - l_d ); // one of the equal d's has been added to much, subtract it again
		long l_n = (left >> 32) * r_d;
		long r_n = (right >> 32) * l_d;
		long d = (long)r_d * l_d;
		// overflow checks
		return cancel( ((l_n+r_n) << 32) + (int)d);
	}
	
	static long sub(long left, long right) {
		return add(left, neg(right));
	}
	
	static long neg(long frac64) {
		return (-(frac64 >> 32) << 32) | (int)frac64;
	}
	
	static long mul(long left, long right) {
		long n = ((left >> 32) * (right >> 32)) << 32;
		long d = (int)left * (int)right;
		return cancel(n | d);
	}
	
	static long div(long left, long right) {
		long n = ((left >> 32) * (int)right) << 32;
		long d = (int)left * (right >> 32);
		if (d < 0) {
			d = -d;
			n = -n;
		}
		return cancel(n | d);
	}
	
	static long reciprocal(long frac64) {
		if (frac64 < 0)
			return abs(frac64) >> 32 | (((long)-((int)frac64)) << 32);
		return frac64 >> 32 | (frac64 << 32);
	}

	
	private static long cancel(long frac64) {
		int n = numerator(frac64);
		if (n == 1 || n == -1)
			return frac64;
		int d = denominator(frac64); 
		int gcd = gcd(abs(n), d);
		return frac(n / gcd, d / gcd); //TODO not use frac that allows/checks for negative d
	}
	
	//TODO cancel for n + d args 
	
	private static int gcd(int a, int b) {
		while (b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	static int numerator(long frac64) {
		return (int) (frac64 >> 32);
	}
	
	static int denominator(long frac64) {
		return (int)frac64;
	}
	
	static String print(long frac64) {
		int d = denominator(frac64);
		if (d <= 0)
			return "NaN";
		int n = numerator(frac64);
		if (n == 0)
			return "0";
		if (abs(n) == d)
			return Integer.signum(n)+"";
		if (d == 1)
			return String.valueOf(n);
		return n+"/"+d;
	}
}
