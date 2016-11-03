package com.spikes2212.robot2016.commands.drivetrain;

import java.util.Arrays;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.util.FeedForward;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class FeedForwardStraight extends Command {
	private FeedForward feedForward;
	private final double time;
//	private final double TOLERANCE=0.2;
	private double distance;
	
    public FeedForwardStraight(double distance) {
        requires(Robot.drivetrain);
        this.distance=distance;
        this.feedForward=new FeedForward(Constants.KP, Constants.MAX_LEFT_VELOCITY, Constants.MAX_ACCELERATION, Constants.MAX_DECCELERATION, this.distance);
        time=System.currentTimeMillis();
        setTimeout(3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dtime=(System.currentTimeMillis()-time)/1000.0;
    	double[] expected=feedForward.getExpected(dtime);
    	
    	Robot.drivetrain.forward(feedForward.getVoltage(expected[1], expected[2], expected[0], -Robot.drivetrain.getLeftDistance()));
    	SmartDashboard.putString("expected", Arrays.toString(expected));
    	SmartDashboard.putNumber("dtime", dtime);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("stopped");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
