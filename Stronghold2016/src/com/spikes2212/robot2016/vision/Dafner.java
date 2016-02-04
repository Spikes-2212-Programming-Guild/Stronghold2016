package com.spikes2212.robot2016.vision;

public class Dafner {
	
	public enum Orientation {
		VERTICAL, HORIZONTAL
	}

	private Orientation orientation;
	private double left;
	private double right;
	private double top;
	private double bottom;
	private double area;
	
	public Dafner(Orientation orientation, double left, double right, double top, double bottom, double area) {
		this.orientation = orientation;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.area = area;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public double getLeft() {
		return left;
	}
	
	public double getRight() {
		return right;
	}
	
	public double getTop() {
		return top;
	}
	
	public double getBottom() {
		return bottom;
	}
	
	public double getWidth() {
		return right - left;
	}
	
	public double getHeight() {
		return bottom - top;
	}
	
	public double getArea() {
		return area;
	}
	

}
