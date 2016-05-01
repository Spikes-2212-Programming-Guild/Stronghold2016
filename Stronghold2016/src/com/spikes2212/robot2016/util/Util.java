package com.spikes2212.robot2016.util;

public class Util {

	public static double limit(double value, double min, double max) {
		return Math.min(max, Math.max(min, value));
	}

	public static double limitAbs(double value, double abs) {
		return limit(value, -Math.abs(abs), Math.abs(abs));
	}

	public static double roundTo(double num, int digits) {
		return ((int) (num * Math.pow(10, digits))) / Math.pow(10, digits);

	}

}
