package ch.arkeine.function.implementation;

import ch.arkeine.function.Function;

public class Sinus implements Function{

	@Override
	public double getYValue(double xValue) {
		return Math.sin(xValue / 1000);
	}

	@Override
	public String getName() {
		return "Sin";
	}

}
