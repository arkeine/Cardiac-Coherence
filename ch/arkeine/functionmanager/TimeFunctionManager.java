package ch.arkeine.functionmanager;

import ch.arkeine.function.Function;

/**
 * Use the time in milli-second from the instantiation
 * as x parameter of the function
 */
public class TimeFunctionManager {

	/* ============================================ */
	// CONSTRUCTOR
	/* ============================================ */

	public TimeFunctionManager(Function function) {
		this.function = function;
		this.InitializationTime = System.currentTimeMillis();
	}

	/* ============================================ */
	// ASSESSOR / MUTATOR
	/* ============================================ */

	public double getYValue() {
		long currentTime = System.currentTimeMillis();
		long diffTime = currentTime - InitializationTime;

		return function.getYValue(diffTime);
	}

	/* ============================================ */
	// FIELD
	/* ============================================ */

	// Input
	private Function function;

	// Tool
	private long InitializationTime;
}
