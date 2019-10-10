package a4;

public class VerticalStackPicture implements Picture {

	private Picture top;
	private Picture bottom;

	public VerticalStackPicture(Picture top, Picture bottom) {
		
		if (top == null || bottom == null || top.getHeight() < 0 || top.getWidth() < 0 || bottom.getHeight() < 0 || bottom.getWidth() < 0 || top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("Input pictures must not be null and dimesnions must match.");
		}

		this.top = top;
		this.bottom = bottom;
	}


	
	public int getWidth() {
		return top.getWidth();
	}

	
	public int getHeight() {
		return top.getHeight() + bottom.getHeight();
	}

	
	public Pixel getPixel(int x, int y) {
		Pixel[][] picture = new Pixel[this.getWidth()][this.getHeight()];
		
		for (int i = 0; i< picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				if (j < top.getHeight() ) {
					picture[i][j] = top.getPixel(i, j);
				} else {
					picture[i][j] = bottom.getPixel(i, j - top.getHeight());
				}
			}
		}
		
		return picture[x][y];
		
	}

	
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || p == null || factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		if (y < top.getHeight()) {
			top = top.paint(x, y, p, factor);
		} else {
			bottom = bottom.paint(x, y - bottom.getHeight(), p, factor);
		}
		
		return this;
	}

	
	public Picture paint(int x, int y, Pixel p) {
		
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || p == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}
		
		return paint(x, y, p, 1.0);

	}

}