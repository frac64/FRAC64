package frac64;

public class NONE {

	public static void main(String[] args) {
		for (int i = 0; i < (1 << 16); i+=128) {
			if (i % (128 * 32) == 0) {
				System.out.println();
			}
			if (getBit(i, 7) == 1 || i > 255 && getBit(i, 15) == 0) {
				System.out.print("<u></u>");
			} else {
				System.out.print("<i></i>");
			}
		}
	}
	
	static int getBit(int n, int k) {
	    return (n >> k) & 1;
	}
}
