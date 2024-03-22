package calculations;

public class Calcs {
	public static double kmhToMph(double kmh) {
        // Conversion formula: mp/h = km/h / 1.60934
        return kmh / 1.60934;
    }
	public static double mphToKmh(double mph) {
        // Conversion formula: km/h = mp/h * 1.60934
        return mph * 1.60934;
    }
	public static double cToF(double C) {
        // Conversion formula: F = (C * 9/5)+32
        return (C * 9/5)+32;
    }
	public static double fToC(double F) {
        // Conversion formula: C = (F - 32) * 5/9
        return (F - 32) * 5/9;
    }
	public static double gToOz(double g) {
		// Conversion formula: g = oz / 28.35
		return g/28.35;
	}
	public static double ozToG(double oz) {
		// Conversion formula: oz = g * 28.35
		return oz*28.35;
	}
	public static double mlToFloz(double ml) {
		// Conversion formula: fl. oz = ml / 29.574
		return ml/29.574;
	}
	public static double mlToGal(double ml) {
		// Conversion formula: gal = ml / 3785
		return ml/3785;
	}
	public static double flozToMl(double floz) {
		// Conversion formula: ml = fl. oz * 29.574
		return floz * 29.574;
	}
	public static double galToMl(double gal) {
		// Conversion formula: ml = gal * 3785
		return gal*3785;
	}
}
