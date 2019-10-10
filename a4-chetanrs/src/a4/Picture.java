package a4;

public interface Picture {

	// Getters for the dimensions of a picture.
	// Width refers to the number of columns and
	// height is the number of rows.

	public int getWidth();
	public int getHeight();

	// getPixel(x, y) retrieves the pixel at position (x,y) in the
	// picture. The coordinate (0,0) is the upper left
	// corner of the picture. The coordinate (getWidth()-1, getHeight()-1)
	// is the lower right of the picture. An IllegalArgumentException
	// is thrown if x or y are not in range.

	public Pixel getPixel(int x, int y);

	// The various forms of the paint() method return a new
	// picture object based on this picture with certain pixel
	// positions "painted" with a new value.

	// paint(int x, int y, Pixel p) paints the pixel at
	// position (x,y) with the pixel value p. The second
	// form includes a factor parameter that specifies a
	// blending factor. A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.

	public Picture paint(int x, int y, Pixel p, double factor);

	default Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || p == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		return paint(x, y, p, 1.0);
	}

	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value. The second form
	// should blend with the specified factor as previously described.

	default Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if ( ax < 0 || ay < 0 || bx < 0 || by < 0 || p == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		int startX = 0;
		int endX = 0;
		int endY = 0;
		int startY = 0;

		if (ax < bx) {
			startX = ax;
			endX = bx;
		} else {
			startX = bx;
			endX = ax;
		}
		
		if (ay < by) {
			startY = ay;
			endY = by;
		} else {
			startY = by;
			endY = ay;
		}

		Picture picture = this;
		
		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				picture = picture.paint(i, j, p, factor);
			}

		}
		
		return picture;

	}

	default Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || p == null) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}
		return paint(ax, ay, bx, by, p, 1.0);
	}
	
	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p. Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame).

	// Calculate the distance of a particular (x,y) position to (cx,cy) with
	// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))

	// The second form with factor, blends as previously described.

	default Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null || factor == 0) {
			throw new IllegalArgumentException("Arguments are out of range or null.");
		}

		Picture output = this;
		
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j <= getHeight(); j++) {
				if (Math.sqrt((i - cx) * (i - cx) + (j - cy) * (j - cy)) <= radius) {
					output = output.paint(i, j, p, factor);
				}
			}
		}
		
		return output;
	}
	
	default Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}
}