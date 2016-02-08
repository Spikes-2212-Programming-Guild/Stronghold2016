package com.spikes2212.robot2016.pid;

import edu.wpi.first.wpilibj.command.Command;

public abstract class DoublePIDCommand extends Command {

	private PIDCalculator calculator1, calculator2;

	public DoublePIDCommand(double kp1, double ki1, double kd1, double setpoint1, double tolerance1, double kp2,
			double ki2, double kd2, double setpoint2, double tolerance2) {
		calculator1 = new PIDCalculator(kp1, ki1, kd1);
		calculator1.setSetpoint(setpoint1);
		calculator1.setTolerance(tolerance1);
		calculator2 = new PIDCalculator(kp2, ki2, kd2);
		calculator2.setSetpoint(setpoint2);
		calculator2.setTolerance(tolerance2);
	}

	public abstract double getPIDInput1();

	public abstract double getPIDInput2();

	public abstract void usePIDOutput(double output1, double output2);

	@Override
	protected void execute() {
		usePIDOutput(calculator1.calculate(getPIDInput1()), calculator2.calculate(getPIDInput2()));
	}

	@Override
	protected boolean isFinished() {
		return calculator1.hasReached() && calculator2.hasReached();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
