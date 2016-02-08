package com.spikes2212.robot2016.commands.autonomous;

import com.spikes2212.robot2016.Field;
import com.spikes2212.robot2016.commands.drivetrain.PIDStraightDriveByDistance;
import com.spikes2212.robot2016.commands.drivetrain.PIDTurnDriveByAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveFromDefenseToTower extends CommandGroup {
    
	private int slot;
	private double turningAngle, drivingDistance;
	
    public DriveFromDefenseToTower(int slot) {
    	initSlot(slot);
    }
    
    private void initSlot(int slot){
    	this.slot = slot;
    	this.turningAngle = Field.getAngleToTurnFromDefenseTowardsTower(slot);
    	this.drivingDistance = Field.getDistanceFromDefenseToTower(slot);
    	addSequential(new PIDTurnDriveByAngle(turningAngle));
    	addSequential(new PIDStraightDriveByDistance(drivingDistance));
    	addSequential(new PIDTurnDriveByAngle(-turningAngle));
    }
    
    public int getSlot(){
    	return slot;
    }

	public double getTurningAngle() {
		return turningAngle;
	}

	public double getDrivingDistance() {
		return drivingDistance;
	} 
}
