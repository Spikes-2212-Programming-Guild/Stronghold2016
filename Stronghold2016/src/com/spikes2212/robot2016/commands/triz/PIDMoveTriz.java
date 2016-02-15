package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.pid.PIDCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDMoveTriz extends PIDCommand {

	private static final double KD = 0.01;
	private static final double KI = 0;
	private static final double KP = 0;
	private static final double TOLERANCE = 0.01; // meter

	private double angle;

	public PIDMoveTriz(double angle) {
		requires(triz);
		this.angle = angle;
		SmartDashboard.putNumber("PIDMoveTriz.KP", KP);
		SmartDashboard.putNumber("PIDMoveTriz.KI", KI);
		SmartDashboard.putNumber("PIDMoveTriz.KD", KD);
		SmartDashboard.putNumber("PIDMoveTriz.TOLERANCE", TOLERANCE);
	}

	@Override
	protected void initialize() {
		triz.calibrate();
		getCalculator().setPID(SmartDashboard.getNumber("PIDMoveTriz.KP", KP),
				SmartDashboard.getNumber("PIDMoveTriz.KI", KI), SmartDashboard.getNumber("PIDMoveTriz.KD", KD));
		getCalculator().setSetpoint(angle);
		getCalculator().setTolerance(SmartDashboard.getNumber("PIDMoveTriz.TOLERANCE", TOLERANCE));
	}

	@Override
	protected void end() {
		triz.stop();
	}

	@Override
	public double getPIDInput() {
		return triz.getAngle();
	}

	@Override
	public void usePIDOutput(double output) {
		triz.tryMove(output);
	}

}
