package com.spikes2212.robot2016.commands.triz;

import static com.spikes2212.robot2016.Robot.triz;

import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.pid.PIDCommand;

/**
 *
 */
public class MoveTrizToPosition extends PIDCommand {
	private final static double KP=0;
	private final static double KI=0;
	private final static double KD=0;
	private static final double ABSOLUTE_TOLERANCE = 0;
	private final double startDistance;
	private double maximumOutput;
    public MoveTrizToPosition(double distance) {
    	super(KP, KI, KD, distance, ABSOLUTE_TOLERANCE);
    	startDistance=distance;
    	requires(triz);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public double getPIDInput() {
		return Robot.triz.getPosition()-startDistance;
	}

	@Override
	public void usePIDOutput(double output) {
		if(output!=0){
			maximumOutput= Math.max(maximumOutput, output);
			output/=maximumOutput;
		}
		if (!(output > 0 && triz.isUp() || output < 0 && triz.isDown())) {
			triz.move(output);
		}
	}
}
