package com.spikes2212.robot2016.subsystems.roller;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon shooterTalon;
    public Shooter(CANTalon shooterTalon){
    	this.shooterTalon=shooterTalon;
    }
    public Shooter(int shooterTalonPort){
		this(new CANTalon(shooterTalonPort));
    }
    public void ShootBySpeed(double speed){
    	this.shooterTalon.set(speed);
    }
    public void stop(){
    	this.shooterTalon.set(0);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

