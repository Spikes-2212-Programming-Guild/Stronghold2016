package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.Robot;
import com.spikes2212.robot2016.subsystems.Drivetrain;
import com.spikes2212.robot2016.util.FeedForward;

import edu.wpi.first.wpilibj.command.Command;


public class FeedForwardStraight extends Command {
	private FeedForward feedForward;
	private final double time;
    public FeedForwardStraight() {
        requires(Robot.drivetrain);
        this.feedForward=new FeedForward(Constants.KP, Constants.MAX_LEFT_VELOCITY, Constants.MAX_ACCELERATION, Constants.MAX_DECCELERATION, 1);
        time=System.currentTimeMillis();
        setTimeout(3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] expected=feedForward.getExpected((System.currentTimeMillis()-time)/1000);
    	Robot.drivetrain.forward(feedForward.getVoltage(expected[1], expected[2], expected[0], Robot.drivetrain.getLeftDistance()));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Robot.drivetrain.getLeftDistance()==1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("stopped");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
