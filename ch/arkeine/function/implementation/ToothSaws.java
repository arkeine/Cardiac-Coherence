package ch.arkeine.function.implementation;

import static java.lang.Math.sin;

import ch.arkeine.function.Function;

import static java.lang.Math.pow;
import static java.lang.Math.PI;;

public class ToothSaws implements Function {

	@Override
	public double getYValue(double xValue) {
		return 2/PI * Math.asin(sin(PI * xValue));
	}

	@Override
	public String getName() {
		return "Triangle";
	}
}
