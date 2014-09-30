package frac64;

import static frac64.FRAC64.add;
import static frac64.FRAC64.div;
import static frac64.FRAC64.frac;
import static frac64.FRAC64.mul;
import static frac64.FRAC64.print;
import static frac64.FRAC64.sub;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFRAC64 {

	@Test
	public void fractionFromNumeratorAndDenominator() {
		assertFraction("1/2", frac(1, 2));
		assertFraction("3/4", frac(3, 4));
		assertFraction("1", frac(3, 3));
		assertFraction("-1", frac(3, -3));
		assertFraction("-1", frac(-3, 3));
	}
	
	@Test
	public void addition() {
		assertFraction("137/72", add(frac(17, 18), frac(23, 24)));
		assertFraction("3/2", add(frac(1, 2), frac(1, 1)));
		assertFraction("1", add(frac(3, 4), frac(1, 4)));
		assertFraction("5/8", add(frac(3, 8), frac(2, 8)));
		assertFraction("3/4", add(frac(1, 2), frac(1, 4)));
		assertFraction("17/35", add(frac(1, 5), frac(2, 7)));
		assertFraction("-3/35", add(frac(1, 5), frac(-2, 7)));
		assertFraction("1/5", add(frac(-2, 5), frac(3, 5)));
		assertFraction("-1/5", add(frac(2, 5), frac(3, -5)));
		assertFraction("2/3571", add(frac(1, 3571), frac(1, 3571)));
	}
	
	@Test
	public void subtraction() {
		assertFraction("-1/2", sub(frac(1, 2), frac(1, 1)));
		assertFraction("1/2", sub(frac(3, 4), frac(1, 4)));
		assertFraction("1/4", sub(frac(1, 2), frac(1, 4)));
		assertFraction("-3/35", sub(frac(1, 5), frac(2, 7)));
		assertFraction("17/35", sub(frac(1, 5), frac(-2, 7)));
		assertFraction("-1", sub(frac(-2, 5), frac(3, 5)));
		assertFraction("1", sub(frac(2, 5), frac(3, -5)));
		assertFraction("1/8", sub(frac(3, 8), frac(2, 8)));
		assertFraction("-1/72", sub(frac(17, 18), frac(23, 24)));
	}
	
	@Test
	public void multiplication() {
		assertFraction("-1/16", mul(frac(3, 6), frac(-1, 8)));
		assertFraction("1/8", mul(frac(1, 2), frac(1, 4)));
		assertFraction("1/16", mul(frac(3, 6), frac(1, 8)));
	}
	
	@Test
	public void division() {
		assertFraction("-4", div(frac(3, 6), frac(-1, 8)));
		assertFraction("2", div(frac(1, 2), frac(1, 4)));
		assertFraction("4", div(frac(3, 6), frac(1, 8)));
		assertFraction("8/5", div(frac(2, 5), frac(1, 4)));
		assertFraction("-8/5", div(frac(2, 5), frac(1, -4)));
		assertFraction("8/5", div(frac(-2, 5), frac(1, -4)));
		assertFraction("-8/5", div(frac(-2, 5), frac(1, 4)));
	}
	
	private static void assertFraction(String expected, long actual) {
		assertEquals(expected, print(actual));
	}
}
