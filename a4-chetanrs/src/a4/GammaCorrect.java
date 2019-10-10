package a4;

public class GammaCorrect implements PixelTransformation {

	private double gamma;
	
	public GammaCorrect (double gamma) {
		this.gamma = gamma;
	}
	
	public Pixel transform(Pixel p) {
		
		if (p == null) {
			throw new IllegalArgumentException("Pixel cannopt be null.");
		}
		
		double newRed = Math.pow(p.getRed(), (1.0/gamma));
		double newGreen = Math.pow(p.getGreen(), (1.0/gamma));
		double newBlue = Math.pow(p.getBlue(), (1.0/gamma));
		
		return new ColorPixel(newRed, newGreen, newBlue);
	}

}
