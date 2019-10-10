package a4;

public class GradientPicture implements Picture {

	private int width;
	private int height;
	private Pixel upper_left;
	private Pixel upper_right;
	private Pixel lower_left;
	private Pixel lower_right;

	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width <= 0 || height <= 0 || upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
			throw new IllegalArgumentException("Arguments are invalid or null.");
		}

		this.width = width;
		this.height = height;
		this.upper_left = upper_left;
		this.upper_right = upper_right;
		this.lower_left = lower_left;
		this.lower_right = lower_right;

	}

	public int getWidth() {

		return width;
	}


	public int getHeight() {

		return height;
	}


	public Pixel getPixel(int x, int y) {

		Pixel[][] picture = new Pixel[getWidth()][getHeight()];

		picture[0][0] = upper_left;
		picture[0][height-1] = lower_left;
		picture[width-1][0] = upper_right;
		picture[width-1][height-1] = lower_right;

		for (int i = 0; i < height; i++) {
			double j = i;
			double k = (j/(height-1));
			picture[0][i] = upper_left.blend(lower_left, k);
			picture[width-1][i] = upper_right.blend(lower_right,  k);
		}

		for (int i = 0; i < height; i++) {
			for (int j = 1; j < width; j++) {
				double l = j;
				double n = (l/(width-1));

				picture[j][i] = picture[0][i].blend(picture[width-1][i], n);
			}
		}

		return picture[x][y];
	}


	public Picture paint(int x, int y, Pixel p, double factor) {
		
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || p == null || factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}
		
		Pixel[][] picture = new Pixel[width][height];
		
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				picture[i][j] = getPixel(i, j);
			}
		}
		
		picture[x][y] = picture[x][y].blend(p, factor);
		Picture output = new MutablePixelArrayPicture(picture);

		return output;
	}

}
