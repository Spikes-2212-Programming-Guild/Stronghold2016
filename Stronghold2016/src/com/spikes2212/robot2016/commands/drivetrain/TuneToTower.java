package com.spikes2212.robot2016.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import static com.spikes2212.robot2016.Robot.drivetrain;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.spikes2212.robot2016.pid.PIDCalculator;

import static com.spikes2212.robot2016.Robot.cameras;

/**
 *
 */
public class TuneToTower extends Command {
	private boolean tower;
	private static final double P = 1;
	private static final double I = 0;
	private static final double D = 0;
	private static final double TOLERANCE = 0.05; // meter

	private PIDCalculator calculator;
	private Image image;

	public TuneToTower() {
		requires(drivetrain);
		requires(cameras);
		calculator = new PIDCalculator(P, I, D);
		calculator.setTolerance(TOLERANCE);
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		tower = cameras.getAngleUp() != -1;
		cameras.getImage(image);
		calculator.setSetpoint(cameras.getAngleHorizontal());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (tower)
			drivetrain.turn(
					calculator.calculate(Math.abs(drivetrain.getLeftDistance() - drivetrain.getRightDistance() / 2)));
		else
			end();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return calculator.hasReached();
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
