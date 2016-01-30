package com.spikes2212.robot2016.commands.drivetrain;

import static com.spikes2212.robot2016.Robot.drivetrain;

import com.spikes2212.robot2016.pid.PIDCalculator;
import com.spikes2212.robot2016.pid.PIDCalculator.AbsoluteTolerance;

import edu.wpi.first.wpilibj.command.Command;;

public class PIDStraightDriveByDistanceWithoutDeviation extends Command {

	private static final double STRAIGHT_KP = 1;
	private static final double STRAIGHT_KI = 0;
	private static final double STRAIGHT_KD = 0;
	private static final double TURN_KP = 1;
	private static final double TURN_KI = 0;
	private static final double TURN_KD = 0;
	private static final double ABSOLUTE_TOLERANCE = 1; // centimeter

	private PIDCalculator pidStraight;
	private PIDCalculator pidTurn;

	private double maximumStraight;

	public PIDStraightDriveByDistanceWithoutDeviation(double distance) {
		requires(drivetrain);
		pidStraight = new PIDCalculator(STRAIGHT_KP, STRAIGHT_KI, STRAIGHT_KD);
		pidStraight.setTolerance(new AbsoluteTolerance(ABSOLUTE_TOLERANCE));
		pidStraight.setSetpoint(distance);
		pidTurn = new PIDCalculator(TURN_KP, TURN_KI, TURN_KD);
		pidTurn.setSetpoint(0);
		maximumStraight = STRAIGHT_KP * distance;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double straight = pidStraight.calculate(0.5 * (drivetrain.getLeftDistance() + drivetrain.getRightDistance()));
		maximumStraight = Math.max(maximumStraight, Math.abs(straight));
		double turn = pidTurn.calculate(drivetrain.getYawRate());
		drivetrain.arcade(straight / maximumStraight, turn);
	}

	@Override
	protected boolean isFinished() {
		return pidStraight.hasReached();
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
