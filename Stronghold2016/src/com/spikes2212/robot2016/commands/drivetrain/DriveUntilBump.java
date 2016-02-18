package com.spikes2212.robot2016.commands.drivetrain;

import com.spikes2212.robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilBump extends Command {
    
	private double SPEED;
	
    public  DriveUntilBump(double speed) {
    	requires(Robot.drivetrain);
    	SPEED=speed;
    }

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(!Robot.triz.isBump()){
			Robot.drivetrain.forward(SPEED);
		}
	}

	@Override
	protected boolean isFinished() {
		
		return Robot.triz.isBump();
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		end();
		// TODO Auto-generated method stub
		
	}
}
