package com.spikes2212.robot2016.commands.folder;

import static com.spikes2212.robot2016.Robot.folder;

import com.spikes2212.robot2016.pid.PIDCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDMoveFolder extends PIDCommand {

	private static final double KD = 0.01;
	private static final double KI = 0;
	private static final double KP = 0;
	private static final double TOLERANCE = 1;

	private double angle;

	public PIDMoveFolder(double angle) {
		requires(folder);
		this.angle = angle;
		SmartDashboard.putNumber("PIDMoveFolder.KP", KP);
		SmartDashboard.putNumber("PIDMoveFolder.KI", KI);
		SmartDashboard.putNumber("PIDMoveFolder.KD", KD);
		SmartDashboard.putNumber("PIDMoveFolder.TOLERANCE", TOLERANCE);
		SmartDashboard.putNumber("PIDMoveFolder.ANGLE", angle);
	}

	@Override
	protected void initialize() {
		folder.calibrate();
		getCalculator().setPID(SmartDashboard.getNumber("PIDMoveFolder.KP", KP),
				SmartDashboard.getNumber("PIDMoveFolder.KI", KI), SmartDashboard.getNumber("PIDMoveFolder.KD", KD));
		getCalculator().setSetpoint(SmartDashboard.getNumber("PIDMoveFolder.ANGLE", angle));
		getCalculator().setTolerance(SmartDashboard.getNumber("PIDMoveFolder.TOLERANCE", TOLERANCE));
	}

	@Override
	protected void end() {
		folder.stop();
	}

	@Override
	public double getPIDInput() {
		return folder.getAngle();
	}

	@Override
	public void usePIDOutput(double output) {
		folder.tryMove(output);
	}

}
