package a4;

public class MonochromePicture implements Picture {

	private int width;
	private int height;
	private Pixel value;

	public MonochromePicture(int width, int height, Pixel value) {
		if (width <= 0 || height <= 0 || value == null) {
			throw new IllegalArgumentException("Invalid or null arguments.");
		}
		
		this.width = width;
		this.height = height;
		this.value = value;
		
		Pixel[][] picture = new Pixel[width][height];
		
		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < height - 1; j++) {
				picture[i][j] = value;
			}
		}
	}

	public int getWidth() {
		if (width <= 0 ) {
			throw new IllegalArgumentException("Width must be greater than 0.");
		}
		
		return width;
	}

	public int getHeight() {
		if (height <= 0) {
			throw new IllegalArgumentException("Height must be greater than 0.");
		}
		
		return height;
	}

	public Pixel getPixel(int x, int y) {
		if (value == null || x < 0 || x > getWidth()-1 || y < 0 || y > getHeight()-1) {

			throw new IllegalArgumentException("pixel is null or out of bounds");
		}
		
		return value;
	}

	public Picture paint(int x, int y, Pixel p) {
		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(x, y, p);
		
		return output;
	}

	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(x, y, p, factor);
		
		return output;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || p == null) {
			throw new IllegalArgumentException("Invalid or empty arguments.");
		}

		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(ax, ay, bx, by, p, 1.0);
		
		return output;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || p == null) {
			throw new IllegalArgumentException("Invalid or empty arguments.");
		}

		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(ax, ay, bx, by, p, factor);
		
		return output;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0 || p == null) {
			throw new IllegalArgumentException("Invalid or empty arguments.");
		}
		
		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(cx, cy, radius, p, 1.0);
		
		return output;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null || factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Invalid or empty arguments.");
		}
		
		Picture output = new MutablePixelArrayPicture(width, height, value);
		output.paint(cx, cy, radius, p, factor);
		
		return output;
	}
}