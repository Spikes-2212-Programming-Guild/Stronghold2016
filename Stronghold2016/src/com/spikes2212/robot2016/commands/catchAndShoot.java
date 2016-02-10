package com.spikes2212.robot2016.commands;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.picker.RollBallIn;
import com.spikes2212.robot2016.commands.shooter.ShootByVoltage;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class catchAndShoot extends CommandGroup {
    
    public  catchAndShoot() {
    	addSequential(new RollBallIn());
    	addSequential(new ShootByVoltage(Constants.SHOOTING_VOLTAGE));
    }
}
