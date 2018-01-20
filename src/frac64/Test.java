package frac64;

public class Test {

	public static void main(String[] args) {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				System.out.print(String.format("%3s", z(x, y)));
			}
			System.out.println();
		}
	}

	private static int z(int x, int y) {
		int low_mask  = 0b01010101_01010101_01010101_01010101;
		int high_mask = 0b1010101_01010101_01010101_010101010;
		int low_x_high_y = (x & low_mask) | (y & high_mask);
		int high_x_low_y = (x & high_mask) | (y & low_mask);
		int z = (low_x_high_y & high_x_low_y) | (low_x_high_y + high_x_low_y);
		return z;
	}
}
