package com.spikes2212.robot2016.util;

public class Util {

	public static double limit(double value, double min, double max) {
		return Math.min(max, Math.max(min, value));
	}

	public static double limitAbs(double value, double abs) {
		return limit(value, -Math.abs(abs), Math.abs(abs));
	}

}
