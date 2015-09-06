package ch.arkeine.function;

public class Sinus implements Function{

	@Override
	public double getYValue(double xValue) {
		return Math.sin(xValue);
	}

}
