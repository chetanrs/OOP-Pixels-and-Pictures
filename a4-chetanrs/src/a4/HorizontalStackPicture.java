package a4;

public class HorizontalStackPicture implements Picture {
	
	private Picture left;
	private Picture right;
	
	public HorizontalStackPicture(Picture left, Picture right) {
		
		if (left == null || right== null || left.getHeight() < 0 || left.getWidth() < 0 || right.getHeight() < 0 || right.getWidth() < 0 || left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("Input pictures cannot be null and dimensions must match.");
		}

		this.left = left;
		this.right = right;

	}

	public int getWidth() {
		
		return left.getWidth() + right.getWidth();
	}

	public int getHeight() {
		return right.getHeight();
	}

	public Pixel getPixel(int x, int y) {
		
		Pixel[][] picture = new Pixel[this.getWidth()][this.getHeight()];
		
		for (int i = 0; i< picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				if (i < left.getWidth() ) {
					picture[i][j] = left.getPixel(i, j);
				} else {
					picture[i][j] = right.getPixel(i - right.getWidth(), j);
				}
			}
		}
		
		return picture[x][y];
	}



	public Picture paint(int x, int y, Pixel p, double factor) {
		
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || p == null || factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		if (x < left.getWidth()) {
			left = left.paint(x, y, p, factor);
		} else {
			right = right.paint(x - right.getWidth(), y, p, factor);
		}
		
		return this;
	}

	public Picture paint(int x, int y, Pixel p) {

		return paint(x, y, p, 1.0);
	}
}