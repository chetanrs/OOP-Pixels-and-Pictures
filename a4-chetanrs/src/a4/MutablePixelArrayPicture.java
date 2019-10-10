package a4;

public class MutablePixelArrayPicture implements Picture {

	private Pixel[][] picture;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		
		if (pixel_array == null) {
			throw new IllegalArgumentException("Pixel array cannot be empty.");
		}
		
		int width = pixel_array.length;
		int height = pixel_array[0].length;

		picture = new Pixel[width][height];

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Width and height must be greater than 0.");
		}

		for (int i = 0; i < width; i++) {
			
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("Subarrays cannot be null.");
			}

			for (int j = 0; j < height; j++) {
				
				if (pixel_array[i][j] == null || pixel_array[i].length != height) {
					throw new IllegalArgumentException("Array elements cannot be null.");
				}
				
				picture[i][j] = pixel_array[i][j];
			}
		}
	}

	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width == 0 || height == 0 || initial_value == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}
		
		picture = new Pixel[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				picture[i][j] = initial_value;
			}
		}
	}

	public MutablePixelArrayPicture(int width, int height) {
		this(width, height, new GrayPixel(0.5));

	}

	
	public int getWidth() {
		if (picture.length <= 0) {
			throw new IllegalArgumentException("Width must be positive.");
		}
		
		return picture.length;
	}

	
	public int getHeight() {
		if (picture[0].length <= 0) {
			throw new IllegalArgumentException("Height cannot be negative.");
		}
		
		return picture[0].length;
	}


	public Pixel getPixel(int x, int y) {
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}
			
		return picture[x][y]; 
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || p == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		picture[x][y] = picture[x][y].blend(p, factor);
		
		return this;
	}


}