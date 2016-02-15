package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.cameras;
import static com.spikes2212.robot2016.Robot.drivetrain;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.pid.DoublePIDCommand;

public class VisionTuneToDistance extends DoublePIDCommand {

	private static final double KP = 1.0;
	private static final double KI = 0;
	private static final double KD = 9.0;
	private static final double TOLERANCE = 0.05; // meter

	private double wantedDistance;
	private Image image;
	private Image binary;

	public VisionTuneToDistance(double wantedDistance) {
		requires(drivetrain);
		requires(cameras);
		this.wantedDistance = wantedDistance;
		getCalculator1().setPID(KP, KI, KD);
		getCalculator1().setTolerance(TOLERANCE);
		getCalculator2().setPID(KP, KI, KD);
		getCalculator2().setTolerance(TOLERANCE);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binary = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
	}

	@Override
	protected void initialize() {
		drivetrain.resetEncoders();
		cameras.getImage(image);
		getCalculator1()
				.setSetpoint(cameras.getDistanceFromTower(image, binary).orElse(wantedDistance) - wantedDistance);
		getCalculator2()
				.setSetpoint(cameras.getDistanceFromTower(image, binary).orElse(wantedDistance) - wantedDistance);
	}

	@Override
	public double getPIDInput1() {
		return drivetrain.getLeftDistance();
	}

	@Override
	public double getPIDInput2() {
		return drivetrain.getRightDistance();
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	public void usePIDOutput(double output1, double output2) {
		drivetrain.setTwoSides(output1 / Constants.MAX_LEFT_VELOCITY, output2 / Constants.MAX_RIGHT_VELOCITY);
	}

}
