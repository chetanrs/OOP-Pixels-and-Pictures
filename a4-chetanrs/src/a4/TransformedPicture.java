package a4;

public class TransformedPicture implements Picture {
	
	private Picture source;
	private PixelTransformation xform;
	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		if (source == null || xform == null) {
			throw new IllegalArgumentException("Arguments cannot be null.");
		}
		
		this.source = source;
		this.xform= xform;
	}
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return source.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return source.getHeight();
	}

	
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x > getWidth() - 1 || y > getHeight() - 1) {
			throw new IllegalArgumentException("Arguments are out of bounds.");
		}
		
		Picture output = source;
		
		return xform.transform(output.getPixel(x, y));
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return null;
	}

}
