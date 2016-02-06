package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.cameras;
import static com.spikes2212.robot2016.Robot.drivetrain;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.pid.PIDCalculator;
import com.spikes2212.robot2016.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class VisionTuneToDistance extends Command {

	private static final double P = 1;
	private static final double I = 0;
	private static final double D = 0;
	private static final double TOLERANCE = 0.05; // meter

	private PIDCalculator calculator;
	private Image image;
	private double wantedDistance;

	public VisionTuneToDistance(double wantedDistance) {
		requires(drivetrain);
		requires(cameras);
		this.wantedDistance = wantedDistance;
		calculator = new PIDCalculator(P, I, D);
		calculator.setTolerance(TOLERANCE);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	}

	@Override
	protected void initialize() {
		cameras.getImage(image);
		drivetrain.resetEncoders();
		calculator.setSetpoint(Vision.getDistanceFrom(image).orElse(0d) - wantedDistance);
	}

	@Override
	protected void execute() {
		drivetrain.forward(calculator.calculate((drivetrain.getLeftDistance() + drivetrain.getRightDistance()) / 2));

	}

	@Override
	protected boolean isFinished() {
		return calculator.hasReached();
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
