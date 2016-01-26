package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightDriveToPortcullis extends Command {
	
	private double speed;
	
    public StraightDriveToPortcullis(double speed) {
    	requires(Robot.drivetrain);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!isFinished()){
    		Robot.drivetrain.forward(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.triz.isUnderPortcullis();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
